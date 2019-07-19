package cn.xiaji.web.controller;

import java.math.BigDecimal;
import java.util.Date;

import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Purchasebill;
import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.query.PurchasebillQuery;
import cn.xiaji.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController extends BaseController {

    @Autowired
    private IPurchasebillService purchasebillService;

    @RequestMapping("/index")
    public String index() {
        return "purchasebill/purchasebill";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Purchasebill> page(PurchasebillQuery query) {
        Page page = purchasebillService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editPurchasebill")
    public Purchasebill beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Purchasebill purchasebill = purchasebillService.findOne(id);
            //解决n-to-n的问题
            purchasebill.setSupplier(null);
            purchasebill.setBuyer(null);
            purchasebill.getItems().clear();
            return purchasebill;
        }
        return null;
    }

    public JsonResult saveOrUpdate(Purchasebill purchasebill) {
        //拿到订单明细
        List<Purchasebillitem> items = purchasebill.getItems();
        //准备总数量与总金额
        BigDecimal totalNum = new BigDecimal("0");
        BigDecimal totalAmount = new BigDecimal("0");
        for (Purchasebillitem item : items) {
            //明细对应的订单
            item.setBill(purchasebill);
            //计算当前数据的小计
            BigDecimal amount = item.getPrice().multiply(item.getNum());
            item.setAmount(amount);
            //计算总数量与总金额
            totalNum = totalNum.add(item.getNum());
            totalAmount = totalAmount.add(item.getAmount());
        }
        //设置总数量与总金额
        purchasebill.setTotalNum(totalNum);
        purchasebill.setTotalAmount(totalAmount);
        try {
            purchasebillService.save(purchasebill);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            purchasebillService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchasebill") Purchasebill purchasebill) {
        return saveOrUpdate(purchasebill);
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Purchasebill purchasebill) {
        return saveOrUpdate(purchasebill);
    }
}