package cn.xiaji.service.Impl;

import java.util.Date;

import cn.xiaji.common.UserContext;
import cn.xiaji.domain.Employee;
import cn.xiaji.domain.Purchasebill;
import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.repository.PurchasebillRepository;
import cn.xiaji.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill, Long> implements IPurchasebillService {
    @Autowired
    private PurchasebillRepository purchasebillRepository;

    @Override
    public void save(Purchasebill purchasebill) {
        if (purchasebill.getId() == null) {
            //添加前放入 录入员为当前用户
            Employee user = UserContext.getUser();
            purchasebill.setInputUser(user);
        }
        super.save(purchasebill);
    }
}