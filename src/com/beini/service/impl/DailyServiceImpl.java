package com.beini.service.impl;

import com.beini.bean.DailyBean;
import com.beini.bean.DailyPageBean;
import com.beini.mapper.DailyMapper;
import com.beini.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by beini on 2017/2/23.
 */
@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyMapper dailyMapper;

    private int sumCount;

    @Override
    public int insertDaily(DailyBean dailyBean) {
        return dailyMapper.inserDaily(dailyBean);
    }

    @Override
    public DailyPageBean queryDailyByNum(DailyPageBean dailyPageBean) {
        List<DailyBean> dailyBeans = dailyMapper.queryDailyByNum(dailyPageBean.getCurrentPage(), dailyPageBean.getPageSize());
        dailyPageBean.setDailyBeans(dailyBeans);
//      dailyPageBean.setDailyCount(getDailyCount());
        return dailyPageBean;
    }

    @Override
    public int queryDailyCount() {
        return dailyMapper.queryDailyCount();
    }


}
