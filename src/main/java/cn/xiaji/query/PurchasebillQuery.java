package cn.xiaji.query;

import java.util.Date;

import cn.xiaji.domain.Purchasebill;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

public class PurchasebillQuery extends BaseQuery {
    //开始时间
    private Date beginDate;
    //结束时间
    private Date endDate;
    //状态
    private Integer status;

    @Override
    public Specification createSpec() {
        Date tempDate = null;
        if (endDate != null) {
            tempDate = DateUtils.addDays(endDate, 1);
        }
        Specification<Purchasebill> spec = Specifications.<Purchasebill>and()
                .ge(beginDate != null, "vdate", beginDate)
                //小于
                .lt(endDate != null, "vdate", tempDate)
                .eq(status != null, "status", status)
                .build();
        return spec;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}