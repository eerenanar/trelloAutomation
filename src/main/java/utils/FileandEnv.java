package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileandEnv {

    public static Map<String, String> fileandenv = new HashMap<String, String>();
    public static Properties propMain = new Properties();
    public static Properties propPreSet = new Properties();

    public static Map<String, String> envAndFile() {

        try {
                FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/inputs/config.properties");
                propMain.load(fisDev);
                fileandenv.put("BaseUrl", propMain.getProperty("BaseUrl"));
                fileandenv.put("key", propMain.getProperty("key"));
                fileandenv.put("token", propMain.getProperty("token"));

 
        } catch (Exception e) {
            // TODO: handle exception
        }

        return fileandenv;

    }
    public static Map<String, String> getConfigReader(){
        if(fileandenv == null) {
            fileandenv = envAndFile();
        }


        return fileandenv;

    }

}