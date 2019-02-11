package site.btsearch.core.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conn {

//    mybatis 数据源
    @Autowired
    protected SqlSessionTemplate Template;

    @Autowired
    protected JdbcTemplate jdbcTemplate;




}
