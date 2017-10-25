package com.beini.service.impl;

import com.beini.bean.DailyBean;
import com.beini.mapper.DailyMapper;
import com.beini.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by beini on 2017/2/23.
 */
@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyMapper dailyMapper;


    @Override
    public int insertDaily(DailyBean dailyBean) {
        return dailyMapper.inserDaily(dailyBean);
    }

}
