package MVC_Handle.HandleSevlet.HandlePost;

import javax.servlet.http.HttpServletRequest;

public interface PostMethods {
    //获取url，这个url在iocmap中就是具体的method
    public String achieveObject_key(HttpServletRequest req);
    public void handle();
}
