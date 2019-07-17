package cn.xiaji.web.controller;

import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Product;
import cn.xiaji.query.ProductQuery;
import cn.xiaji.service.IProductService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/index")
    public String index() {
        return "product/product";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Product> page(ProductQuery query) {
        Page page = productService.findPageByQuery(query);
        return new UIPage(page);
    }


    //图片上传功能
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmssS");

    public void uploadImage(Product product, MultipartFile productImage, HttpServletRequest req) {
        if (productImage != null) {
            String webapp = req.getServletContext().getRealPath("/");
            //图片存在就把它给删除掉
    /*
    if (product.getId() != null && StringUtils.isNotBlank(product.getPic())) {
        File deleteFile = new File(webapp, product.getPic());
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
        File deleteFile2 = new File(webapp, product.getSmallPic());
        if (deleteFile2.exists()) {
            deleteFile2.delete();
        }
    }
   */
            try {
                // 上传文件命名,拷贝到webapp的位置,设置productImage到product
                Date date = new Date();
                // 大小图的路径+文件名称
                String fileName = "/upload/" + sdf.format(date) + ".png";
                String smallFileName = "/upload/" + sdf.format(date) + "_small.png";
                // 大小图的在服务器上面的物理路径
                File destFile = new File(webapp, fileName);
                File smallDestFile = new File(webapp, smallFileName);

                // 生成upload目录
                File parentFile = destFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();// 自动生成upload目录
                }
                // 把上传的临时图片，复制到当前项目的webapp路径
                //FileUtils.copyFile(upload, destFile);
                FileCopyUtils.copy(productImage.getInputStream(), new FileOutputStream(destFile));
                // 处理缩略图
                //Thumbnails.of(upload).scale(0.1F).toFile(smallDestFile);
                Thumbnails.of(productImage.getInputStream()).scale(0.1F).toFile(smallDestFile);
                // 把大小图的相对webapp的路径设置到数据库产品表里面
                product.setPic(fileName);
                product.setSmallPic(smallFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //添加或修改
    private JsonResult saveOrUpdate(Product product, HttpServletRequest req){
        //下面是解决上传文件为空报错的问题
        MultipartFile productImage = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart){
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(req, MultipartHttpServletRequest.class);
            productImage = multipartRequest.getFile("productImage");
        }
        uploadImage(product, productImage, req);

        try {
            productService.save(product);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editProduct")
    public Product beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Product product = productService.findOne(id);
            //解决n-to-n的问题
            product.setTypes(null);
            product.setUnit(null);
            product.setBrand(null);
            return product;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id, HttpServletRequest req) {
        try {
            Product product = productService.findOne(id);
            productService.delete(id);
            // 删除图片
            String webapp = req.getServletContext().getRealPath("/");
            if (id != null && StringUtils.isNotBlank(product.getPic())) {
                File deleteFile = new File(webapp, product.getPic());
                if (deleteFile.exists()) {
                    deleteFile.delete();
                }
                File deleteSmallFile = new File(webapp, product.getSmallPic());
                if (deleteSmallFile.exists()) {
                    deleteSmallFile.delete();
                }
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editProduct") Product product, HttpServletRequest req) {
        return saveOrUpdate(product, req);
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Product product, HttpServletRequest req) {
        return saveOrUpdate(product, req);
    }
}