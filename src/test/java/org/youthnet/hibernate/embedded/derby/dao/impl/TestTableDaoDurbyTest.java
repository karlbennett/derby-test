package org.youthnet.hibernate.embedded.derby.dao.impl;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.hibernate.embedded.derby.dao.TestTableDao;
import org.youthnet.hibernate.embedded.derby.domain.TestTable;
import org.youthnet.hibernate.embedded.derby.domain.types.UuidTypeGeneric;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestTableDaoDurbyTest {

    @Resource(name = "testTableDao")
    private TestTableDao testTableDao;

    @Resource(name = "derbyDataSource")
    private DataSource derbyDataSource;

    JdbcTemplate jdbcTemplate;

    private TestTable testTable1;
    private TestTable testTable2;
    private TestTable testTable3;

    @Before
    public void buildUp() {
        testTable1 = new TestTable();
        testTable2 = new TestTable();
        testTable3 = new TestTable();

        testTable1.setId(UuidTypeGeneric.fromString("11111111-1111-1111-1111-111111111111"));
        testTable1.setName("Test1");
        testTable2.setId(UuidTypeGeneric.fromString("22222222-2222-2222-2222-222222222222"));
        testTable2.setName("Test2");
        testTable3.setId(UuidTypeGeneric.fromString("33333333-3333-3333-3333-333333333333"));
        testTable3.setName("Test3");

        jdbcTemplate = new JdbcTemplate(derbyDataSource);
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM test_table");        
    }

    @Test
    public void testCreate() throws Exception {
        testTableDao.create(testTable1);

        String name = jdbcTemplate.queryForObject("SELECT name FROM test_table WHERE id = (X'" + testTable1.getId().getUuid().toString().replace("-","") + "')", String.class);

        assertEquals("test name value exists", "Test1", name);
    }

    @Test
    public void testRequestOne() throws Exception {
        testTableDao.create(testTable1);

        TestTable testTable = testTableDao.request(testTable1.getId());

        assertNotNull("test request not null", testTable);
        assertEquals("correct table retrieved", "Test1", testTable.getName());
    }

    @Test
    public void testRequestAll() throws Exception {
        testTableDao.create(testTable1);
        testTableDao.create(testTable2);
        testTableDao.create(testTable3);

        List<TestTable> testTables = testTableDao.request();

        assertEquals("all rows retrieved", 3, testTables.size());
        assertEquals("test row ones name", "Test1", testTables.get(0).getName());
        assertEquals("test row twos name", "Test2", testTables.get(1).getName());
        assertEquals("test row threes name", "Test3", testTables.get(2).getName());
    }

    @Test
    public void testUpdate() throws Exception {
        testTableDao.create(testTable1);

        String name = jdbcTemplate.queryForObject("SELECT name FROM test_table WHERE id = (X'" + testTable1.getId().getUuid().toString().replace("-","") + "')", String.class);

        assertEquals("test old name", "Test1", name);

        TestTable testTable = testTableDao.request(testTable1.getId());
        testTable.setName("New Test");

        testTableDao.update(testTable);

        name = jdbcTemplate.queryForObject("SELECT name FROM test_table WHERE id = (X'" + testTable1.getId().getUuid().toString().replace("-","") + "')", String.class);

        assertEquals("test new name", "New Test", name);
    }

    @Test
    public void testDeleteWithId() throws Exception {
        testTableDao.create(testTable1);
        testTableDao.delete(testTable1.getId());

        List<String> names = jdbcTemplate.queryForList("SELECT name FROM test_table", String.class);

        assertEquals("delete row with id", 0, names.size());
    }

    @Test
    public void testDelete() throws Exception {
        testTableDao.create(testTable1);
        testTableDao.delete(testTable1);

        List<String> names = jdbcTemplate.queryForList("SELECT name FROM test_table", String.class);

        assertEquals("delete row with object", 0, names.size());
    }
}
