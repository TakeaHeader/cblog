package site.btsearch.core.view;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
import site.btsearch.core.tools.Assert;

import java.util.Locale;


/***
 * viewName default is "JsonView",that is site.btsearch.JsonView
 * view is order to this viewName can not get bean for default view
 * order is defualt Integer.MAX_VALUE,you can change to order resolver
 *
 */
public class JsonViewResolver extends AbstractCachingViewResolver implements Ordered {

    private final String viewName = "JSON";

    private View view = null;

    private int order = Integer.MAX_VALUE ;

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public void setView(View view){
        this.view = view;
    }

    /**
     *
     * @param viewName  viewName must not be Null,if Null throw NullPointException
     * @param locale  ignore
     * @return  if viewName.equals(viewName) is true , return JsonView ,false return null
     * else this ViewResolver is not supported!
     * @throws Exception
     */
    protected View loadView(String viewName, Locale locale) throws Exception {
        Assert.isNull(viewName);
        View returnView = null;
        if(viewName.equals(this.viewName)){
            returnView =  (View) getApplicationContext().getBean(viewName);
        }
        if(returnView == null && this.view != null){
            returnView = this.view;
        }
        return returnView;
    }
}
