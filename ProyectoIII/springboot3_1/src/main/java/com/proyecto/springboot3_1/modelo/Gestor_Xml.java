package com.proyecto.springboot3_1.modelo;

///clase de las operaciones de la base de datos local

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.nio.file.Files;


@RestController
public class Gestor_Xml {
    private static final String RUTA_BASE = "ruta_base/";

    ///metodos

    @PostMapping("/insertarinstancia")
    public void insertarInstancias(@RequestBody JsonNode jsonData) {
        String nombreCarpeta = jsonData.get("nombreCarpeta").asText();
        JsonNode jsonInstancias = jsonData.get("instancias");

        String rutaCarpeta = RUTA_BASE + nombreCarpeta + "/";
        File carpeta = new File(rutaCarpeta);

        // Verificar si la carpeta existe, de lo contrario, crearla
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada correctamente");
            } else {
                System.out.println("Error al crear la carpeta");
                return;
            }
        }

        for (JsonNode instancia : jsonInstancias) {
            String nombreArchivo = generarNombreArchivo();
            String rutaArchivo = rutaCarpeta + nombreArchivo;

            // Verificar si el archivo ya existe
            File archivo = new File(rutaArchivo);
            if (archivo.exists()) {
                System.out.println("El archivo ya existe");
                continue;
            }

            //Construir el formato XML
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<instancia>");
            // Aquí se pueden agregar los campos y valores correspondientes a la instancia
            xmlBuilder.append(instancia);
            xmlBuilder.append("</instancia>");

            // Guardar la instancia en el archivo XML
            try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
                fileWriter.write(xmlBuilder.toString());
                System.out.println("Instancia insertada correctamente en " + nombreCarpeta);
            } catch (IOException e) {
                System.out.println("Error al insertar la instancia: " + e.getMessage());
            }
        }
    }

    @PostMapping("/suma")
    public void suma(@RequestBody JsonNode jsonnumeros) {

        int a = jsonnumeros.get("a").intValue();
        int b = jsonnumeros.get("b").intValue();
        int c = jsonnumeros.get("c").intValue();

        int resultado = a + b + c;
        System.out.println("El resultado de la suma es: " + resultado);
    }

    private static String generarNombreArchivo() {
        // Generar un nombre de archivo único utilizando algún método (por ejemplo, un timestamp)
        // Puedes adaptar esta lógica según tus necesidades
        return "instancia" + System.currentTimeMillis() + ".xml";
    }
}
