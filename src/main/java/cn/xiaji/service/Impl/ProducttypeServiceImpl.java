package cn.xiaji.service.Impl;
import cn.xiaji.domain.Producttype;
import cn.xiaji.repository.ProducttypeRepository;
import cn.xiaji.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducttypeServiceImpl extends BaseServiceImpl<Producttype,Long> implements IProducttypeService {
    @Autowired
    private ProducttypeRepository producttypeRepository;

    @Override
    public List<Producttype> findAllTypes() {
        return producttypeRepository.findAllTypes();
    }
}