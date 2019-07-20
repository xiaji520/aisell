package cn.xiaji.service;

import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.domain.vo.PurchaseBillItemChartVo;
import cn.xiaji.domain.vo.PurchaseBillItemVo;
import cn.xiaji.query.PurchasebillitemQuery;

import java.util.List;

public interface IPurchasebillitemService extends IBaseService<Purchasebillitem, Long> {
    //获取表格数据
    List<PurchaseBillItemVo> findItems(PurchasebillitemQuery query);

    //获取报表数据
    List<PurchaseBillItemChartVo> findCharts(PurchasebillitemQuery query);

}