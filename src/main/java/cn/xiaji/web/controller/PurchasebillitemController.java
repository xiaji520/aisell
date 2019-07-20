package cn.xiaji.web.controller;

import cn.xiaji.domain.vo.PurchaseBillItemChartVo;
import cn.xiaji.domain.vo.PurchaseBillItemVo;
import cn.xiaji.query.PurchasebillitemQuery;
import cn.xiaji.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController extends BaseController {

    @Autowired
    private IPurchasebillitemService purchasebillitemService;

    @RequestMapping("/index")
    public String index() {
        return "purchasebillitem/purchasebillitem";
    }

    @RequestMapping("/findItems")
    @ResponseBody
    public List<PurchaseBillItemVo> findItems(PurchasebillitemQuery query) {
        return purchasebillitemService.findItems(query);
    }

    @RequestMapping("/findCharts")
    @ResponseBody
    public List<PurchaseBillItemChartVo> findCharts(PurchasebillitemQuery query) {
        return purchasebillitemService.findCharts(query);
    }
}