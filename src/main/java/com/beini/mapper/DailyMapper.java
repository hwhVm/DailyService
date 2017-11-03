package com.beini.mapper;

import com.beini.bean.DailyBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by beini on 2017/2/22.
 */

public interface DailyMapper {

    @Insert("insert into daily(title,date,content,author,picUrl,user_id) values(#{title},#{date},#{content},#{author},#{picUrl},#{user_id});")
    int inserDaily(DailyBean dailyBean);

    @Select("select * from  daily where user_id=#{user_id}  limit #{start} ,#{num} ")
    List<DailyBean> queryDailyByNum(@Param("start") int start, @Param("num") int num,@Param("user_id")int user_id);

    @Select("select count(*) from daily")
    int queryDailyCount();

    @Update("UPDATE daily set title=#{title},date=#{date},content=#{content},author=#{author},picUrl=#{picUrl} where user_id=#{user_id}")
    void updataDaily(DailyBean dailyBean);

    @Delete("DELETE FROM daily where daily_id=#{daily_id}")
    void deteleDailyById(int daily_id);

}
