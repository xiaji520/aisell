package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Menu;
import cn.xiaji.query.MenuQuery;
import cn.xiaji.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/index")
    public String index() {
        return "menu/menu";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Menu> page(MenuQuery query) {
        Page page = menuService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editMenu")
    public Menu beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Menu menu = menuService.findOne(id);
            //解决n-to-n的问题
            return menu;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            menuService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editMenu") Menu menu) {
        try {
            menuService.save(menu);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Menu menu) {
        try {
            menuService.save(menu);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}