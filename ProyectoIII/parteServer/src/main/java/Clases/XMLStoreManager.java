/*package Clases;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class XMLStoreManager {

    private static Map<String, XMLStore> stores;

    public XMLStoreManager() {
        this.stores = new HashMap<>();
    }

    public static void createStore(String storeName) {
        if (!stores.containsKey(storeName)) {
            XMLStore store = new XMLStore();
            stores.put(storeName, store);
            System.out.println("XML Store '" + storeName + "' created.");
        } else {
            System.out.println("XML Store '" + storeName + "' already exists.");
        }
    }

    public void insertInstance(String storeName, Map<String, Object> instanceData) {
        XMLStore store = stores.get(storeName);
        if (store != null) {
            String instanceId = generateInstanceId();
            store.insertInstance(instanceId, instanceData);
            System.out.println("Instance inserted with ID: " + instanceId);
        } else {
            System.out.println("XML Store '" + storeName + "' does not exist.");
        }
    }

    public void deleteInstance(String storeName, String instanceId) {
        XMLStore store = stores.get(storeName);
        if (store != null) {
            if (store.containsInstance(instanceId)) {
                store.deleteInstance(instanceId);
                System.out.println("Instance with ID: " + instanceId + " deleted.");
            } else {
                System.out.println("Instance with ID: " + instanceId + " does not exist in XML Store '" + storeName + "'.");
            }
        } else {
            System.out.println("XML Store '" + storeName + "' does not exist.");
        }
    }

    // Otros métodos y lógica necesaria para manejar los XML Stores

    private String generateInstanceId() {
        // Lógica para generar un ID único para cada instancia
        return UUID.randomUUID().toString();
    }
}
*/