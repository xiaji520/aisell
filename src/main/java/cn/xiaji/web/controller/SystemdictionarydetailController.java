package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Systemdictionarydetail;
import cn.xiaji.query.SystemdictionarydetailQuery;
import cn.xiaji.service.ISystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/systemdictionarydetail")
public class SystemdictionarydetailController extends BaseController {

    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    @RequestMapping("/index")
    public String index() {
        return "systemdictionarydetail/systemdictionarydetail";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Systemdictionarydetail> page(SystemdictionarydetailQuery query) {
        Page page = systemdictionarydetailService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editSystemdictionarydetail")
    public Systemdictionarydetail beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Systemdictionarydetail systemdictionarydetail = systemdictionarydetailService.findOne(id);
            //解决n-to-n的问题
            return systemdictionarydetail;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            systemdictionarydetailService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSystemdictionarydetail") Systemdictionarydetail systemdictionarydetail) {
        try {
            systemdictionarydetailService.save(systemdictionarydetail);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Systemdictionarydetail systemdictionarydetail) {
        try {
            systemdictionarydetailService.save(systemdictionarydetail);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}