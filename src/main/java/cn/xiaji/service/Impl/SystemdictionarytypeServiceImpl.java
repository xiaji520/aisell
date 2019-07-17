package cn.xiaji.service.Impl;
import cn.xiaji.domain.Systemdictionarytype;
import cn.xiaji.repository.SystemdictionarytypeRepository;
import cn.xiaji.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype,Long> implements ISystemdictionarytypeService {
    @Autowired
    private SystemdictionarytypeRepository systemdictionarytypeRepository;
}