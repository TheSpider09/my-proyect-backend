package models;

import parsers.JsonParser;
import java.util.*;

public class Usuario {
    private Integer id;
    private String nombre;
    private String email;
    private Boolean activo;
    private List<String> tecnologias;
    private String rol;

    // Constructores
    public Usuario() {
        this.tecnologias = new ArrayList<>();
    }

    public Usuario(Integer id, String nombre, String email, Boolean activo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
        this.rol = rol;
        this.tecnologias = new ArrayList<>();
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public List<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<String> tecnologias) { this.tecnologias = tecnologias; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public void agregarTecnologia(String tecnologia) {
        this.tecnologias.add(tecnologia);
    }

    // Métodos:
    // Crea un Usuario a partir de un JSON string
    public static Usuario fromJson(String json) {
        Usuario usuario = new Usuario();

        // Extraer datos básicos
        usuario.id = JsonParser.extraerNumero(json, "id");
        usuario.nombre = JsonParser.extraerString(json, "nombre");
        usuario.email = JsonParser.extraerString(json, "email");
        usuario.activo = JsonParser.extraerBoolean(json, "activo");
        usuario.rol = JsonParser.extraerString(json, "rol");

        // Extraer array de tecnologías
        usuario.tecnologias = JsonParser.extraerArrayStrings(json, "tecnologias");

        return usuario;
    }

    //Convierte este Usuario a JSON string
    public String toJson() {
        Map<String, Object> datos = new HashMap<>();
        datos.put("id", this.id);
        datos.put("nombre", this.nombre);
        datos.put("email", this.email);
        datos.put("activo", this.activo);
        datos.put("rol", this.rol);
        datos.put("tecnologias", this.tecnologias);

        return JsonParser.objetoAJson(datos);
    }

    @Override
    public String toString() {
        return ("user: \n"
                + "\t- id: " + id + "\n"
                + "\t- nombre: " + nombre + "\n"
                + "\t- email: " + email + "\n"
                + "\t- activo: " + activo + "\n"
                + "\t- rol: " + rol + "\n");
    }
}