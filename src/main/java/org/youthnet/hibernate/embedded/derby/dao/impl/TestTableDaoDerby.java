package org.youthnet.hibernate.embedded.derby.dao.impl;

import org.springframework.stereotype.Component;
import org.youthnet.hibernate.embedded.derby.dao.TestTableDao;
import org.youthnet.hibernate.embedded.derby.domain.TestTable;
import org.youthnet.hibernate.embedded.derby.domain.types.UuidType;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
@Component("testTableDao")
public class TestTableDaoDerby extends GenericDaoDerby<TestTable, UuidType> implements TestTableDao {

    public TestTableDaoDerby() {
        setPersistentClass(TestTable.class);
    }
}
