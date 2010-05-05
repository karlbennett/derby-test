package org.youthnet.hibernate.embedded.derby.domain.types;

import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.util.UUID;

/**
 * User: karl
 * Date: 24-Mar-2010
 */

public interface UuidType extends UserType, Serializable {

    public void setUuid(UUID uuid);

    public UUID getUuid();
}
