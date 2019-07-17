package cn.xiaji.service;

import cn.xiaji.domain.Systemdictionarydetail;

import java.util.List;

public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail, Long> {
    List<Systemdictionarydetail> findAllUnit();
    List<Systemdictionarydetail> findAllBrand();
}