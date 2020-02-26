package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;

import Annotation_Collection.Sql.Dao;

@Dao("orderdetail")
public class orderdetail{public long orders_id;
public long getorders_id(){
return orders_id;}
public void setorders_id(long args){this.orders_id=args;}
public long items_id;
public long getitems_id(){
return items_id;}
public void setitems_id(long args){this.items_id=args;}
public long items_num;
public long getitems_num(){
return items_num;}
public void setitems_num(long args){this.items_num=args;}
public long id;
public long getid(){
return id;}
public void setid(long args){this.id=args;}
}