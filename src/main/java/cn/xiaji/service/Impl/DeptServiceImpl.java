package cn.xiaji.service.Impl;
import cn.xiaji.domain.Dept;
import cn.xiaji.repository.DeptRepository;
import cn.xiaji.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept,Long> implements IDeptService {
    @Autowired
    private DeptRepository deptRepository;
}