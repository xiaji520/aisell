package cn.xiaji.service.Impl;
import cn.xiaji.domain.Product;
import cn.xiaji.repository.ProductRepository;
import cn.xiaji.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long> implements IProductService {
    @Autowired
    private ProductRepository productRepository;
}