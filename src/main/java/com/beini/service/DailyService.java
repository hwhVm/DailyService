package com.beini.service;


import com.beini.bean.DailyBean;
import com.beini.bean.DailyPageBean;


/**
 * Created by beini on 2017/2/23.
 */
public interface DailyService {

    int insertDaily(DailyBean dailyBean);

    DailyPageBean queryDailyByNum(DailyPageBean dailyPageBean);

    int queryDailyCount();

    void updataDaily(DailyBean dailyBean);

    void deteleDaily(int id);
}
