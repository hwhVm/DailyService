package com.beini.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by beini on 2017/4/19.
 */
public class DailySql {
    private String tableName = "t_user";

    public String findAllSql() {
        return new SQL() {
            {
                SELECT("*");
                FROM(tableName);
            }
        }.toString();
    }

    public String findUserByIdSql(final int id) {
        return new SQL(){
            {
                SELECT("*");
                FROM(tableName);
                WHERE("user_id=" + id);

            }
        }.toString();
    }
}
