package cn.xiaji.service.Impl;

import cn.xiaji.common.UserContext;
import cn.xiaji.domain.Employee;
import cn.xiaji.domain.Menu;
import cn.xiaji.repository.MenuRepository;
import cn.xiaji.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements IMenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findByUser() {
        //拿到当前用户
        Employee user = UserContext.getUser();
        System.out.println(user);
        System.out.println(user);
        System.out.println(user);
        System.out.println(user);
        //根据用户拿到他的菜单
        List<Menu> menuList = menuRepository.findByUser(user.getId());
        System.out.println(menuList);
        System.out.println(menuList);
        System.out.println(menuList);
        System.out.println(menuList);
        //1.准备父菜单集合
        List<Menu> parentMenus = new ArrayList<>();
        //2.遍历对应的子菜单
        for (int i = 0; i < menuList.size(); i++) {
            //3.拿到相应的子菜单,获取他的父菜单
            Menu menu = menuList.get(i);
            Menu parent = menu.getParent();
            //4.判断父菜单是否已有我们查的父菜单
            if (!parentMenus.contains(parent)) {
                //不包含的话把父菜单放到集合中
                parentMenus.add(parent);
            }
            //5.不包含的话 把父菜单放到集合中
            parent.getChildren().add(menu);
        }
        return parentMenus;
    }
}