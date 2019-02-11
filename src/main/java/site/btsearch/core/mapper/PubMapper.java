package site.btsearch.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PubMapper {

    public List<String> queryIP(String IP);

    public int insertLog(@Param("Message") String Message, @Param("DateTime") String DateTime);

}
