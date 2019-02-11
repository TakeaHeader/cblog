package site.btsearch.core.view;

import org.springframework.web.servlet.view.AbstractView;
import site.btsearch.core.tools.JsonUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/***
 * JSON视图,返回JSON数据
 */

public class JsonView extends AbstractView {

    protected final String ContentType = "application/json;charset=utf-8";

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(ContentType);
        ServletOutputStream out = response.getOutputStream();
        byte [] data = JsonUtil.toByteArray(model);
        out.write(data,0,data.length);
        out.flush();
        out.close();
    }

}
