package cn.xiaji.service.Impl;
import cn.xiaji.domain.Resource;
import cn.xiaji.repository.ResourceRepository;
import cn.xiaji.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource,Long> implements IResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
}