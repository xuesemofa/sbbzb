package org.sbbzb.com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.sbbzb.com.mapper.PeopleMapper;
import org.sbbzb.com.model.PeopleModel;
import org.sbbzb.com.service.PeopleService;
import org.sbbzb.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

    @Resource
    private PeopleMapper mapper;

    @Override
    public int save(PeopleModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.save(model);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }

    @Override
    public int update(PeopleModel model) {
        return mapper.update(model);
    }

    @Override
    public Page<PeopleModel> find(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.find();
    }

    @Override
    public Page<PeopleModel> findByName(int pageNow, int pageSize, String name) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findByName("%" + name + "%");
    }
}
