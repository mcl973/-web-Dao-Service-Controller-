package SqlInit.AchieveAllTableAndInstance;

import java.io.File;
import java.util.Map;

public interface Instance {
    public void instance(String classname, Map<String, Integer> fieldmap);
    public boolean savefile(File file, String context);
    public String getappth();

}
