package com.annoDemo.dao;

import com.annoDemo.domain.Role;

import java.util.List;

public interface IRoleDao {

    /**
     * @return 返回所有角色
     */
    List<Role> listRoles();
}
