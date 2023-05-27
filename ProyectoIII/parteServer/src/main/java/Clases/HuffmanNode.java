/*package Clases;

import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char data;
    HuffmanNode left, right;

    public HuffmanNode(int frequency, char data) {
        this.frequency = frequency;
        this.data = data;
    }

    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }
}

class HuffmanCode {
    public static Map<Character, String> buildHuffmanTree(String data) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : data.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getValue(), entry.getKey()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, '-');
            parent.left = left;
            parent.right = right;

            pq.offer(parent);
        }

        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodes(pq.peek(), "", huffmanCodes);

        return huffmanCodes;
    }

    private static void buildHuffmanCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.data, code);
            return;
        }

        buildHuffmanCodes(node.left, code + "0", huffmanCodes);
        buildHuffmanCodes(node.right, code + "1", huffmanCodes);
    }

    public static String compressData(String data, Map<Character, String> huffmanCodes) {
        StringBuilder compressedData = new StringBuilder();
        for (char c : data.toCharArray()) {
            compressedData.append(huffmanCodes.get(c));
        }
        return compressedData.toString();
    }

    public static String decompressData(String compressedData, Map<Character, String> huffmanCodes) {
        StringBuilder decompressedData = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        for (char c : compressedData.toCharArray()) {
            currentCode.append(c);
            for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
                if (entry.getValue().equals(currentCode.toString())) {
                    decompressedData.append(entry.getKey());
                    currentCode.setLength(0);
                    break;
                }
            }
        }
        return decompressedData.toString();
    }
}


*/