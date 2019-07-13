package cn.xiaji.repository;

import cn.xiaji.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, Long> {
    //根据不同用户得到不同菜单
    @Query("select distinct m from Employee e join e.roles r join r.permissions p join p.menu m where e.id = ?1")
    List<Menu> findByUser(Long userId);
}