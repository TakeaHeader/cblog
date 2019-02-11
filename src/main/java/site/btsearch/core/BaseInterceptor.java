package site.btsearch.core;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;
import site.btsearch.core.exception.OperationException;
import site.btsearch.core.tools.Constant;
import site.btsearch.core.tools.JsonUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseInterceptor implements HandlerInterceptor,ApplicationContextAware {

    protected final Logger logger = Logger.getLogger(getClass());

    private final String DATATYPE = "type";

    protected ApplicationContext context;

    protected final UrlPathHelper pathHelper = new UrlPathHelper();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public boolean preHandle(HttpServletRequest Request, HttpServletResponse Response, Object o) throws Exception {
        if(!checkIP(Request)){
            Response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(o instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)o;
            Method method = handlerMethod.getMethod();
            Class type = method.getReturnType();
            if(type.isAssignableFrom(Map.class) || type.isAssignableFrom(List.class) || type.isAssignableFrom(Collection.class)){
                modelAndView.setViewName(Constant.JSONVIEW);
            }
        }
        this.DeteminedView(modelAndView);
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if(e != null){
            Error(httpServletRequest,httpServletResponse,o,e);
        }

    }

    /**
     *you can add a datatype to set datatype ,default is Constant.RESOURCEVIEW
     * @param mav
     */
    protected void DeteminedView(ModelAndView mav){
        if(mav != null){
            Map<String,Object> model = mav.getModel();
            String ViewName = model.get(DATATYPE) == null ? null: model.get(DATATYPE).toString();
            if(ViewName != null){
                mav.setViewName(ViewName);
                model.remove(DATATYPE);
                logger.debug("Set ViewName >>> " + ViewName);
            }
        }
    }

    public boolean checkIP(HttpServletRequest Request){
        /*String IP  = Request.getHeader("x-forwarded-for") == null? Request.getRemoteAddr(): Request.getHeader("x-forwarded-for");
        logger.debug("Check IP:"+IP);
        return pubService.IsNotAllowIP(IP);*/
        return true;
    }


    protected void Error(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.debug(e);
        if(e instanceof OperationException){
            printError(httpServletResponse,e);
        }else{
            this.dealUnHandlerError(httpServletResponse,e);
        }
    }


    private void dealUnHandlerError(HttpServletResponse httpServletResponse, Exception e){
//        服务器抛出的错误,向外只提示服务器发生错误, 给出友好提示,并写入错误到日志表
        logger.error(e);
        Exception ex  = new RuntimeException("服务器发生错误!");
        printError(httpServletResponse,ex);
    }

    private void printError(HttpServletResponse httpServletResponse, Exception e){
        Map result = new HashMap();
        result.put("code",-1);
        result.put("msg",e.getMessage());
        ServletOutputStream out = null;
        try{
            httpServletResponse.setContentType("application/json;charset=utf-8");
            out = httpServletResponse.getOutputStream();
            byte [] data = JsonUtil.toByteArray(result);
            out.write(data,0,data.length);
            out.flush();
            out.close();
        }catch (Exception ex){
            this.dealUnHandlerError(httpServletResponse,ex);
        }finally {
            if(out != null){
                try{
                    out.close();
                }catch (IOException e1){
                    logger.error(e1);
                }
            }
        }
    }

}
