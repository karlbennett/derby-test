package org.youthnet.hibernate.embedded.derby.domain;

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
    @Column
    private byte[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
