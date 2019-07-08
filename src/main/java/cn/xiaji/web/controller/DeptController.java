package cn.xiaji.web.controller;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Dept;
import cn.xiaji.query.DeptQuery;
import cn.xiaji.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @RequestMapping("/index")
    public String index() {
        return "dept/dept";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Dept> page(DeptQuery query) {
        Page page = deptService.findPageByQuery(query);
        return new UIPage(page);
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editDept")
    public Dept beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Dept dept = deptService.findOne(id);
            //解决n-to-n的问题
            return dept;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            deptService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editDept") Dept dept) {
        try {
            deptService.save(dept);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Dept dept) {
        try {
            deptService.save(dept);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}