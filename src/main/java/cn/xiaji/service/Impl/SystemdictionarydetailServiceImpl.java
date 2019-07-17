package cn.xiaji.service.Impl;

import cn.xiaji.domain.Systemdictionarydetail;
import cn.xiaji.domain.Systemdictionarytype;
import cn.xiaji.repository.SystemdictionarydetailRepository;
import cn.xiaji.service.ISystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail, Long> implements ISystemdictionarydetailService {
    @Autowired
    private SystemdictionarydetailRepository systemdictionarydetailRepository;

    @Override
    public List<Systemdictionarydetail> findAllUnit() {
        return systemdictionarydetailRepository.findAllBySn(Systemdictionarytype.PRODUCT_UNIT);
    }

    @Override
    public List<Systemdictionarydetail> findAllBrand() {
        return systemdictionarydetailRepository.findAllBySn(Systemdictionarytype.PRODUCT_BRAND);
    }
}