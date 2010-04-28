package org.youthnet.hibernat.embedded.derby.domain;

import javax.persistence.*;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
@Entity
@Table(name = "test_table")
public class TestTable extends GenericTable {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
