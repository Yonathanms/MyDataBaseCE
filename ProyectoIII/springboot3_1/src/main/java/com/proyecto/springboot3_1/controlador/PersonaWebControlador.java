package com.proyecto.springboot3_1.controlador;
///El controlador de una clase es el que permite comunicar el servidor y base de datos
/// al cliente, se usa un formato de comunicacion http

import com.proyecto.springboot3_1.modelo.Persona;
import com.proyecto.springboot3_1.servicios.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.List;
import com.fazecast.jSerialComm.SerialPort;

@RestController
public class PersonaWebControlador {

    ///importamos los servicios de esta clase al controlador
    @Autowired
    private PersonaServicio personaServicio;


    @RequestMapping("/")
    public String hola_mundo() {
        return "Hola Mundoo!!";   //metodo de prueba
    }


    ///metodo de agregar persona
    @PostMapping("/add")
    public String agregar_Persona(@RequestBody Persona persona) {
        personaServicio.agregar_persona(persona);
        return "Persona agregada con exito..";
    }

    ///metodo de obtener persona segun su id
    @RequestMapping("/consultapersona/{id}")
    public Persona verificar_personaID(@PathVariable("id") long id) {
        return personaServicio.verificar_personaID(id);
    }

    ///metodo que verifica si la persona ya fue registrada
    @RequestMapping("verificacion/{correo}/{clave}")
    public Boolean verificar_cuenta(@PathVariable("correo") String correo, @PathVariable("clave") String clave) {
        boolean verificado = personaServicio.verificar_cuenta(correo, clave);

        if (verificado) {
            System.out.println("Verificado");
            encenderBuzzer();
        } else {
            System.out.println("Denegado");
            encenderLED();
        }

        return verificado;
    }

    private void encenderBuzzer() {
        final String PUERTO_SERIAL = "COM3";
        final int TIMEOUT = 2000;

        SerialPort puertoSerial = null;
        SerialPort[] puertos = SerialPort.getCommPorts();

        for (SerialPort puerto : puertos) {
            if (puerto.getSystemPortName().equals(PUERTO_SERIAL)) {
                puertoSerial = puerto;
                break;
            }
        }

        if (puertoSerial == null) {
            System.out.println("No se pudo encontrar el puerto serial: " + PUERTO_SERIAL);
            return;
        }

        try {
            puertoSerial.openPort();
            puertoSerial.setBaudRate(9600);
            puertoSerial.setNumDataBits(8);
            puertoSerial.setNumStopBits(SerialPort.ONE_STOP_BIT);
            puertoSerial.setParity(SerialPort.NO_PARITY);

            OutputStream outputStream = puertoSerial.getOutputStream();

            // Encender el buzzer
            outputStream.write('B');
            outputStream.flush();

            // Esperar un tiempo para que suene el buzzer
            Thread.sleep(1000);

            // Apagar el buzzer
            outputStream.write('S');
            outputStream.flush();

            outputStream.close();
            puertoSerial.closePort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encenderLED() {

        final String PUERTO_SERIAL = "COM3";
        // Definir el tiempo de espera para establecer la conexión (en milisegundos)
        final int TIMEOUT = 2000;
        // Definir el tiempo de espera entre el encendido y apagado del LED (en milisegundos)
        final int DELAY_LED = 1000;
        // Obtener la instancia del puerto serial
        SerialPort puertoSerial = null;
        SerialPort[] puertos = SerialPort.getCommPorts();
        for (SerialPort puerto : puertos) {
            if (puerto.getSystemPortName().equals(PUERTO_SERIAL)) {
                puertoSerial = puerto;
                break;
            }
        }

        if (puertoSerial == null) {
            System.out.println("No se pudo encontrar el puerto serial: " + PUERTO_SERIAL);
            return;
        }

        try {
            // Abrir el puerto serial
            puertoSerial.openPort();
            // Configurar los parámetros del puerto serial
            puertoSerial.setBaudRate(9600);
            puertoSerial.setNumDataBits(8);
            puertoSerial.setNumStopBits(SerialPort.ONE_STOP_BIT);
            puertoSerial.setParity(SerialPort.NO_PARITY);

            // Obtener el flujo de salida del puerto serial
            OutputStream outputStream = puertoSerial.getOutputStream();

            // Encender el LED
            outputStream.write('H'); // Enviar un carácter 'H' al Arduino para encender el LED
            outputStream.flush();
            Thread.sleep(DELAY_LED);
            outputStream.write('L'); // Enviar un carácter 'L' al Arduino para apagar el LED
            outputStream.flush();

            // Cerrar el flujo de salida y el puerto serial
            outputStream.close();
            puertoSerial.closePort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //metodo de prueba
    ///metodo de obtener a todas las personas de la BD
    @RequestMapping("/personas")
    public List<Persona> get_Personas(){
        return personaServicio.get_personas();
    }

}


