/*package Clases;

import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {
    private static Map<String, String> userCredentials = new HashMap<>();

    public static void main(String[] args) {
        // Agregar usuarios y contraseñas a la base de datos
        addUserCredentials("user1", "password1");
        addUserCredentials("user2", "password2");
        addUserCredentials("user3", "password3");

        // Autenticar usuarios
        String username = "user1";
        String password = "password1";
        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            System.out.println("Usuario autenticado");
        } else {
            System.out.println("Autenticación fallida");
        }
    }

    public static void addUserCredentials(String username, String password) {
        String compressedPassword = compressPassword(password);
        userCredentials.put(username, compressedPassword);
    }

    public static boolean authenticateUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            String storedPassword = userCredentials.get(username);
            String decompressedPassword = decompressPassword(storedPassword);
            return decompressedPassword.equals(password);
        }
        return false;
    }

    private static String compressPassword(String password) {
        Map<Character, String> huffmanCodes = HuffmanCode.buildHuffmanTree(password);
        return HuffmanCode.compressData(password, huffmanCodes);
    }

    private static String decompressPassword(String compressedPassword) {
        Map<Character, String> huffmanCodes = HuffmanCode.buildHuffmanTree(compressedPassword);
        return HuffmanCode.decompressData(compressedPassword, huffmanCodes);
    }
}*/