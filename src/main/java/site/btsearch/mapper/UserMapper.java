package site.btsearch.mapper;

import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public interface UserMapper {

    public Map selectUser(String username, String password);



}
