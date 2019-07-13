package cn.xiaji.repository;

import cn.xiaji.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PermissionRepository extends BaseRepository<Permission, Long> {
    //根据当前用户获取到他对应的所有权限
    //jpql关联法制:1.不需要写one 2.关联前面对象的别名,属性
    @Query("select p.sn from Employee o join o.roles r join r.permissions p where o.id =?1")
    Set<String> findPermissionByUser(Long userId);

}