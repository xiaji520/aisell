package cn.xiaji.repository;

import cn.xiaji.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/*
    只要发现接口继承了JpaRepository,它就会自动去完成相应的CRUD
    Employee:CRUD操作的哪一个类型 Long:主键的类型
 */
//改继承BaseRepository
public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    //验证用户名是否存在
    @Query("select count(o) from Employee o where o.username=?1")
    Long getCountByUsername(String username);

    //根据用户名查询
    Employee findByUsername(String uername);

    //根据用户名模糊查询
    List<Employee> findByUsernameLike(String uername);

    //根据用户名与邮件模糊查询
    List<Employee> findByUsernameLikeAndEmailLike(String uername, String email);

    /*----------------------------------------------------------------*/
    //根据用户名查询
    @Query("select o from Employee o where o.username=?1")
    Employee queryByUsername(String username);

    //根据用户名模糊查询
    @Query("select o from Employee o where o.username like ?1")
    List<Employee> queryByUsernameLike(String uername);

    //根据用户名与邮件模糊查询
    @Query("select o from Employee o where o.username like ?1 and o.email like ?2")
    List<Employee> queryByUsernameAndEmailLike(String uername, String email);
}
