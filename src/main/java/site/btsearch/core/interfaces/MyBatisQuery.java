package site.btsearch.core.interfaces;

import java.util.List;
import java.util.Map;

public interface MyBatisQuery<T> extends JdbcQuery{

    public Map queryMapByXml(T template, String sqlid, Object paramter) throws Exception;

    public Map queryMapFromListByXml(T template, String sqlid, Object paramter, String key) throws Exception;

    public List queryListByXml(T template, String sqlid, Object paramter) throws Exception;

}
