package cn.regexp.coding.trainee.service;

import cn.regexp.coding.trainee.domain.Role;

/**
 * @author Regexpei
 * @date 2024/8/2 22:20
 * @description 角色接口
 * @since 1.0.0
 */
public interface IRoleService {

    /**
     * 根据角色标识获取角色
     *
     * @param enName 角色标识
     * @return 角色
     */
    Role getRoleByEnName(String enName);
}
