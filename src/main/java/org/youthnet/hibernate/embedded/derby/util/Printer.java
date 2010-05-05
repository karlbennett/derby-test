package org.youthnet.hibernate.embedded.derby.util;

import org.youthnet.hibernate.embedded.derby.domain.TestTable;

import java.util.List;

/**
 * User: karl
 * Date: 29-Apr-2010
 */
public class Printer {

    static final String LINES = " --------------------------------------   -------------------------------------- ";
    static final String NAMES = "|                  Id                  | |                 Name                 |";
    static final String FORMAT = "| %1$36s | | %2$36s |";

    private Printer() {}

    public static void printRows(List<TestTable> rows) {
        System.out.println(LINES);
        System.out.println(NAMES);
        System.out.println(LINES);
        for (TestTable row : rows) {
            System.out.println(String.format(FORMAT, row.getId().getUuid().toString(), row.getName()));
        }
        System.out.println(LINES);
        System.out.println();
    }

    public static void printRow(TestTable row) {
        System.out.println(LINES);
        System.out.println(NAMES);
        System.out.println(LINES);
        System.out.println(String.format(FORMAT, row.getId().getUuid().toString(), row.getName()));
        System.out.println(LINES);
        System.out.println();
    }
}
