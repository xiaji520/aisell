package cn.xiaji.service.Impl;
import cn.xiaji.common.UserContext;
import cn.xiaji.domain.Employee;
import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.repository.PurchasebillitemRepository;
import cn.xiaji.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem,Long> implements IPurchasebillitemService {
    @Autowired
    private PurchasebillitemRepository purchasebillitemRepository;
}