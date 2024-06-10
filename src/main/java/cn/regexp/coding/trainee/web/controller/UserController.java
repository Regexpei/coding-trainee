package cn.regexp.coding.trainee.web.controller;

import cn.regexp.coding.trainee.entity.User;
import cn.regexp.coding.trainee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/6/10 17:07
 * @description 用户控制层
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/selectAll")
    public List<User> selectAll() {
        // 不涉及业务，这里直接使用持久层对象操作，规范应该是调用 Service 层
        return userMapper.selectAll();
    }
}
