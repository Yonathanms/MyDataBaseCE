package Servicios;

import MetodosCompartidos.ServiciosGenericosAPI;
import Modelo.Persona;

/// En los servicios va la parte lógica del Servidor, aquí se crean los métodos,
/// para el caso de persona se pueden crear metodos, como guardar, editar, eliminar...
public interface PersonaServicioAPI extends ServiciosGenericosAPI <Persona, Long> {

}