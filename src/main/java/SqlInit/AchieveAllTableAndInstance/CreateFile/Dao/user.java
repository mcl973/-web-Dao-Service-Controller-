package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;import java.sql.Date;
import Annotation_Collection.Sql.*;@Dao("user")
public class user{public Date birthday;
public Date getbirthday(){
return birthday;}
public void setbirthday(Date args){this.birthday=args;}
public String address;
public String getaddress(){
return address;}
public void setaddress(String args){this.address=args;}
public String sex;
public String getsex(){
return sex;}
public void setsex(String args){this.sex=args;}
public long id;
public long getid(){
return id;}
public void setid(long args){this.id=args;}
public String username;
public String getusername(){
return username;}
public void setusername(String args){this.username=args;}
}