package cn.xiaji.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemdictionarydetailServiceTest {
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    @Test
    public void testFindAll()throws Exception{
        systemdictionarydetailService.findAll().forEach(e-> System.out.println(e));
    }
}