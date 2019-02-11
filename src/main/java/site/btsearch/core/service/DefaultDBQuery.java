package site.btsearch.core.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import site.btsearch.core.tools.Constant;

import java.util.List;
import java.util.Map;

@Service
public class DefaultDBQuery extends AbstractService<SqlSessionTemplate> {

    public Map queryMapByXml(SqlSessionTemplate template, String sqlid, Object paramter) throws Exception {
        return template.selectOne(sqlid,paramter);
    }

    public List queryListByXml(SqlSessionTemplate template, String sqlid, Object paramter) throws Exception {
        return template.selectList(sqlid,paramter);
    }

    public Map queryMapFromListByXml(SqlSessionTemplate template, String sqlid, Object paramter, String key) throws Exception {
        return template.selectMap(sqlid,paramter,key);
    }


    public String getServiceName() {
        return Constant.DBQuery;
    }




}
