package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.query.PurchasebillitemQuery;
import cn.xiaji.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Purchasebillitem> page(PurchasebillitemQuery query) {
        Page page = purchasebillitemService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editPurchasebillitem")
    public Purchasebillitem beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Purchasebillitem purchasebillitem = purchasebillitemService.findOne(id);
            //解决n-to-n的问题
            return purchasebillitem;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            purchasebillitemService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchasebillitem") Purchasebillitem purchasebillitem) {
        try {
            purchasebillitemService.save(purchasebillitem);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Purchasebillitem purchasebillitem) {
        try {
            purchasebillitemService.save(purchasebillitem);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}