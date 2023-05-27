/*package Clases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientApplication extends JFrame {
    private JButton createXMLStoreButton;
    private JTextField storeNameTextField;
    private JTextArea responseTextArea;

    public ClientApplication() {
        setTitle("Client Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel storeNameLabel = new JLabel("XML Store Name:");
        storeNameLabel.setBounds(10, 10, 100, 25);
        add(storeNameLabel);

        storeNameTextField = new JTextField();
        storeNameTextField.setBounds(120, 10, 200, 25);
        add(storeNameTextField);

        createXMLStoreButton = new JButton("Create XML Store");
        createXMLStoreButton.setBounds(10, 40, 150, 25);
        createXMLStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XMLStoreManager xmlStoreManager = new XMLStoreManager();
                xmlStoreManager.createStore("nombreDelXMLStore");
            }
        });

        add(createXMLStoreButton);

        responseTextArea = new JTextArea();
        responseTextArea.setBounds(10, 80, 360, 170);
        responseTextArea.setEditable(false);
        add(responseTextArea);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientApplication client = new ClientApplication();
                client.setVisible(true);
            }
        });
    }
}
*/