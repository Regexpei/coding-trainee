package cn.regexp.coding.trainee.mapper;

import cn.regexp.coding.trainee.entity.User;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/6/2 17:21
 * @description 用户持久层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Override
    @Select("select id, name, age from user")
    List<User> selectAll();
}
