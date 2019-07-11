package cn.xiaji.service.Impl;
import cn.xiaji.domain.Role;
import cn.xiaji.repository.RoleRepository;
import cn.xiaji.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
}