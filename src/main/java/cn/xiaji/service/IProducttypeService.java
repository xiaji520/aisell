package cn.xiaji.service;
import cn.xiaji.domain.Producttype;

import java.util.List;

public interface IProducttypeService extends IBaseService<Producttype, Long> {
    List<Producttype> findAllTypes();
}