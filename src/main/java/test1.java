///**
// * Copyright (C), 2015-2020, XXX有限公司
// * FileName: test1
// * Author:   Administrator
// * Date:     2020/02/16 12:12
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// */
//
//import Annotation_Collection.RouteMap.RouteMapping;
//import Annotation_Collection.Sql.Dao;
////import MyService.impl.exampleimpl;
//import sun.misc.ProxyGenerator;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author Administrator
// * @create 2020/02/16
// * @since 1.0.0
// */
//@Dao("test1")
//@RouteMapping("/test")
//public class test1 {
//    public double a = 10;
//    public char b;
//    public long c;
//    public short d;
//    public byte e;
//    public boolean f;
//    public String g;
//    public int h;
//    public float i;
//    public test1(){}
//    public double getA() {
//        return a;
//    }
//
//    public void setA(double a) {
//        this.a = a;
//    }
//
//    public char getB() {
//        return b;
//    }
//
//    public void setB(char b) {
//        this.b = b;
//    }
//
//    public long getC() {
//        return c;
//    }
//
//    public void setC(long c) {
//        this.c = c;
//    }
//
//    public short getD() {
//        return d;
//    }
//
//    public void setD(short d) {
//        this.d = d;
//    }
//
//    public byte getE() {
//        return e;
//    }
//
//    public void setE(byte e) {
//        this.e = e;
//    }
//
//    public boolean isF() {
//        return f;
//    }
//
//    public void setF(boolean f) {
//        this.f = f;
//    }
//
//    public String getG() {
//        return g;
//    }
//
//    public void setG(String g) {
//        this.g = g;
//    }
//
//    public int getH() {
//        return h;
//    }
//
//    public void setH(int h) {
//        this.h = h;
//    }
//
//    public float getI() {
//        return i;
//    }
//
//    public void setI(float i) {
//        this.i = i;
//    }
//    public static void testProxyGenetate() {
//        byte[] newProxyClass = ProxyGenerator.generateProxyClass("$Proxy0", exampleimpl.class.getInterfaces());
//        System.out.println(newProxyClass);
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream(new File("E:\\java\\First_Frame_work\\src\\$Proxy0.class"));
//            try {
//                fileOutputStream.write(newProxyClass);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (fileOutputStream != null) {
//                    try {
//                        fileOutputStream.flush();
//                        fileOutputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
