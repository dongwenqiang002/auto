package com.kuliao.auto.db.mapper;

import com.kuliao.auto.db.model.Path;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface PathMapper {
    @Delete({
        "delete from path",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into path (id, path, ",
        "component, meta, ",
        "parent_id, title)",
        "values (#{id,jdbcType=INTEGER}, #{path,jdbcType=VARCHAR}, ",
        "#{component,jdbcType=VARCHAR}, #{meta,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR})"
    })
    int insert(Path record);

    @Select({
        "select",
        "id, path, component, meta, parent_id, title",
        "from path",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="component", property="component", jdbcType=JdbcType.VARCHAR),
        @Result(column="meta", property="meta", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR)
    })
    Path selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, path, component, meta, parent_id, title",
        "from path"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="component", property="component", jdbcType=JdbcType.VARCHAR),
        @Result(column="meta", property="meta", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR)
    })
    List<Path> selectAll();

    @Update({
        "update path",
        "set path = #{path,jdbcType=VARCHAR},",
          "component = #{component,jdbcType=VARCHAR},",
          "meta = #{meta,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Path record);
}