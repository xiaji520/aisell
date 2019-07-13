package cn.xiaji.service;

import cn.xiaji.domain.Permission;

import java.util.Set;

public interface IPermissionService extends IBaseService<Permission, Long> {
    Set<String> findPermissionByUser(Long userId);
}