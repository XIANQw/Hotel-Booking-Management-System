package jar.util;

public class ToJson {
    public static String toJson(String dataJson, String info, int status){
        if(dataJson.length() == 0) dataJson = "[]";
        String json = "{\"info\":\"" + info + "\", \"status\":" + status + ", \"data\":" + dataJson + "}" ;
        return json;
    }
}