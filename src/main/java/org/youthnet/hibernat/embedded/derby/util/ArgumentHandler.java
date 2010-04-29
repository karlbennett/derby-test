package org.youthnet.hibernat.embedded.derby.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.youthnet.hibernat.embedded.derby.dao.TestTableDao;
import org.youthnet.hibernat.embedded.derby.domain.TestTable;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * User: karl
 * Date: 29-Apr-2010
 */
@Component("argumentHandler")
public class ArgumentHandler {

    private static final String GET = "--get";
    private static final String ADD = "--add";
    private static final String DELETE = "--delete";
    private static final String UPDATE = "--update";

    private AbstractApplicationContext context;

    @Resource(name = "testTableDao")
    private TestTableDao testTableDao;

    public void handleArguments(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        List<String> arguments = Arrays.asList(args);

        TestTable testTable = new TestTable();

        if (arguments.contains(ADD)) {
            int addIndex = arguments.indexOf(ADD);
            if (arguments.size() > ++addIndex) {
                String names = arguments.get(addIndex);
                if (!names.contains("--")) {
                    String[] nameArray = names.split(",");
                    for (String name : nameArray) {
                        testTable = new TestTable();
                        testTable.setName(name);
                        System.out.println("Adding Row:");
                        testTableDao.create(testTable);
                        Printer.printRow(testTableDao.request(testTable.getId()));
                    }
                }
            }
        }

        if (arguments.contains(GET)) {
            int addIndex = arguments.indexOf(GET);
            if (arguments.size() > ++addIndex) {
                String ids = arguments.get(addIndex);
                if (!ids.contains("--")) {
                    String[] idArray = ids.split(",");
                    for (String id : idArray) {
                        System.out.println("Requesting Row:");
                        Printer.printRow(testTableDao.request(Integer.parseInt(id)));
                    }
                }
            }
        }

        if (arguments.contains(UPDATE)) {
            int addIndex = arguments.indexOf(UPDATE);
            if (arguments.size() > ++addIndex) {
                String[] updateArray = arguments.get(addIndex).split(",");
                if (updateArray.length == 2) {
                    testTable.setId(Integer.parseInt(updateArray[0]));
                    testTable.setName(updateArray[1]);
                    System.out.println("Updating Row:");
                    Printer.printRow(testTableDao.request(testTable.getId()));
                    System.out.println("To:");
                    Printer.printRow(testTable);
                    testTableDao.update(testTable);
                }
            }
        }

        if (arguments.contains(DELETE)) {
            int addIndex = arguments.indexOf(DELETE);
            if (arguments.size() > ++addIndex) {
                String ids = arguments.get(addIndex);
                if (!ids.contains("--")) {
                    String[] idArray = ids.split(",");
                    for (String id : idArray) {
                        System.out.println("Deleting Row:");
                        testTable = testTableDao.delete(Integer.parseInt(id));
                        Printer.printRow(testTable);
                    }
                }
            }
        }

        System.out.println("Database:");
        Printer.printRows(testTableDao.request());
    }
}
