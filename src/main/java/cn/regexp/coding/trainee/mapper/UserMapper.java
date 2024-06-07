package cn.regexp.coding.trainee.mapper;

import cn.regexp.coding.trainee.common.datasource.DynamicDataSource;
import cn.regexp.coding.trainee.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/6/2 17:21
 * @description 用户持久层
 */
@Mapper
public interface UserMapper {


    @DynamicDataSource
    @Select("select id, name, age from user")
    List<User> selectFromDb1();

    @DynamicDataSource(name = DynamicDataSource.DB2_DATASOURCE)
    @Select("select id, name, age from user")
    List<User> selectFromDb2();


    @Select("select id, name, age from user")
    List<User> selectFromDb1NoAnno();

}
