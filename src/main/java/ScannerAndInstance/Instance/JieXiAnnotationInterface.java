package ScannerAndInstance.Instance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public interface JieXiAnnotationInterface {
    public  String JiexiAnnotation(Class clazz);
    public String JiexiAnnotation(Method method);
    public String JiexiAnnotation(Field field);
    public String JiexiAnnotation(Parameter parameter);
}
