package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;import java.sql.Timestamp;
import Annotation_Collection.Sql.*;@Dao("items")
public class items{public Timestamp createtime;
public Timestamp getcreatetime(){
return createtime;}
public void setcreatetime(Timestamp args){this.createtime=args;}
public float price;
public float getprice(){
return price;}
public void setprice(float args){this.price=args;}
public String name;
public String getname(){
return name;}
public void setname(String args){this.name=args;}
public long id;
public long getid(){
return id;}
public void setid(long args){this.id=args;}
public String detail;
public String getdetail(){
return detail;}
public void setdetail(String args){this.detail=args;}
public String pic;
public String getpic(){
return pic;}
public void setpic(String args){this.pic=args;}
}