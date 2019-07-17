package cn.xiaji.service.Impl;
import cn.xiaji.domain.Supplier;
import cn.xiaji.repository.SupplierRepository;
import cn.xiaji.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,Long> implements ISupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
}