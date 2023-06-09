package com.proyecto.springboot3_1.controlador;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char character;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(int frequency, char character, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

@RestController
public class HuffmanCompression {

    public static Map<Character, String> buildHuffmanTable(String text) {
        // Calcula la frecuencia de cada caracter en el texto
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Crea un nodo Huffman para cada caracter y los agrega a una cola de prioridad
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getValue(), entry.getKey(), null, null));
        }

        // Construye el árbol Huffman combinando los nodos con menor frecuencia
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, '\0', left, right);
            pq.add(parent);
        }

        // Construye la tabla de códigos Huffman utilizando el árbol Huffman
        HuffmanNode root = pq.poll();
        Map<Character, String> huffmanTable = new HashMap<>();
        buildHuffmanTableRecursive(root, "", huffmanTable);

        return huffmanTable;
    }

    private static void buildHuffmanTableRecursive(HuffmanNode node, String code, Map<Character, String> huffmanTable) {
        if (node.isLeaf()) {
            huffmanTable.put(node.character, code); // Asigna el código Huffman al caracter
        } else {
            buildHuffmanTableRecursive(node.left, code + "0", huffmanTable); // Recursivamente, asigna un 0 al código Huffman del subárbol izquierdo
            buildHuffmanTableRecursive(node.right, code + "1", huffmanTable); // Recursivamente, asigna un 1 al código Huffman del subárbol derecho
        }
    }

    public static String compress(String text) {
        // Construye la tabla de códigos Huffman
        Map<Character, String> huffmanTable = buildHuffmanTable(text);

        // Comprime el texto utilizando la tabla de códigos Huffman
        StringBuilder compressedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressedText.append(huffmanTable.get(c)); // Agrega el código Huffman del caracter al texto comprimido
        }

        return compressedText.toString();
    }

    public static String decompress(String compressedText, Map<Character, String> huffmanTable) {
        StringBuilder originalText = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();

        for (int i = 0; i < compressedText.length(); i++) {
            currentCode.append(compressedText.charAt(i)); // Construye el código Huffman actual
            for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
                if (entry.getValue().equals(currentCode.toString())) { // Si encuentra una coincidencia en la tabla de códigos Huffman
                    originalText.append(entry.getKey()); // Agrega el caracter correspondiente al texto original
                    currentCode.setLength(0); // Reinicia el código actual para buscar el próximo código Huffman
                    break;
                }
            }
        }

        return originalText.toString();
    }

    ///metodo que prueba el algoritmo
    @PostMapping("/COMPRIMIRclave")
    public void retornar_algoritmoHuffman(@RequestBody JsonNode datoclave) {

        String clave = datoclave.get("clave").asText();

        System.out.println("Clave comprimida en bits= "+compress(clave));
        System.out.println("Array con el valor de bits por carácter de la clave= "+buildHuffmanTable(clave));
        System.out.println("Clave descomprimida= "+decompress(compress(clave),buildHuffmanTable(clave)));
    }
}


