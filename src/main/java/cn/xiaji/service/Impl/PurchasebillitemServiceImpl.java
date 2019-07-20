package cn.xiaji.service.Impl;

import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.domain.vo.PurchaseBillItemChartVo;
import cn.xiaji.domain.vo.PurchaseBillItemVo;
import cn.xiaji.query.PurchasebillitemQuery;
import cn.xiaji.repository.PurchasebillitemRepository;
import cn.xiaji.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem, Long> implements IPurchasebillitemService {
    @Autowired
    private PurchasebillitemRepository purchasebillitemRepository;
    @Override
    public List<PurchaseBillItemVo> findItems(PurchasebillitemQuery query) {
        //获取所有数据
        List<Purchasebillitem> items = purchasebillitemRepository.findByQuery(query);
        //准备vo集合
        List<PurchaseBillItemVo> vos = new ArrayList<>();
        for (Purchasebillitem item : items) {
            PurchaseBillItemVo vo = new PurchaseBillItemVo(item,query);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<PurchaseBillItemChartVo> findCharts(PurchasebillitemQuery query) {
        //获取分组条件
        String groupByStr = query.getGroupByStr();
        //获取jpql
        String whereJpql = query.getWhereJpql();
        //获取jpql中?对应值
        List<Object> params = query.getParams();
        //拼接jpql
        String jpql = "select new cn.xiaji.domain.vo.PurchaseBillItemChartVo(" + groupByStr + ",sum(amount)) from Purchasebillitem o "
                + whereJpql + " group by " + groupByStr;
        //如果获取单独的一些值，那么就会变成一个Object数组
        List<PurchaseBillItemChartVo> vos = super.findByJpql(jpql, params.toArray());
        return vos;
    }
}