package org.youthnet.hibernate.embedded.derby.dao;

import org.youthnet.hibernate.embedded.derby.domain.GenericTable;

import java.util.List;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
public interface GenericDao <M extends GenericTable, I> {

    public M create(M object);

    public M request(I id);

    public List<M> request();

    public M update(M object);

    public M delete(I id);

    public M delete(M object);
}
