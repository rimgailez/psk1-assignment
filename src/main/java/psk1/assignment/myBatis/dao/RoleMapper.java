package psk1.assignment.myBatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import psk1.assignment.myBatis.model.Role;

@Mapper
public interface RoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectRolesInMovie(Integer movieId);
}