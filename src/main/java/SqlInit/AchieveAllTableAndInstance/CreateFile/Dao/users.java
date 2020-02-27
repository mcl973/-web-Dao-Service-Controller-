package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;

import Annotation_Collection.Sql.Dao;

@Dao("users")
public class users{public String birthday;
public String getbirthday(){
return birthday;}
public void setbirthday(String args){this.birthday=args;}
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