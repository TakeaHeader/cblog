package site.btsearch.service;

import site.btsearch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Service
@RequestMapping("/User")
public class UserService {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/login")
    public Map login(){


        return null;
    }



}
