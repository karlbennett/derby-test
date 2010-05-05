package org.youthnet.hibernate.embedded.derby.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.youthnet.hibernate.embedded.derby.domain.types.UuidType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
@MappedSuperclass
public abstract class GenericTable {
    
    @Id
    @Column(columnDefinition = "raw(16)")
    @Generated(GenerationTime.NEVER)
    @Type(type = "org.youthnet.hibernate.embedded.derby.domain.types.UuidTypeGeneric")
    private UuidType id;

    public UuidType getId() {
        return id;
    }

    public void setId(UuidType id) {
        this.id = id;
    }
}
