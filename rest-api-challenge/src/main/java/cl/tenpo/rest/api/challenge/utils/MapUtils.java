package cl.tenpo.rest.api.challenge.utils;

import java.util.Map;

public class MapUtils {

    private MapUtils(){
        throw new IllegalStateException("Utility class");
    }

    public static String convertWithIteration(Map<String, String[]> map) {
        StringBuilder mapAsString = new StringBuilder("");
        if (!map.isEmpty()) {
            for (Map.Entry<String,String[]> entry : map.entrySet()) {
                mapAsString.append(entry.getKey() + "=" + entry.getValue()[0] + ", ");
            }
            mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("");
        }
        return mapAsString.toString();

    }
}
