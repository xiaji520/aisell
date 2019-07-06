package cn.xiaji.service;

import cn.xiaji.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, ID extends Serializable> {
    void save(T t);//添加和修改

    void delete(ID id);

    T findone(ID id);

    List<T> findAll();

    //根据Query拿到分页对象(分页)
    Page findPageByQuery(BaseQuery baseQuery);

    //根据Query拿到对应的所有数据(不分页)
    List<T> findByQuery(BaseQuery baseQuery);

    //根据jpql与对应的参数拿到数据
    List findByJpql(String jpql, Object... values);

}
