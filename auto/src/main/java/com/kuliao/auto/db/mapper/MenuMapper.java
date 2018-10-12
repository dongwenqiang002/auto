package com.kuliao.auto.db.mapper;

import com.kuliao.auto.db.model.Menu;
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
public interface MenuMapper {
    @Delete({
        "delete from menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into menu (id, parent_id, ",
        "icon, title, path_id, ",
        "sort)",
        "values (#{id,jdbcType=INTEGER}, #{parentId,jdbcType=INTEGER}, ",
        "#{icon,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{pathId,jdbcType=INTEGER}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    int insert(Menu record);

    @Select({
        "select",
        "id, parent_id, icon, title, path_id, sort",
        "from menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="path_id", property="pathId", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    Menu selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, parent_id, icon, title, path_id, sort",
        "from menu"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="path_id", property="pathId", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<Menu> selectAll();

    @Update({
        "update menu",
        "set parent_id = #{parentId,jdbcType=INTEGER},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "path_id = #{pathId,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menu record);
}