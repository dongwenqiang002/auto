package com.kuliao.auto.db.mapper;

import com.kuliao.auto.db.model.PathRole;
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
public interface PathRoleMapper {
    @Delete({
        "delete from path_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into path_role (id, role_id, ",
        "path_id)",
        "values (#{id,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{pathId,jdbcType=INTEGER})"
    })
    int insert(PathRole record);

    @Select({
        "select",
        "id, role_id, path_id",
        "from path_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="path_id", property="pathId", jdbcType=JdbcType.INTEGER)
    })
    PathRole selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, role_id, path_id",
        "from path_role"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="path_id", property="pathId", jdbcType=JdbcType.INTEGER)
    })
    List<PathRole> selectAll();

    @Update({
        "update path_role",
        "set role_id = #{roleId,jdbcType=INTEGER},",
          "path_id = #{pathId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PathRole record);
}