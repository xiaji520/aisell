package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Producttype;
import cn.xiaji.query.ProducttypeQuery;
import cn.xiaji.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/producttype")
public class ProducttypeController extends BaseController {

    @Autowired
    private IProducttypeService producttypeService;

    @RequestMapping("/index")
    public String index() {
        return "producttype/producttype";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Producttype> page(ProducttypeQuery query) {
        Page page = producttypeService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editProducttype")
    public Producttype beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Producttype producttype = producttypeService.findOne(id);
            //解决n-to-n的问题
            return producttype;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            producttypeService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editProducttype") Producttype producttype) {
        try {
            producttypeService.save(producttype);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Producttype producttype) {
        try {
            producttypeService.save(producttype);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}