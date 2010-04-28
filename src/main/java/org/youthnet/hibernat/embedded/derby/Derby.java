package org.youthnet.hibernat.embedded.derby;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.youthnet.hibernat.embedded.derby.dao.TestTableDao;
import org.youthnet.hibernat.embedded.derby.domain.TestTable;

import java.util.Arrays;
import java.util.List;

/**
 * Derby Test
 */
public class Derby {

    private static final String GET = "--get";
    private static final String ADD = "--add";
    private static final String DELETE = "--delete";
    private static final String UPDATE = "--update";
    private static final String PRINT = "--print";

    private static AbstractApplicationContext context;

    private static TestTableDao testTableDao;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        List<String> arguments = Arrays.asList(args);

        testTableDao =  context.getBean("testTableDao", TestTableDao.class);

        TestTable testTable = new TestTable();

        if (arguments.contains(ADD)) {
            int addIndex = arguments.indexOf(ADD);
            if (arguments.size() > ++addIndex) {
                testTable.setName(arguments.get(addIndex));
                System.out.println("Adding Row:");
                createRow(testTable);
                printRow(testTable.getId());
            }
        }

        if (arguments.contains(GET)) {
            int addIndex = arguments.indexOf(GET);
            if (arguments.size() > ++addIndex) {
                System.out.println("Requesting Row:");
                printRow(Integer.parseInt(arguments.get(addIndex)));
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
                    updateRow(testTable);
                    printRow(testTable.getId());
                }
            }
        }

        if (arguments.contains(DELETE)) {
            int addIndex = arguments.indexOf(DELETE);
            int id = 0;
            if (arguments.size() > ++addIndex) {
                id = Integer.parseInt(arguments.get(addIndex));
                System.out.println("Deleting Row:");
                testTable = deleteRow(id);
                printRow(testTable);
            }
        }

        if (arguments.contains(PRINT)) {
            int addIndex = arguments.indexOf(PRINT);
            System.out.println("Printing:");
            printRows();
        }

    }

    public static TestTable createRow(TestTable testTable) {
        return testTableDao.create(testTable);
    }

    public static TestTable requestRow(int id) {
        return testTableDao.request(id);
    }

    public static List<TestTable> requestRows() {
        return testTableDao.request();
    }

    public static TestTable updateRow(TestTable testTable) {
        return testTableDao.update(testTable);
    }

    public static TestTable deleteRow(int id) {
        return testTableDao.delete(id);
    }

    public static void printRows() {
        List<TestTable> rows = requestRows();

        System.out.println(" --------------------   -------------------- ");
        System.out.println("|         Id         | |        Name        |");
        System.out.println(" --------------------   -------------------- ");
        for (TestTable row : rows) {
            System.out.println(String.format("|%1$20d| |%2$20s|", row.getId(), row.getName()));
        }
        System.out.println(" --------------------   -------------------- ");
    }

    public static void printRow(int id) {
        TestTable row = requestRow(id);
        System.out.println(" --------------------   -------------------- ");
        System.out.println("|         Id         | |        Name        |");
        System.out.println(" --------------------   -------------------- ");
        System.out.println(String.format("|%1$20d| |%2$20s|", row.getId(), row.getName()));
        System.out.println(" --------------------   -------------------- ");
    }

    public static void printRow(TestTable row) {
        System.out.println(" --------------------   -------------------- ");
        System.out.println("|         Id         | |        Name        |");
        System.out.println(" --------------------   -------------------- ");
        System.out.println(String.format("|%1$20d| |%2$20s|", row.getId(), row.getName()));
        System.out.println(" --------------------   -------------------- ");
    }
}
