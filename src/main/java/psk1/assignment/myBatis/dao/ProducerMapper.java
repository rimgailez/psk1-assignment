package psk1.assignment.myBatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import psk1.assignment.myBatis.model.Producer;

@Mapper
public interface ProducerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Producer record);

    Producer selectByPrimaryKey(Integer id);

    List<Producer> selectAll();

    int updateByPrimaryKey(Producer record);
}