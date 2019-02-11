package site.btsearch.core.interfaces;

import java.util.List;
import java.util.Map;

public interface JdbcQuery extends Query{

    public Map queryMap(String Sql, Object object) throws Exception;

    public List queryList(String Sql, Object object) throws Exception;

}
