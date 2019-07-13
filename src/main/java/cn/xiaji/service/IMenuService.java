package cn.xiaji.service;

import cn.xiaji.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu, Long> {
    List<Menu> findByUser();
}