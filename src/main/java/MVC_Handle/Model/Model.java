/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Model
 * Author:   Administrator
 * Date:     2020/02/21 9:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MVC_Handle.Model;

import AllConfigure.HtmlBasePath.HtmlBasePath;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public class Model implements HtmlBasePath {
    Map<String,Object> model = new HashMap<>();
    String url;
    //废弃
    String redircturl ;

    public void setModel(String name,Object object){
        model.put(name,object);
    }
    public Map<String,Object> getModel(){
        return model;
    }
    public Object getModelValue(String name){
        return model.get(name);
    }
    public Set<String> getModelNames(){
        return model.keySet();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = BaseUrlPath+url+HouZhuiMing;
//        setRedircturl(url);
    }
    //废弃
    public String getRedircturl() {
        return redircturl;
    }
    //废弃
    public void setRedircturl(String redircturl) {
        this.redircturl = redircturl;
    }
}
