import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

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


        get("/get/*", ((request, response) -> {
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

        get("/pic/*", (((request, response) -> {
            response.header("Content-Transfer-Encoding", "binary");
            response.header("Content-Disposition", "attachment; filename=\"2.jpg\"");
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("2.jpg"));
                HttpServletResponse raw = response.raw();
                raw.getOutputStream().write(bytes);
                raw.getOutputStream().flush();
                raw.getOutputStream().close();
            } catch (Exception e) {
                response.status(404);
            }
            response.status(200);
            System.out.println("Get picture");
            return response.raw();
        })));
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
