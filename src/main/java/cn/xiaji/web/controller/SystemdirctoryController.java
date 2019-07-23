package cn.xiaji.web.controller;


import cn.xiaji.domain.Systemdictionarydetail;
import cn.xiaji.domain.Systemdictionarytype;
import cn.xiaji.service.ISystemdictionarydetailService;
import cn.xiaji.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SystemdirctoryController {
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;
    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

    @RequestMapping("/query")
    @ResponseBody
    public List<Systemdictionarytype> query() {
        return systemdictionarytypeService.findAll();
    }

    @RequestMapping("/systemdirctory")
    public String jump2() {
        return "systemdirctory";
    }

    @RequestMapping("/sn")
    @ResponseBody
    public List<Systemdictionarydetail> findbysn(String sn) {
        if (Systemdictionarytype.PRODUCT_BRAND.equals(sn)) {
            List<Systemdictionarydetail> brandBySn = systemdictionarydetailService.findAllBrand();
            return brandBySn;
        }
        if (Systemdictionarytype.PRODUCT_UNIT.equals(sn)) {
            List<Systemdictionarydetail> unitBySn = systemdictionarydetailService.findAllUnit();
            return unitBySn;
        }
        return null;
    }

}
