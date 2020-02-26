package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;

import Annotation_Collection.Sql.Dao;

@Dao("person")
public class person{public String birthday;
public String getbirthday(){
return birthday;}
public void setbirthday(String args){this.birthday=args;}
public long sex;
public long getsex(){
return sex;}
public void setsex(long args){this.sex=args;}
public long personid;
public long getpersonid(){
return personid;}
public void setpersonid(long args){this.personid=args;}
public long persontohome;
public long getpersontohome(){
return persontohome;}
public void setpersontohome(long args){this.persontohome=args;}
public String personame;
public String getpersoname(){
return personame;}
public void setpersoname(String args){this.personame=args;}
public byte[] picture;
public byte[] getpicture(){
return picture;}
public void setpicture(byte[] args){this.picture=args;}
}