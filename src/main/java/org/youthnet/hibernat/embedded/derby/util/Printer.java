package org.youthnet.hibernat.embedded.derby.util;

import org.youthnet.hibernat.embedded.derby.domain.TestTable;

import java.util.List;

/**
 * User: karl
 * Date: 29-Apr-2010
 */
public class Printer {

    private Printer() {}

    public static void printRows(List<TestTable> rows) {
        System.out.println(" --------------------   -------------------- ");
        System.out.println("|         Id         | |        Name        |");
        System.out.println(" --------------------   -------------------- ");
        for (TestTable row : rows) {
            System.out.println(String.format("|%1$20d| |%2$20s|", row.getId(), row.getName()));
        }
        System.out.println(" --------------------   -------------------- ");
        System.out.println();
    }

    public static void printRow(TestTable row) {
        System.out.println(" --------------------   -------------------- ");
        System.out.println("|         Id         | |        Name        |");
        System.out.println(" --------------------   -------------------- ");
        System.out.println(String.format("|%1$20d| |%2$20s|", row.getId(), row.getName()));
        System.out.println(" --------------------   -------------------- ");
        System.out.println();
    }
}
