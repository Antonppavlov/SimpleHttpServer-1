import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static spark.Spark.*;

/**
 * Created by petho on 2017-03-21.
 */
public class Main {

    public static void main(String [] args){
        port(7676);

        post("/*", ((request, response) -> {
            JsonObject json = new JsonObject();
            json.addProperty("status","Success");
            json.addProperty("method","Post");
            json.addProperty("body",request.body());
            json.addProperty("params",getMapString( request.queryMap().toMap()));
            json.addProperty("session",request.session().toString());
            json.addProperty("cookies",request.cookies().toString());
            System.out.println("Post");
            return json.toString();
        }));


        get("/*",((request, response) ->{
            JsonObject json = new JsonObject();
            json.addProperty("status","Success");
            json.addProperty("method","Get");
            json.addProperty("params",getMapString( request.queryMap().toMap()));
            json.addProperty("session",request.session().toString());
            json.addProperty("cookies",request.cookies().toString());
            System.out.println("Get");
            return json.toString();
        }));

        put("/*",((request, response) ->{
            JsonObject json = new JsonObject();
            json.addProperty("status","Success");
            json.addProperty("method","Put");
            json.addProperty("params",getMapString( request.queryMap().toMap()));
            json.addProperty("session",request.session().toString());
            json.addProperty("cookies",request.cookies().toString());
            System.out.println("Put");
            return json.toString();
        }));
    }

    private static String getMapString(Map<String, String[]> map){
        StringBuilder sb = new StringBuilder();
        for(String key : map.keySet()){
            sb.append(key).append(":");
            for(String str : map.get(key)){
                sb.append(str);
            }
        }
        return sb.toString();
    }

}
