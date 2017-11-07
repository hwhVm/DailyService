package com.beini.switchsource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beini on 2017/11/7.
 */
public class ChooseDataSource extends AbstractRoutingDataSource {

    static Map<String, List<String>> METHOD_TYPE_MAP = new HashMap<String, List<String>>();

    protected Object determineCurrentLookupKey() {
        return DataSourceHandler.getDataSource();
    }

    // 设置方法名前缀对应的数据源
    public void setMethodType(Map<String, String> map) {
        for (String key : map.keySet()) {
            List<String> v = new ArrayList<String>();
            String[] types = map.get(key).split(",");
            for (String type : types) {
                if (!StringUtils.isEmpty(type)) {
                    v.add(type);
                }
            }
            METHOD_TYPE_MAP.put(key, v);
        }
    }
}
