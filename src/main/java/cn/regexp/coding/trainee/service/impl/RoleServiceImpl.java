package cn.regexp.coding.trainee.service.impl;

import cn.regexp.coding.trainee.domain.Role;
import cn.regexp.coding.trainee.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Regexpei
 * @date 2024/8/2 22:21
 * @description 角色服务实现类
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private static final HashMap<String, Role> ROLE_MAP =new HashMap<String, Role>() {{
        put("admin", new Role("admin", "管理员"));
        put("user", new Role("user", "用户"));
        put("guest", new Role("guest", "访客"));
    }};

    @Override
    public Role getRoleByEnName(String enName) {
        return ROLE_MAP.get(enName);
    }
}
