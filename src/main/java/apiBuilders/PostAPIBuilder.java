package apiBuilders;

import java.util.HashMap;
import java.util.Map;

import utils.FileandEnv;

public class PostAPIBuilder {
	String key= FileandEnv.envAndFile().get("key");
	String token= FileandEnv.envAndFile().get("token");

    public Map<String,String> postRequestBody(String name){

        Map<String,String> body = new HashMap<String,String>();

          body.put("key",key);
          body.put("token",token);
          body.put("name",name);

        return body;

    }
    public Map<String,String> getRequestBody(){

        Map<String,String> body = new HashMap<String,String>();

        body.put("key",key);
        body.put("token",token);

        return body;

    }

}