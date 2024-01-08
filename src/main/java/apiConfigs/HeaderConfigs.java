package apiConfigs;

import java.util.HashMap;
import java.util.Map;


import utils.FileandEnv;

public class HeaderConfigs {
	
	String key= FileandEnv.envAndFile().get("key");
	String token= FileandEnv.envAndFile().get("token");

    public Map<String,String> defaultHeaders(){

        Map<String,String> defaultHeaders = new HashMap<String,String>();
        defaultHeaders.put("key", key);
        defaultHeaders.put("token",token);

        return defaultHeaders;

    }
    public Map<String,Object> headersWithToken(String name){

        Map<String,Object> defaultHeaders = new HashMap<String,Object>();
        defaultHeaders.put("key",key);
        defaultHeaders.put("token",token);
        defaultHeaders.put("name",name);
        Map<String,String> newMap =new HashMap<String,String>();
        for (Map.Entry<String, Object> entry : defaultHeaders.entrySet()) {
            if(entry.getValue() instanceof String){
                newMap.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return defaultHeaders;

    }
    public Map<String,Object> headerForCard(String id){

        Map<String,Object> defaultHeaders = new HashMap<String,Object>();
        defaultHeaders.put("key",key);
        defaultHeaders.put("token",token);
        defaultHeaders.put("idList",id);

        Map<String,String> newMap =new HashMap<String,String>();
        for (Map.Entry<String, Object> entry : defaultHeaders.entrySet()) {
            if(entry.getValue() instanceof String){
                newMap.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return defaultHeaders;

    }
}