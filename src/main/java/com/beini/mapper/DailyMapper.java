package com.beini.mapper;

import com.beini.bean.DailyBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by beini on 2017/2/22.
 */

public interface DailyMapper {

    @Insert("insert into daily(title,date,content,author,picUrl) values(#{title},#{date},#{content},#{author},#{picUrl});")
    int inserDaily(DailyBean dailyBean);

    @Select("select * from  daily limit #{start} ,  #{num} ")
    List<DailyBean> queryDailyByNum(@Param("start") int start, @Param("num") int num);

    @Select("select count(*) from daily")
    int queryDailyCount();
}
