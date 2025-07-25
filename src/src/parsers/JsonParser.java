package parsers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    //Extrae un valor string de un JSON simple
    public static String extraerString(String json, String campo) {
        String patron = "\"" + campo + "\"\\s*:\\s*\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    //Extrae un valor numérico de un JSON simple
    public static Integer extraerNumero(String json, String campo) {
        String patron = "\"" + campo + "\"\\s*:\\s*(\\d+)";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }

    //Extrae un valor boolean de un JSON simple
    public static Boolean extraerBoolean(String json, String campo) {
        String patron = "\"" + campo + "\"\\s*:\\s*(true|false)";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return Boolean.parseBoolean(matcher.group(1));
        }
        return null;
    }

    //Extrae un array de strings de un JSON
    public static List<String> extraerArrayStrings(String json, String campo) {
        String patron = "\"" + campo + "\"\\s*:\\s*\\[([^\\]]+)\\]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            String contenidoArray = matcher.group(1);
            // Remover comillas y espacios, dividir por comas
            String[] elementos = contenidoArray.replaceAll("\"", "").split(",");
            List<String> resultado = new ArrayList<>();
            for (String elemento : elementos) {
                resultado.add(elemento.trim());
            }
            return resultado;
        }
        return new ArrayList<>();
    }

    //Convierte un objeto a JSON básico
    public static String objetoAJson(Map<String, Object> objeto) {
        StringBuilder json = new StringBuilder("{");
        boolean primero = true;

        for (Map.Entry<String, Object> entry : objeto.entrySet()) {
            if (!primero) {
                json.append(",");
            }

            json.append("\"").append(entry.getKey()).append("\":");

            Object valor = entry.getValue();
            if (valor instanceof String) {
                json.append("\"").append(valor).append("\"");
            } else if (valor instanceof Boolean || valor instanceof Number) {
                json.append(valor);
            } else if (valor instanceof List) {
                json.append(listaAJsonArray((List<?>) valor));
            } else {
                json.append("null");
            }

            primero = false;
        }

        json.append("}");
        return json.toString();
    }

    private static String listaAJsonArray(List<?> lista) {
        StringBuilder array = new StringBuilder("[");
        boolean primero = true;

        for (Object item : lista) {
            if (!primero) {
                array.append(",");
            }

            if (item instanceof String) {
                array.append("\"").append(item).append("\"");
            } else {
                array.append(item);
            }

            primero = false;
        }

        array.append("]");
        return array.toString();
    }
}