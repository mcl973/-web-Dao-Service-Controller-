/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Models
 * Author:   Administrator
 * Date:     2020/02/26 16:04
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
 * @create 2020/02/26
 * @since 1.0.0
 */
public class Models implements HtmlBasePath {
    Map<String,Object[]> model = new HashMap<>();
    String url;
    //废弃
    String redircturl ;
    public boolean isModelNull(){
        return model.size() == 0 && (url==null || url.equals(""));
    }
    public boolean isMapModelNull(){
        return model.size() == 0;
    }
    public boolean isUrlNull(){
        return url == null || url.equals("");
    }
    public void setModel(String name,Object[] object){
        model.put(name,object);
    }

    public Map<String,Object[]> getModel(){
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
        //废弃
        this.url = BaseUrlPath+url+HouZhuiMing;
        //在用
        setRedircturl(url);
    }

    public String getRedircturl() {
        return HttpPath+redircturl+HouZhuiMing;
    }

    public void setRedircturl(String redircturl) {
        this.redircturl = redircturl;
    }
}
