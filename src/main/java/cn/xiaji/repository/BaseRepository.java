package cn.xiaji.repository;

import cn.xiaji.domain.Employee;
import cn.xiaji.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/*
自定义一个Repository,它是JpaRepository的功能基础上继承增强
在上面添加@NoRepositoryBean标注，这样Spring Data Jpa在启动时就不会去实例化BaseRepository这个接口
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    //根据Query拿到分页对象(分页)
    Page findPageByQuery(BaseQuery baseQuery);

    //根据Query拿到对应的所有数据(不分页)
    List<T> findByQuery(BaseQuery baseQuery);

    //根据jpql与对应的参数拿到数据(扩展 自己写jpql)
    List findByJpql(String jpql, Object... values);
}

