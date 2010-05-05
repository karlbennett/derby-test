package org.youthnet.hibernate.embedded.derby.domain.types;

import org.hibernate.HibernateException;
import org.hibernate.Hibernate;
import org.youthnet.hibernate.embedded.derby.util.UuidConverter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAccessorType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Olivier Van Acker
 *         Date: Oct 23, 2008
 *         Partly copied this code from: http://forum.springframework.org/showthread.php?t=46445
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement
public class UuidTypeGeneric implements UuidType {

    private UUID uuid;

    public UuidTypeGeneric() {
        super();
    }

    public UuidTypeGeneric(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    private static final String CAST_EXCEPTION_TEXT = " cannot be cast to a org.youthnet.vbase.dao.custom.UuidTypeGeneric.";

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#sqlTypes()
	 */
    @Override
    public int[] sqlTypes() {
        return new int[]{Hibernate.BINARY.sqlType()}; // Force Derby to use a binary type for this column.
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#returnedClass()
	 */
    @Override
    public Class returnedClass() {
        return UuidTypeGeneric.class;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#equals(java.lang.Object,
	 *      java.lang.Object)
	 */
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null && y == null) {
            return true;
        } else if (x == null || y == null) {
            return false;
        }
        if (!UuidTypeGeneric.class.isAssignableFrom(x.getClass())) {
            throw new HibernateException(x.getClass().toString() + CAST_EXCEPTION_TEXT);
        } else if (!UuidTypeGeneric.class.isAssignableFrom(y.getClass())) {
            throw new HibernateException(y.getClass().toString() + CAST_EXCEPTION_TEXT);
        }

        UUID a = ((UuidTypeGeneric) x).getUuid();
        UUID b = ((UuidTypeGeneric) y).getUuid();

        return a.equals(b);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
    @Override
    public int hashCode(Object x) throws HibernateException {
        if (!UuidTypeGeneric.class.isAssignableFrom(x.getClass())) {
            throw new HibernateException(x.getClass().toString() + CAST_EXCEPTION_TEXT);
        }
        UUID uuid = ((UuidTypeGeneric) x).getUuid();
        return uuid.hashCode();
    }

    /*
    * (non-Javadoc)
    *
    * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet,
    *      java.lang.String[], java.lang.Object)
    */

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
        Object value = resultSet.getObject(names[0]);
        if (value == null) {
            return null;
        } else {
            return new UuidTypeGeneric(UuidConverter.convertByteArrayToUuid((byte[])value));
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
	 *      java.lang.Object, int)
	 */
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.NULL);
            return;
        }

        if (!UuidTypeGeneric.class.isAssignableFrom(value.getClass())) {
            throw new HibernateException(value.getClass().toString() + " with value " + value.toString() + CAST_EXCEPTION_TEXT);
        }

        preparedStatement.setObject(index, UuidConverter.convertUuidArrayToByteArray(((UuidTypeGeneric) value).getUuid()));

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
	 */
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return (UuidTypeGeneric) value;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#isMutable()
	 */
    @Override
    public boolean isMutable() {
        return false;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable,
	 *      java.lang.Object)
	 */
    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.hibernate.usertype.UserType#replace(java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    /**
     * Returns the least significant 64 bits of this UUID's 128 bit value.
     *
     * @return
     */
    @XmlElement
    public long getLeastSignificantBits() {
        return uuid.getLeastSignificantBits();
    }

    /**
     * Returns the most significant 64 bits of this UUID's 128 bit value.
     *
     * @return
     */
    @XmlElement
    public long getMostSignificantBits() {
        return uuid.getMostSignificantBits();
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof UuidTypeGeneric) {
            UuidTypeGeneric uuidCompare = (UuidTypeGeneric) object;
            if(this.uuid.equals(uuidCompare.getUuid())) {
                return true;
            }
        }
        return false;
    }

     /**
     * Return uuid as string
     * @return UUID string
     */
    @Override
    public String toString() {
        return uuid.toString();
    }

    /**
     * Return uuid vcreated from string
     * @param uuidString string UUID
     * @return UuidUserType
     */
    public static UuidTypeGeneric fromString(String uuidString) {
        return new UuidTypeGeneric(UUID.fromString(uuidString));
    }

}
