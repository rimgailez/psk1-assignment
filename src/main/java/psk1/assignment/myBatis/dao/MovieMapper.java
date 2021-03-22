package psk1.assignment.myBatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import psk1.assignment.myBatis.model.Movie;

@Mapper
public interface MovieMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Movie record);

    Movie selectByPrimaryKey(Integer id);

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie record);
}