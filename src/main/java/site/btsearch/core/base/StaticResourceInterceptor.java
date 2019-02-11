package site.btsearch.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StaticResourceInterceptor {

    public boolean match(HttpServletRequest request, HttpServletResponse response);

}
