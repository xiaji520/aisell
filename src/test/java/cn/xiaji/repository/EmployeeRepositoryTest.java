package cn.xiaji.repository;

import cn.xiaji.domain.Employee;
import cn.xiaji.query.EmployeeQuery;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testAdd() throws Exception {
        Employee employee = new Employee();
        employee.setAge(12);
        Employee save = employeeRepository.save(employee);
        System.out.println(save);
    }

    @Test
    public void testUpdate() throws Exception {
        Employee employee = new Employee();
        employee.setId(274L);
        employee.setAge(22);
        Employee save = employeeRepository.save(employee);
        System.out.println(save);
    }

    @Test
    public void testDelete() throws Exception {
        employeeRepository.delete(274L);
    }

    @Test
    public void testFindOne() throws Exception {
        Employee one = employeeRepository.findOne(1L);
        System.out.println(one);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Employee> list = employeeRepository.findAll();
        list.forEach(e -> System.out.println(e));
    }

    /*分页测试*/
    @Test
    public void testPage() throws Exception {
        /*
        int page:当前页(0开始计算的)
        int size:每页条数
        */
        Pageable pageable = new PageRequest(0, 10);
        Page<Employee> page = employeeRepository.findAll(pageable);
        page.forEach(e -> System.out.println(e));
        //总条数
        System.out.println(page.getTotalElements());
        //总页数
        System.out.println(page.getTotalPages());
        //当前页数
        System.out.println(page.getNumber());
        //当前页的数量
        System.out.println(page.getNumberOfElements());
        //每条页数
        System.out.println(page.getSize());
    }

    /*排序*/
    @Test
    public void testSort() throws Exception {
        /*
        第一个参数:排序规则
        第二个参数:排序字段
        */
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        List<Employee> list = employeeRepository.findAll(sort);
        list.forEach(e -> System.out.println(e));
    }

    /*分页加排序*/
    @Test
    public void testPageSort() throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Employee> page = employeeRepository.findAll(pageable);
        page.forEach(e -> System.out.println(e));
    }

    /*-------------------------------------------------*/
    /*根据用户名查询*/
    @Test
    public void testFindByUsername() throws Exception {
        Employee employee = employeeRepository.findByUsername("admin");
        System.out.println(employee);
    }

    /*根据用户名模糊查询*/
    @Test
    public void testFindByUsernameLike() throws Exception {
        List<Employee> like = employeeRepository.findByUsernameLike("%admin%");
        like.forEach(e -> System.out.println(e));
    }

    /*根据用户名和邮件模糊查询*/
    @Test
    public void testFindByUsernameLikeAndEmailLike() throws Exception {
        List<Employee> like = employeeRepository.findByUsernameLikeAndEmailLike("%admin%", "%2%");
        like.forEach(e -> System.out.println(e));
    }

    /*-------------------------------------------------------------------*/
    /*根据用户名查询*/
    @Test
    public void testFindQueryUsername() throws Exception {
        Employee employee = employeeRepository.queryByUsername("admin");
        System.out.println(employee);
    }

    /*根据用户名模糊查询*/
    @Test
    public void testFindQueryUsernameLike() throws Exception {
        List<Employee> like = employeeRepository.queryByUsernameLike("%admin%");
        like.forEach(e -> System.out.println(e));
    }

    /*根据用户名和邮件模糊查询*/
    @Test
    public void testFindQueryUsernameAndEmailLike() throws Exception {
        List<Employee> like = employeeRepository.queryByUsernameAndEmailLike("%admin%", "%2%");
        like.forEach(e -> System.out.println(e));
    }

    /*-----------------------------------------------------------------*/
    /*根据用户名模糊查询*/
    @Test
    public void testJpaSpecificationExecutor() throws Exception {
        /*
        根据相应的规则(Specification)去查询对应的数据
            Predicate(谓语): where xxx=? and yyy=?
            Root:可以获取类中相应的属性(拿到xxx,yyy)
            CriteriaQuery:如select,from,where,group by ,order by等
            CriteriaBuilder:解决 xxx=?或者xxx like ?或者 xxx>?...
                            多个条件结合 xxx=? and/or yyy >?...
        */
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.使用root去拿username属性
                Path username = root.get("username");
                //2.对这个属性添加查询规则
                Predicate like = criteriaBuilder.like(username, "%1%");
                return like;
            }
        });

        list.forEach(e -> System.out.println(e));
    }

    /*根据多个条件模糊查询*/
    @Test
    public void testJpaSpecificationExecutor2() throws Exception {
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //使用root去拿username属性
                Path username = root.get("username");
                //对这个属性添加查询规则
                Predicate like1 = criteriaBuilder.like(username, "%1%");

                //使用root去拿email属性
                Path email = root.get("email");
                //对这个属性添加查询规则
                Predicate like2 = criteriaBuilder.like(email, "%2%");

                //多个条件连接起来
                Predicate and = criteriaBuilder.and(like1, like2);
                return and;
            }
        });
        list.forEach(e -> System.out.println(e));
    }


    /*模糊查询+分页+排序*/
    @Test
    public void testJpaSpecificationExecutor3() throws Exception {
        //排序
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        //分页
        Pageable pageable = new PageRequest(0, 5, sort);
        Page<Employee> page = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //使用root去拿username属性
                Path username = root.get("username");
                //对这个属性添加查询规则
                Predicate like = criteriaBuilder.like(username, "%1%");
                return like;
            }
        }, pageable);
        page.forEach(e -> System.out.println(e));
    }

    /*----------------------------------------------------------------*/
    //别人的包
    @Test
    public void testJpaSpec() throws Exception {
        Specification<Employee> username = Specifications.<Employee>and()
                .like("username", "%1%")
                .like("email", "%2%")
                .gt("age", 20)
                .build();
        //查询
        List<Employee> list = employeeRepository.findAll(username);
        list.forEach(e -> System.out.println(e));
    }

    @Test
    public void testJpaSpec2() throws Exception {
        //排序
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        //分页
        Pageable pageable = new PageRequest(0, 5, sort);

        Specification<Employee> username = Specifications.<Employee>and()
                .like("username", "%1%")
                // .like("email", "%2%")
                // .gt("age", 20)
                .build();
        //查询
        Page<Employee> page = employeeRepository.findAll(username, pageable);
        page.forEach(e -> System.out.println(e));
    }

/*----------------------------------*/
    //测试
    @Test
    public void testJpaSpecPageQuery() throws Exception {
        //模拟前台传参
        EmployeeQuery query = new EmployeeQuery();
        query.setUsername("1");
        //query.setEmail("2");
        //query.setAge(20);
        query.setOrderName("age");
        query.setOrderType("DESC");
        //从Query中获取到这个对象
        Specification spec = query.createSpec();

        //排序
        Sort sort = query.createSort();

        //分页
        query.setCurrentPage(1);
        query.setPageSize(5);
        Pageable pageable = new PageRequest(query.getJpaCurrentPage(), query.getPageSize(),sort);
        Page page = employeeRepository.findAll(spec, pageable);
        page.forEach(e -> System.out.println(e));
    }

    //扩展
     @Test
     public void testFindPageByQuery() throws Exception {
         EmployeeQuery query = new EmployeeQuery();
         query.setUsername("1");
         query.setPageSize(5);
         Page<Employee> page = employeeRepository.findPageByQuery(query);
         page.forEach(e-> System.out.println(e));
     }
}