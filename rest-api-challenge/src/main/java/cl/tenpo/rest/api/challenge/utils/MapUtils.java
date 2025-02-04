package cl.tenpo.rest.api.challenge.utils;

import java.util.Map;

public class MapUtils {

    public static String convertWithIteration(Map<String, String[]> map) {
        StringBuilder mapAsString = new StringBuilder("");
        if (!map.isEmpty()) {
            for (String key : map.keySet()) {
                mapAsString.append(key + "=" + map.get(key)[0] + ", ");
            }
            mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("");
        }
        return mapAsString.toString();

    }
}
