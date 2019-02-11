package site.btsearch.core.interfaces;

public interface Update<T> extends Service{

    public int insert(T template, String sql, Object param) throws Exception;

    public int update(T template, String sql, Object param) throws Exception;

    public int insertByXml(T template, String sqlid, Object param) throws Exception;

    public int updateByXml(T template, String sqlid, Object param) throws Exception;

    public int delete() throws Exception;

}
