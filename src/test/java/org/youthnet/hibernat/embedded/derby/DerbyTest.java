package org.youthnet.hibernat.embedded.derby;


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
import org.youthnet.hibernat.embedded.derby.dao.TestTableDao;

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

        assertTrue("a table exists", 0 < rows.size());
        assertEquals("table is test table", "TEST_TABLE", rows.get(0).get("TABLENAME"));
    }

    private void printRows(List<Map<String, Object>> rows) {
        for (Map<String, Object> row : rows) {
            String[] columnNames = row.keySet().toArray(new String[0]);
            for (String columnName : columnNames) {
                System.out.println(" Column: " + columnName + " Value: " + row.get(columnName));
            }
            System.out.println("-----------------------------------------------------------------");
        }
    }
}
