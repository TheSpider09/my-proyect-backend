import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== " + Config.getProperty("app.name", "Mi App") + " ===");
        System.out.println("Version: " + Config.getProperty("app.version", "1.0.0"));
        System.out.println("Fecha y hora: " +
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        System.out.println("\n--- Configuración ---");
        System.out.println("API Base URL: " + Config.getApiUrl());
        System.out.println("HTTP Timeout: " + Config.getTimeout() + "ms");
        System.out.println("Log Level: " + Config.getProperty("log.level", "INFO"));

        System.out.println("\n--- Información del Sistema ---");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Memoria libre: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " MB");

        System.out.println("\n=== Sistema iniciado correctamente ===");
        System.out.println("Listo para consumir APIs desde: " + Config.getApiUrl());
    }
}