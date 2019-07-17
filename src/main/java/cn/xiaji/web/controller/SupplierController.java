package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Supplier;
import cn.xiaji.query.SupplierQuery;
import cn.xiaji.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("/index")
    public String index() {
        return "supplier/supplier";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Supplier> page(SupplierQuery query) {
        Page page = supplierService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editSupplier")
    public Supplier beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Supplier supplier = supplierService.findOne(id);
            //解决n-to-n的问题
            return supplier;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            supplierService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSupplier") Supplier supplier) {
        try {
            supplierService.save(supplier);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Supplier supplier) {
        try {
            supplierService.save(supplier);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}