package cn.regexp.coding.trainee.service.impl;

import cn.regexp.coding.trainee.core.service.BaseService;
import cn.regexp.coding.trainee.domain.Role;
import cn.regexp.coding.trainee.domain.User;
import cn.regexp.coding.trainee.service.IRoleService;
import cn.regexp.coding.trainee.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/8/2 22:20
 * @description 用户服务实现类
 * @since 1.0.0
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Override
    public List<User> selectAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("小一", "admin"));
        userList.add(new User("小二", "user"));
        userList.add(new User("小三", "guest"));

        for (User user : userList) {
            Role role = get(IRoleService.class).getRoleByEnName(user.getRoleEnName());
            if (role != null) {
                user.setRoleCnName(role.getName());
            }
        }
        return userList;
    }
}
