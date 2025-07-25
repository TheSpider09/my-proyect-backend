import models.Usuario;
import parsers.JsonParser;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("-".repeat(31) + " SIMULACIÓN JSON " + "-".repeat(31) + "\n");

        // Ejercicio 1: Parsing de JSON a objeto Java
        //ejercicio1();

        // Ejercicio 2: Conversión de objeto Java a JSON
        //ejercicio2();

        // Ejercicio 3: Simulación de respuesta de API
        //ejercicio3();

        // Ejercicio 4: Procesamiento de múltiples usuarios
        //ejercicio4();
    }

    public static String guionRepeat() {
        return ("\n" + "-".repeat(80) + "\n");
    }

    public static void ejercicio1() {
        String json = """
            {
                "id": 1,
                "nombre": "Ana García",
                "email": "ana@empresa.com",
                "activo": true,
                "rol": "desarrolladora",
                "tecnologias": ["Java", "Spring Boot", "PostgreSQL", "Docker"]
            }
            """;

        System.out.println("JSON:\n" + json + guionRepeat());

        //Convertir Json a objeto "user"
        Usuario user = Usuario.fromJson(json);

        System.out.println("Se recibió correctamente el JSON: \n" + user + guionRepeat());
    }

    public static void ejercicio2() {
        //Crearemos un usuario
        Usuario user = new Usuario(2, "Summer Ray", "summer.rat@empresa.com", false, "senior developer");
        user.agregarTecnologia("Java");
        user.agregarTecnologia("Spring");
        user.agregarTecnologia("MySQL");
        user.agregarTecnologia("Redis");

        System.out.println("Usuario creado:\n" + user + guionRepeat());

        // Convertir a json
        String json = user.toJson();

        System.out.println("JSON:\n" + json + guionRepeat());
    }

    public static void ejercicio3() {
        // JSON que simula respuesta típica de API REST
        String respuestaAPI = """
            {
                "success": true,
                "data": {
                    "id": 3,
                    "nombre": "María Rodriguez",
                    "email": "maria@tech.com",
                    "activo": true,
                    "rol": "senior developer",
                    "tecnologias": ["Java", "Microservicios", "Kubernetes", "AWS"]
                },
                "message": "Usuario obtenido exitosamente",
                "timestamp": "2024-07-23T10:30:00Z"
            }
            """;

        System.out.println("Respuesta de la API:\n" + respuestaAPI + guionRepeat());

        //Extraer información de la respuesta
        Boolean success = JsonParser.extraerBoolean(respuestaAPI, "succes");
        String message = JsonParser.extraerString(respuestaAPI, "message");
        String date = JsonParser.extraerString(respuestaAPI, "timestamp");

        System.out.print("Metadatos de la respuesta:\n" +
                "\t- Success: " + success +
                "\n\t- Message: " + message +
                "\n\t- Timestamp: " + date + "\n" + guionRepeat());

        if (Boolean.TRUE.equals(success)) {
            // Extraer el objeto data (simplificado)
            String userData = """
                {
                    "id": 3,
                    "nombre": "María Rodriguez",
                    "email": "maria@tech.com",
                    "activo": true,
                    "rol": "senior developer",
                    "tecnologias": ["Java", "Microservicios", "Kubernetes", "AWS"]
                }
                """;

            Usuario usuario = Usuario.fromJson(userData);
            System.out.println("\n👤 Usuario extraído:");
            System.out.println(usuario);
        }

    }

    public static void ejercicio4() {
        String[] jsonUsuarios = {
                """
            {
                "id": 10,
                "nombre": "Pedro Sánchez",
                "email": "pedro@dev.com",
                "activo": true,
                "rol": "junior developer",
                "tecnologias": ["Java", "MySQL"]
            }
            """,
                """
            {
                "id": 11,
                "nombre": "Laura Martín",
                "email": "laura@fullstack.com",
                "activo": false,
                "rol": "fullstack developer",
                "tecnologias": ["Java", "React", "MongoDB", "Docker"]
            }
            """,
                """
            {
                "id": 12,
                "nombre": "Diego Torres",
                "email": "diego@backend.com",
                "activo": true,
                "rol": "backend developer",
                "tecnologias": ["Java", "Spring Boot", "PostgreSQL", "RabbitMQ", "Redis"]
            }
            """
        };

        List <Usuario> users = new ArrayList<>();

        // Procesar cada JSON
        for (int i = 0; i < jsonUsuarios.length; i++) {
            System.out.println("\nProcesando usuario " + (i + 1) + ":");
            Usuario usuario = Usuario.fromJson(jsonUsuarios[i]);
            users.add(usuario);
            System.out.println(usuario.getNombre() + " - " + usuario.getRol());
        }

        //Estadísticas
        System.out.println(guionRepeat() + "Estadísticas:");
        System.out.println("- Total usuarios: " + users.size());

        // Usuario con más tecnologías
        Usuario userConMasTech = users.stream()
                .max((u1, u2) -> Integer.compare(u1.getTecnologias().size(), u2.getTecnologias().size()))
                .orElse(null);

        if (userConMasTech != null) {
            System.out.println("- Usuario con más tecnologías: " + userConMasTech.getNombre() +
                    " (" + userConMasTech.getTecnologias().size() + " tecnologías)");
        }

        // Todas las tecnologías únicas
        Set <String> todasLasTech = new HashSet<>();
        users.forEach(u -> todasLasTech.addAll(u.getTecnologias()));
        System.out.println("- Tecnologías únicas encontradas: " + todasLasTech.size());
        System.out.println("- Lista de Tecnologías: " + todasLasTech);
    }
}