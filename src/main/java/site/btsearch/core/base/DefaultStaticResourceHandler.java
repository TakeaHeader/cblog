package site.btsearch.core.base;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultStaticResourceHandler implements HttpRequestHandler,ServletContextAware{

    @Value(value="${CONFIG.QXML}")
    private String QXML;

    @Value(value="${CONFIG.KZFW}")
    private boolean KZFW;

    private final String defaultServletName = "default";

    private final Logger logger = Logger.getLogger(DefaultStaticResourceHandler.class);

    private final UrlPathHelper pathHelper = new UrlPathHelper();

    private ServletContext servletContext;

    private StaticResourceInterceptor staticResourceInterceptor;

    private HttpServletRequest request;

    private HttpServletResponse response;

    public void setStaticResourceInterceptor(StaticResourceInterceptor staticResourceInterceptor) {
        this.staticResourceInterceptor = staticResourceInterceptor;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String url = pathHelper.getLookupPathForRequest(request);
        if(url.indexOf(QXML) != -1){
            if(!CheckResource()){
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"禁止访问");
                return;
            }
        }else{
            if(!KZFW){
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"禁止访问");
                return;
            }
        }
        RequestDispatcher rd = this.servletContext.getNamedDispatcher(this.defaultServletName);
        rd.forward(request,response);
    }

    public boolean CheckResource(){
        if(this.staticResourceInterceptor != null){
            return staticResourceInterceptor.match(request,response);
        }
        return true;
    }


}
