package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;import java.sql.Timestamp;
import Annotation_Collection.Sql.*;@Dao("orders")
public class orders{public String number;
public String getnumber(){
return number;}
public void setnumber(String args){this.number=args;}
public String note;
public String getnote(){
return note;}
public void setnote(String args){this.note=args;}
public Timestamp createtime;
public Timestamp getcreatetime(){
return createtime;}
public void setcreatetime(Timestamp args){this.createtime=args;}
public long user_id;
public long getuser_id(){
return user_id;}
public void setuser_id(long args){this.user_id=args;}
public long id;
public long getid(){
return id;}
public void setid(long args){this.id=args;}
}