package com.proyecto.springboot3_1.modelo;

import jakarta.persistence.*;

///construccion de la clase persona, genera una tabla y guardan los atributos
/// del nombre, apellidos, correo y la contraseña
@Entity
@Table(name = "persona_table")
public class Persona {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String apellidos;

    private String correo;

    private String clave;

    /// getters y setters de la clase persona
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
