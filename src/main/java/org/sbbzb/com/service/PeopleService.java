package org.sbbzb.com.service;

import com.github.pagehelper.Page;
import org.sbbzb.com.model.PeopleModel;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PeopleService {

    int save(PeopleModel model);

    int del(String id);

    int update(PeopleModel model);

    Page<PeopleModel> find(int pageNow, int pageSize);

    Page<PeopleModel> findByName(int pageNow, int pageSize, String name);
}
