package site.btsearch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@Service
public class IndexService {


    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.sendRedirect("login/login.html");
    }


}
