package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Systemdictionarytype;
import cn.xiaji.query.SystemdictionarytypeQuery;
import cn.xiaji.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/systemdictionarytype")
public class SystemdictionarytypeController extends BaseController {

    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

    @RequestMapping("/index")
    public String index() {
        return "systemdictionarytype/systemdictionarytype";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Systemdictionarytype> page(SystemdictionarytypeQuery query) {
        Page page = systemdictionarytypeService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editSystemdictionarytype")
    public Systemdictionarytype beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Systemdictionarytype systemdictionarytype = systemdictionarytypeService.findOne(id);
            //解决n-to-n的问题
            return systemdictionarytype;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            systemdictionarytypeService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSystemdictionarytype") Systemdictionarytype systemdictionarytype) {
        try {
            systemdictionarytypeService.save(systemdictionarytype);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Systemdictionarytype systemdictionarytype) {
        try {
            systemdictionarytypeService.save(systemdictionarytype);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}