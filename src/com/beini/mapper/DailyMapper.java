package com.beini.mapper;

import com.beini.bean.DailyBean;
import org.apache.ibatis.annotations.*;


/**
 * Created by beini on 2017/2/22.
 */

public interface DailyMapper {

    @Insert("insert into daily(title,date,content,author,picUrl) values(#{title},#{date},#{content},#{author},#{picUrl});")
    int inserUser(DailyBean dailyBean);
}
