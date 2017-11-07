package com.beini.switchsource;

/**
 * Created by beini on 2017/11/7.
 */
public class DataSourceHandler {
    // 数据源名称线程池
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 在项目启动的时候将配置的读、写数据源加到holder中
     */
    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }

    /**
     * 从holer中获取数据源字符串
     */
    public static String getDataSource() {
        return holder.get();
    }
}
