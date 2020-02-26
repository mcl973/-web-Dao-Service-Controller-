package MyService.Interface;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.mycontext;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.user;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.users;

import java.util.Map;

public interface example {
    public Map<String, md> show(String name);
    public Map<String, users> login(String name);
    public Map<String, mycontext> getAllContext(String name);
}
