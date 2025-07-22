import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Mi Primera Aplicación Backend ===");
        System.out.println("Fecha y hora: " + LocalDateTime.now());

        // Por ahora solo un mensaje, después consumiremos APIs reales
        System.out.println("¡Listo para consumir APIs!");

        // Mostrar información del sistema
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));

        System.out.println("=== Proyecto iniciado correctamente ===");
    }
}