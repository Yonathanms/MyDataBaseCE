package com.proyecto.springboot3_1.controlador;

///clase de las operaciones de la base de datos local

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
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
public class BasesDatosControlador {
    private static final String RUTA_BASE = "ruta_base/";


    ///metodo que crea una carpeta e insterta un XML

    @PostMapping("/INSERTinstancia")
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

    private static String generarNombreArchivo() {
        // Generar un nombre de archivo único utilizando algún método (por ejemplo, un timestamp)
        // Puedes adaptar esta lógica según tus necesidades
        return "instancia" + System.currentTimeMillis() + ".xml";
    }

    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    @RequestMapping("/SELECTbusqueda")
    public String buscarInstancias(@RequestBody JsonNode datos) {
        String nombreCarpeta = datos.get("nombreCarpeta").asText();
        String campo = datos.get("campo").asText();
        String valor = datos.get("valor").asText();

        String rutaCarpeta = RUTA_BASE + nombreCarpeta + "./";

        // Obtener la lista de archivos XML en la carpeta
        File carpeta = new File(rutaCarpeta);
        File[] archivosXml = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (archivosXml == null || archivosXml.length == 0) {
            return "No se encontraron instancias en el XML store.";
        }

        List<String> instanciasEncontradas = new ArrayList<>();

        // Recorrer cada archivo XML y buscar instancias que cumplan con las condiciones
        for (File archivoXml : archivosXml) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document documento = dBuilder.parse(archivoXml);
                documento.getDocumentElement().normalize();

                NodeList nodosInstancia = documento.getElementsByTagName("instancia");

                for (int i = 0; i < nodosInstancia.getLength(); i++) {
                    Node nodo = nodosInstancia.item(i);
                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementoInstancia = (Element) nodo;
                        String valorCampo = elementoInstancia.getElementsByTagName(campo).item(0).getTextContent();
                        boolean cumpleCondicion = evaluarCondicion(valorCampo, "=", valor);

                        if (cumpleCondicion) {
                            String instancia = obtenerInstanciaComoTexto(elementoInstancia);
                            instanciasEncontradas.add(instancia);
                        }
                    }
                }
            } catch (Exception e) {
                return "Error al buscar instancias: " + e.getMessage();
            }
        }

        if (instanciasEncontradas.isEmpty()) {
            return "No se encontraron instancias que cumplan las condiciones.";
        } else {
            StringBuilder resultado = new StringBuilder();
            resultado.append("Instancias encontradas:\n");
            for (String instancia : instanciasEncontradas) {
                resultado.append(instancia).append("\n");
            }
            return resultado.toString();
        }
    }



    ///metodo complementario
    private static String obtenerInstanciaComoTexto(Element instanciaElemento) {
        StringBuilder builder = new StringBuilder();
        NodeList campos = instanciaElemento.getChildNodes();
        for (int i = 0; i < campos.getLength(); i++) {
            Node campo = campos.item(i);
            if (campo.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoCampo = (Element) campo;
                String nombreCampo = elementoCampo.getNodeName();
                String valorCampo = elementoCampo.getTextContent();
                builder.append(nombreCampo).append(": ").append(valorCampo).append(", ");
            }
        }
        // Eliminar la coma y el espacio extra al final
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }
        return builder.toString();
    }


    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    @RequestMapping("/JOINbuscarinstancias")
    public String buscarInstanciasJOIN(@RequestBody JsonNode datos) {
        String nombreCarpeta1 = datos.get("nombreCarpeta1").asText();
        String nombreCarpeta2 = datos.get("nombreCarpeta2").asText();
        String campo = datos.get("campo").asText();
        String valor = datos.get("valor").asText();
        String campo2 = datos.get("campo2").asText();
        String valor2 = datos.get("valor2").asText();
        String campo3 = datos.get("campo3").asText();
        String valor3 = datos.get("valor3").asText();

        String rutaCarpeta1 = RUTA_BASE + nombreCarpeta1 + "/";
        String rutaCarpeta2 = RUTA_BASE + nombreCarpeta2 + "/";

        // Obtener la lista de archivos XML en las carpetas
        File carpeta1 = new File(rutaCarpeta1);
        File carpeta2 = new File(rutaCarpeta2);
        File[] archivosXml1 = carpeta1.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
        File[] archivosXml2 = carpeta2.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (archivosXml1 == null || archivosXml1.length == 0 || archivosXml2 == null || archivosXml2.length == 0) {
            return "No se encontraron instancias en los XML stores.";
        }

        List<String> instanciasEncontradas = new ArrayList<>();

        // Recorrer cada archivo XML del primer XML store y buscar instancias que cumplan con al menos una de las condiciones
        for (File archivoXml1 : archivosXml1) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document documento1 = dBuilder.parse(archivoXml1);
                documento1.getDocumentElement().normalize();

                NodeList nodosInstancia1 = documento1.getElementsByTagName("instancia");

                // Recorrer cada archivo XML del segundo XML store y buscar instancias que cumplan con al menos una de las condiciones
                for (File archivoXml2 : archivosXml2) {
                    Document documento2 = dBuilder.parse(archivoXml2);
                    documento2.getDocumentElement().normalize();

                    NodeList nodosInstancia2 = documento2.getElementsByTagName("instancia");

                    // Realizar el JOIN entre las instancias de ambos XML stores
                    for (int i = 0; i < nodosInstancia1.getLength(); i++) {
                        Node nodo1 = nodosInstancia1.item(i);
                        if (nodo1.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoInstancia1 = (Element) nodo1;
                            String valorCampo1 = elementoInstancia1.getElementsByTagName(campo).item(0).getTextContent();
                            boolean cumpleCondicion1 = evaluarCondicion(valorCampo1, "=", valor);

                            // Si se proporcionó el segundo campo de búsqueda, verificar si cumple la condición
                            boolean cumpleCondicion2 = true;
                            if (campo2 != null && "=" != null && valor2 != null) {
                                String valorCampo2 = elementoInstancia1.getElementsByTagName(campo2).item(0).getTextContent();
                                cumpleCondicion2 = evaluarCondicion(valorCampo2, "=", valor2);
                            }

                            // Si se proporcionó el tercer campo de búsqueda, verificar si cumple la condición
                            boolean cumpleCondicion3 = true;
                            if (campo3 != null && "=" != null && valor3 != null) {
                                String valorCampo3 = elementoInstancia1.getElementsByTagName(campo3).item(0).getTextContent();
                                cumpleCondicion3 = evaluarCondicion(valorCampo3, "=", valor3);
                            }

                            // Verificar si al menos una condición se cumple y agregar la instancia encontrada
                            if (cumpleCondicion1 || cumpleCondicion2 || cumpleCondicion3) {
                                for (int j = 0; j < nodosInstancia2.getLength(); j++) {
                                    Node nodo2 = nodosInstancia2.item(j);
                                    if (nodo2.getNodeType() == Node.ELEMENT_NODE) {
                                        Element elementoInstancia2 = (Element) nodo2;
                                        String valorCampo2 = elementoInstancia2.getElementsByTagName(campo).item(0).getTextContent();
                                        boolean cumpleCondicion2_2 = evaluarCondicion(valorCampo2, "=", valor);

                                        boolean cumpleCondicion2_3 = true;
                                        if (campo2 != null && "=" != null && valor2 != null) {
                                            String valorCampo2_2 = elementoInstancia2.getElementsByTagName(campo2).item(0).getTextContent();
                                            cumpleCondicion2_3 = evaluarCondicion(valorCampo2_2, "=", valor2);
                                        }

                                        boolean cumpleCondicion2_4 = true;
                                        if (campo3 != null && "=" != null && valor3 != null) {
                                            String valorCampo2_3 = elementoInstancia2.getElementsByTagName(campo3).item(0).getTextContent();
                                            cumpleCondicion2_4 = evaluarCondicion(valorCampo2_3, "=", valor3);
                                        }

                                        if (cumpleCondicion2_2 || cumpleCondicion2_3 || cumpleCondicion2_4) {
                                            String instancia = obtenerInstanciaComoTexto2(elementoInstancia1, elementoInstancia2);
                                            instanciasEncontradas.add(instancia);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                return "Error al buscar instancias: " + e.getMessage();
            }
        }

        if (instanciasEncontradas.isEmpty()) {
            return "No se encontraron instancias que cumplan las condiciones.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Instancias encontradas:\n");
            for (String instancia : instanciasEncontradas) {
                sb.append(instancia).append("\n");
            }
            return sb.toString();
        }
    }



    ///metodo complementario
    private static String obtenerInstanciaComoTexto2(Element instanciaElemento1, Element instanciaElemento2) {
        StringBuilder builder = new StringBuilder();
        builder.append("Instancia 1: ");
        NodeList campos1 = instanciaElemento1.getChildNodes();
        for (int i = 0; i < campos1.getLength(); i++) {
            Node campo1 = campos1.item(i);
            if (campo1.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoCampo1 = (Element) campo1;
                String nombreCampo1 = elementoCampo1.getNodeName();
                String valorCampo1 = elementoCampo1.getTextContent();
                builder.append(nombreCampo1).append(": ").append(valorCampo1).append(", ");
            }
        }

        builder.append("Instancia 2: ");
        NodeList campos2 = instanciaElemento2.getChildNodes();
        for (int i = 0; i < campos2.getLength(); i++) {
            Node campo2 = campos2.item(i);
            if (campo2.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoCampo2 = (Element) campo2;
                String nombreCampo2 = elementoCampo2.getNodeName();
                String valorCampo2 = elementoCampo2.getTextContent();
                builder.append(nombreCampo2).append(": ").append(valorCampo2).append(", ");
            }
        }

        // Eliminar la coma y el espacio extra al final
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }
        return builder.toString();
    }

    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    ///Funcion que elimina todas las instancias de un XML stor
    @PostMapping("/DELETEinstancias")
    public void eliminarInstancias(@RequestBody JsonNode nombreCarpeta) {
        String nombreCarpetaTexto = nombreCarpeta.get("nombreCarpeta").asText();
        String rutaCarpeta = RUTA_BASE + nombreCarpetaTexto + "./";
        File carpeta = new File(rutaCarpeta);

        // Verificar si la carpeta existe
        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe");
            return;
        }

        // Obtener la lista de archivos en la carpeta
        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta está vacía, no hay instancias para eliminar");
            return;
        }

        // Eliminar cada archivo en la carpeta
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                if (archivo.delete()) {
                    System.out.println("Instancia eliminada: " + archivo.getName());
                } else {
                    System.out.println("Error al eliminar la instancia: " + archivo.getName());
                }
            }
        }
    }

    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /// funcion que elimina una instancia especifica de una carpeta
    @PostMapping("/DELETEinstancia")
    public void eliminarInstancia(@RequestBody JsonNode variables) {
        String nombreCarpeta = variables.get("nombreCarpeta").asText();
        String campo = variables.get("campo").asText();
        String valor = variables.get("valor").asText();

        String rutaCarpeta = RUTA_BASE + nombreCarpeta + "./";
        File carpeta = new File(rutaCarpeta);

        // Verificar si la carpeta existe
        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe");
            return;
        }

        // Obtener la lista de archivos en la carpeta
        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta está vacía, no hay instancias para eliminar");
            return;
        }

        // Eliminar las instancias que cumplan con las condiciones
        int contadorEliminaciones = 0;
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                String contenido;
                try {
                    contenido = new String(Files.readAllBytes(archivo.toPath()));
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + archivo.getName());
                    continue;
                }

                if (cumpleCondicion(contenido, campo, "=", valor)) {
                    if (archivo.delete()) {
                        System.out.println("Instancia eliminada: " + archivo.getName());
                        contadorEliminaciones++;
                    } else {
                        System.out.println("Error al eliminar la instancia: " + archivo.getName());
                    }
                }
            }
        }

        if (contadorEliminaciones == 0) {
            System.out.println("No se encontraron instancias que cumplan con las condiciones");
        } else {
            System.out.println("Total de instancias eliminadas: " + contadorEliminaciones);
        }
    }

    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    @PostMapping("/UPDATEinstancias")
    public void actualizar_instancias(@RequestBody JsonNode datos) {
        String nombreCarpeta = datos.get("nombreCarpeta").asText();
        String nuevoContenido = datos.get("nuevoContenido").asText();

        String rutaCarpeta = RUTA_BASE + nombreCarpeta + "/";
        File carpeta = new File(rutaCarpeta);

        // Verificar si la carpeta existe
        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe");
            return;
        }

        // Obtener la lista de archivos en la carpeta
        File[] archivos = carpeta.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String nombre) {
                return nombre.toLowerCase().endsWith(".xml");
            }
        });

        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta está vacía, no hay instancias para actualizar");
            return;
        }

        // Actualizar todas las instancias
        int contadorActualizaciones = 0;
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                try {
                    FileWriter writer = new FileWriter(archivo);
                    writer.write(nuevoContenido);
                    writer.close();
                    System.out.println("Instancia actualizada: " + archivo.getName());
                    contadorActualizaciones++;
                } catch (IOException e) {
                    System.out.println("Error al actualizar la instancia: " + archivo.getName());
                }
            }
        }

        if (contadorActualizaciones == 0) {
            System.out.println("No se encontraron instancias para actualizar");
        } else {
            System.out.println("Total de instancias actualizadas: " + contadorActualizaciones);
        }
    }
    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    ///metodo de actualizar una instancia usando la logica de UPDATE
    @PostMapping("/UPDATEinstancia")
    public void actualizar_instancia(@RequestBody JsonNode datos) {
        String nombreCarpeta = datos.get("nombreCarpeta").asText();
        String campo = datos.get("campo").asText();
        String valor = datos.get("valor").asText();
        String nuevoContenido = datos.get("nuevoContenido").asText();

        String rutaCarpeta = RUTA_BASE + nombreCarpeta + "/";
        File carpeta = new File(rutaCarpeta);

        // Verificar si la carpeta existe
        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe");
            return;
        }

        // Obtener la lista de archivos en la carpeta
        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta está vacía, no hay instancias para actualizar");
            return;
        }

        // Actualizar la instancia que cumple con la condición
        int contadorActualizaciones = 0;
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                String contenido;
                try {
                    contenido = new String(Files.readAllBytes(archivo.toPath()));
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + archivo.getName());
                    continue;
                }

                if (cumpleCondicion(contenido, campo, valor)) {
                    try {
                        FileWriter writer = new FileWriter(archivo);
                        writer.write(nuevoContenido);
                        writer.close();
                        System.out.println("Instancia actualizada: " + archivo.getName());
                        contadorActualizaciones++;
                    } catch (IOException e) {
                        System.out.println("Error al actualizar la instancia: " + archivo.getName());
                    }
                }
            }
        }

        if (contadorActualizaciones == 0) {
            System.out.println("No se encontró ninguna instancia que cumpla con la condición");
        } else {
            System.out.println("Total de instancias actualizadas: " + contadorActualizaciones);
        }
    }

    ///metodo complementario
    private static boolean cumpleCondicion(String contenido, String campo, String valor) {
        // Implementa la lógica para verificar si un contenido cumple con la condición
        // Puedes adaptar esta lógica según la estructura y el contenido de tus archivos XML

        // Ejemplo básico: Verificar si el contenido contiene un campo específico con un valor dado
        return contenido.contains("<" + campo + ">" + valor + "</" + campo + ">");
    }


    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/


    /// metodo de prueba
    @PostMapping("/suma")
    public void suma(@RequestBody JsonNode jsonnumeros) {

        int a = jsonnumeros.get("a").intValue();
        int b = jsonnumeros.get("b").intValue();
        int c = jsonnumeros.get("c").intValue();

        int resultado = a + b + c;
        System.out.println("El resultado de la suma es: " + resultado);
    }

    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    ///metodos complementarios

    ///se usa en los metodos de mostrar instancia con SELECT y JOIN
    private static boolean evaluarCondicion(String valorCampo, String operador, String valor) {
        switch (operador) {
            case "=":
                return valorCampo.equals(valor);
            case "!=":
                return !valorCampo.equals(valor);
            case ">":
                return Double.parseDouble(valorCampo) > Double.parseDouble(valor);
            case "<":
                return Double.parseDouble(valorCampo) < Double.parseDouble(valor);
            case ">=":
                return Double.parseDouble(valorCampo) >= Double.parseDouble(valor);
            case "<=":
                return Double.parseDouble(valorCampo) <= Double.parseDouble(valor);
            default:
                return false;
        }
    }

    ///se usa en los metodos de eliminar y acttualizar una instancia especifica
    private static boolean cumpleCondicion(String contenido, String campo, String operador, String valor) {
        // Implementa la lógica para verificar si un contenido cumple con las condiciones
        // Puedes adaptar esta lógica según la estructura y el contenido de tus archivos XML

        // Ejemplo básico: Verificar si el contenido contiene un campo específico con un valor dado
        return contenido.contains("<" + campo + ">" + valor + "</" + campo + ">");
    }

}
