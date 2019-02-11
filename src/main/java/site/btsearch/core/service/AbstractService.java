package site.btsearch.core.service;

import site.btsearch.core.dao.Conn;
import site.btsearch.core.interfaces.MyBatisQuery;

import java.util.List;
import java.util.Map;

public abstract class AbstractService<T> extends Conn implements MyBatisQuery<T> {


    public Map queryMap(String Sql, Object object) throws Exception {
        if(object == null){
            return jdbcTemplate.queryForMap(Sql);
        }
        return jdbcTemplate.queryForMap(Sql,object);
    }

    public List queryList(String Sql, Object object) throws Exception {
        if(object == null){
            return jdbcTemplate.queryForList(Sql);
        }
        return jdbcTemplate.queryForList(Sql,object);
    }



}
