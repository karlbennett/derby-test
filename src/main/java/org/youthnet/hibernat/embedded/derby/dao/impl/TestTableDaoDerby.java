package org.youthnet.hibernat.embedded.derby.dao.impl;

import org.springframework.stereotype.Component;
import org.youthnet.hibernat.embedded.derby.dao.TestTableDao;
import org.youthnet.hibernat.embedded.derby.domain.TestTable;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
@Component("testTableDao")
public class TestTableDaoDerby extends GenericDaoDerby<TestTable, Integer> implements TestTableDao {

    public TestTableDaoDerby() {
        setPersistentClass(TestTable.class);
    }
}
