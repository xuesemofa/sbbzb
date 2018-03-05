package org.sbbzb.com.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.sbbzb.com.model.PeopleModel;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PeopleMapper {

    @Insert({
            "insert into people_table (uuid,name) values (#{model.uuid},#{model.name})"
    })
    int save(@Param("model") PeopleModel model);

    @Delete({
            "delete people_table where uuid = #{id}"
    })
    int del(@Param("id") String id);

    @Update({
            "update people_table set name = #{model.name} where uuid = #{model.uuid}"
    })
    int update(@Param("model") PeopleModel model);

    @Select({
            "select * from people_table"
    })
    Page<PeopleModel> find();

    @Select({
            "select * from people_table where name like #{name}"
    })
    Page<PeopleModel> findByName(@Param("name") String name);
}
