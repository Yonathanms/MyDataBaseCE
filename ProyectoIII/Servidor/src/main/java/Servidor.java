import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.nio.file.Files;


public class Servidor {
    private static final String RUTA_BASE = "ruta_base/";

    public static void main(String[] args) {


        //1) Mostrar un listado de las instancias asociadas a un XML store con máximo tres condiciones para la
        //búsqueda (usando AND o OR). Debe seguir la estructura de SELECT en SQL
        //buscarInstancias("carritossssss", "modelo", "=", "Focus");

        //2) Mostrar un listado de las instancias asociadas a un XML store con máximo tres condiciones para la
        //búsqueda (usando AND o OR), con un JOIN entre máximo dos XML store. Debe seguir la estructura de
        //SELECT en SQL.
        //buscarInstancias2("carritos", "carritossssss", "marca", "=", "Toyota");


        //Insertar instancias a un XML store (uno o varios en un solo script). Debe usar la estructura de INSERT
        //en SQL.
        //List<String> instancias = new ArrayList<>();
        //instancias.add("<marca>Toyota</marca><modelo>Civic</modelo><color>rojo</azul>");
        //instancias.add("<marca>Ford</marca><modelo>Focus</modelo><color>azul</azul>");
        //insertarInstancias("carritossssss", instancias);

        //Actualizar una instancia de un XML store. Debe usar la estructura de UPDATE en SQL
        //actualizarInstancia("carritos", "marca", "Toyota", "<instancia><marca>Nissan</marca><modelo>Altima</modelo></instancia>");

        //Eliminar todos las instancias de un XML store. Debe usar la estructura de DELETE en SQL.
        //eliminarTodasLasInstancias("carritos");

        //Eliminar un conjunto específico de instancias del XML store. Debe usar la estructura de DELETE en
        //SQL.
        //eliminarInstancias("carritossssss", "marca", "=", "Toyota");

        //Actualizar un conjunto específico de instancias de un XML store. Debe usar la estructura de UPDATE
        //en SQL.
        //actualizarInstancias("carros", "marca", "=", "Toyota", "<instancia><marca>Nissan</marca><modelo>Altima</modelo></instancia>");



    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Mostrar un listado de las instancias asociadas a un XML store con máximo tres condiciones para la
    //búsqueda (usando AND o OR), con un JOIN entre máximo dos XML store. Debe seguir la estructura de
    //SELECT en SQL.
    public static void buscarInstancias(String nombreCarpeta, String campo, String operador, String valor) {
        String rutaCarpeta = RUTA_BASE + nombreCarpeta + "./";

        // Obtener la lista de archivos XML en la carpeta
        File carpeta = new File(rutaCarpeta);
        File[] archivosXml = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (archivosXml == null || archivosXml.length == 0) {
            System.out.println("No se encontraron instancias en el XML store.");
            return;
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
                        boolean cumpleCondicion = evaluarCondicion(valorCampo, operador, valor);

                        if (cumpleCondicion) {
                            String instancia = obtenerInstanciaComoTexto(elementoInstancia);
                            instanciasEncontradas.add(instancia);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al buscar instancias: " + e.getMessage());
            }
        }

        if (instanciasEncontradas.isEmpty()) {
            System.out.println("No se encontraron instancias que cumplan las condiciones.");
        } else {
            System.out.println("Instancias encontradas:");
            for (String instancia : instanciasEncontradas) {
                System.out.println(instancia);
            }
        }
    }

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
    //Mostrar un listado de las instancias asociadas a un XML store con máximo tres condiciones para la
    //búsqueda (usando AND o OR), con un JOIN entre máximo dos XML store. Debe seguir la estructura de
    //SELECT en SQL.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //2) Mostrar un listado de las instancias asociadas a un XML store con máximo tres condiciones para la
    //búsqueda (usando AND o OR), con un JOIN entre máximo dos XML store. Debe seguir la estructura de
    //SELECT en SQL.
    public static void buscarInstancias2(String nombreCarpeta1, String nombreCarpeta2, String campo, String operador, String valor) {
        String rutaCarpeta1 = RUTA_BASE + nombreCarpeta1 + "/";
        String rutaCarpeta2 = RUTA_BASE + nombreCarpeta2 + "/";

        // Obtener la lista de archivos XML en las carpetas
        File carpeta1 = new File(rutaCarpeta1);
        File carpeta2 = new File(rutaCarpeta2);
        File[] archivosXml1 = carpeta1.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
        File[] archivosXml2 = carpeta2.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (archivosXml1 == null || archivosXml1.length == 0 || archivosXml2 == null || archivosXml2.length == 0) {
            System.out.println("No se encontraron instancias en los XML stores.");
            return;
        }

        List<String> instanciasEncontradas = new ArrayList<>();

        // Recorrer cada archivo XML del primer XML store y buscar instancias que cumplan con las condiciones
        for (File archivoXml1 : archivosXml1) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document documento1 = dBuilder.parse(archivoXml1);
                documento1.getDocumentElement().normalize();

                NodeList nodosInstancia1 = documento1.getElementsByTagName("instancia");

                // Recorrer cada archivo XML del segundo XML store y buscar instancias que cumplan con las condiciones
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
                            boolean cumpleCondicion1 = evaluarCondicion(valorCampo1, operador, valor);

                            if (cumpleCondicion1) {
                                for (int j = 0; j < nodosInstancia2.getLength(); j++) {
                                    Node nodo2 = nodosInstancia2.item(j);
                                    if (nodo2.getNodeType() == Node.ELEMENT_NODE) {
                                        Element elementoInstancia2 = (Element) nodo2;
                                        String valorCampo2 = elementoInstancia2.getElementsByTagName(campo).item(0).getTextContent();
                                        boolean cumpleCondicion2 = evaluarCondicion(valorCampo2, operador, valor);

                                        if (cumpleCondicion2) {
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
                System.out.println("Error al buscar instancias: " + e.getMessage());
            }
        }

        if (instanciasEncontradas.isEmpty()) {
            System.out.println("No se encontraron instancias que cumplan las condiciones.");
        } else {
            System.out.println("Instancias encontradas:");
            for (String instancia : instanciasEncontradas) {
                System.out.println(instancia);
            }
        }
    }

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

//2) Mostrar un listado de las instancias asociadas a un XML store con máximo tres condiciones para la
    //búsqueda (usando AND o OR), con un JOIN entre máximo dos XML store. Debe seguir la estructura de
    //SELECT en SQL.
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Implementa las otras operaciones según las especificaciones
    // insertarInstancias, eliminarInstancias, actualizarInstancia, etc.

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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Insertar instancias a un XML store (uno o varios en un solo script). Debe usar la estructura de INSERT
    //en SQL
    public static void insertarInstancias(String nombreCarpeta, List<String> instancias) {
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

        for (String instancia : instancias) {
            String nombreArchivo = generarNombreArchivo();
            String rutaArchivo = rutaCarpeta + nombreArchivo;

            // Verificar si el archivo ya existe
            File archivo = new File(rutaArchivo);
            if (archivo.exists()) {
                System.out.println("El archivo ya existe");
                continue;
            }

            // Construir el formato XML de la instancia
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
        return "instancia_" + System.currentTimeMillis() + ".xml";
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Eliminar todos las instancias de un XML store. Debe usar la estructura de DELETE en SQL.
    public static void eliminarTodasLasInstancias(String nombreCarpeta) {
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



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Eliminar un conjunto específico de instancias del XML store. Debe usar la estructura de DELETE en SQL:
    public static void eliminarInstancias(String nombreCarpeta, String campo, String operador, String valor) {
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

                if (cumpleCondicion(contenido, campo, operador, valor)) {
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

    private static boolean cumpleCondicion(String contenido, String campo, String operador, String valor) {
        // Implementa la lógica para verificar si un contenido cumple con las condiciones
        // Puedes adaptar esta lógica según la estructura y el contenido de tus archivos XML

        // Ejemplo básico: Verificar si el contenido contiene un campo específico con un valor dado
        return contenido.contains("<" + campo + ">" + valor + "</" + campo + ">");
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Actualizar una instancia de un XML store. Debe usar la estructura de UPDATE en SQL:

    public static void actualizarInstancia(String nombreCarpeta, String campo, String valor, String nuevoContenido) {
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

    private static boolean cumpleCondicion(String contenido, String campo, String valor) {
        // Implementa la lógica para verificar si un contenido cumple con la condición
        // Puedes adaptar esta lógica según la estructura y el contenido de tus archivos XML

        // Ejemplo básico: Verificar si el contenido contiene un campo específico con un valor dado
        return contenido.contains("<" + campo + ">" + valor + "</" + campo + ">");
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Actualizar un conjunto específico de instancias de un XML store. Debe usar la estructura de UPDATE en SQL:
    public static void actualizarInstancias(String nombreCarpeta, String campo, String operador, String valor, String nuevoContenido) {
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

        // Actualizar las instancias que cumplan con la condición
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

                if (cumpleCondicion(contenido, campo, operador, valor)) {
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
            System.out.println("No se encontraron instancias que cumplan con la condición");
        } else {
            System.out.println("Total de instancias actualizadas: " + contadorActualizaciones);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}