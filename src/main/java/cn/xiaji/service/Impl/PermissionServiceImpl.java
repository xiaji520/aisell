package cn.xiaji.service.Impl;

import cn.xiaji.domain.Permission;
import cn.xiaji.repository.PermissionRepository;
import cn.xiaji.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements IPermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findPermissionByUser(Long userId) {
        return permissionRepository.findPermissionByUser(userId);
    }
}