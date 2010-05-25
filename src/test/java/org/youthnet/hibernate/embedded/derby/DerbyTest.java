package org.youthnet.hibernate.embedded.derby;


import static junit.framework.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class DerbyTest {

    protected final Log log = LogFactory.getLog(DerbyTest.class);

    @Resource(name = "derbyDataSource")
    private DataSource derbyDataSource;

    JdbcTemplate jdbcTemplate;

    @Before
    public void buildUp() {
        jdbcTemplate = new JdbcTemplate(derbyDataSource);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void tableExistsTest() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM sys.systables WHERE tablename = 'TEST_TABLE'");

//        printRows(rows);

        List<String> tableNames = jdbcTemplate.queryForList(
                "SELECT tablename FROM sys.systables WHERE tablename <> 'GENERICTABLE' AND tablename NOT LIKE 'SYS%'", String.class);
        for(String name : tableNames) {
            System.out.println(name);   
        }

        assertTrue("a table exists", 0 < rows.size());
        assertEquals("table is test table", "TEST_TABLE", rows.get(0).get("TABLENAME"));

//        rows = jdbcTemplate.queryForList(
//                "SELECT * FROM sys.syscolumns WHERE " +
//                        "referenceid = (SELECT tableid FROM sys.systables WHERE tablename = 'TEST_TABLE')");
//
//        printRows(rows);
    }

    private void printRows(List<Map<String, Object>> rows) {
        for (Map<String, Object> row : rows) {
            String[] columnNames = row.keySet().toArray(new String[0]);
            for (String columnName : columnNames) {
                System.out.println(String.format("Column: %1$-30s Value: %2$s", columnName, row.get(columnName)));
            }
            System.out.println("-----------------------------------------------------------------");
        }
    }
}
