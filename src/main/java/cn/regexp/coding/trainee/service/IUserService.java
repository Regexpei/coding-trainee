package cn.regexp.coding.trainee.service;

import cn.regexp.coding.trainee.domain.User;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/8/2 22:19
 * @description 用户接口
 * @since 1.0.0
 */
public interface IUserService {

    /**
     * 查询所有用户及对应角色
     *
     * @return 用户及对应角色
     */
    List<User> selectAll();
}
