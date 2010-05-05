package org.youthnet.hibernate.embedded.derby.dao;

import org.youthnet.hibernate.embedded.derby.domain.TestTable;
import org.youthnet.hibernate.embedded.derby.domain.types.UuidType;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
public interface TestTableDao extends GenericDao<TestTable, UuidType> {
}
