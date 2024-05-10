package Vista;

import Modelo.Reverser;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Cliente {
    private JButton contadorFinitoButton;
    private JButton button2;
    private JButton contadorInfinitoButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel Ventana;
    private JButton reverserButton;
    private JTextArea textArea3;

    public Cliente() {
        // Eventos del botón contadorFinitoButton
        this.contadorFinitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String program = leerFichero("Contadores/ContadorFinito.txt"); // Darle la ruta del fichero ContadorFinito
                Reverser reverser = new Reverser(program); // Crear un objeto de la clase Reverser
                if (reverser.handle()){ // Si el programa se detiene
                    textArea1.setText("El programa se ha detenido"); // Mostrar que el programa se ha detenido
                }else {
                    textArea1.setText("El programa es un bucle infinito"); // Mostrar que el programa es un bucle infinito
                }
            }
        });

        // Evento del botón contadorInfinitoButton
        this.contadorInfinitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String program = leerFichero("Contadores/ContadorInfinito.txt"); // Darle la ruta del fichero ContadorInfinito
                Reverser reverser = new Reverser(program); // Crear un objeto de la clase Reverser
                if (reverser.handle()){ // Si el programa se detiene
                    textArea2.setText("El programa se ha detenido"); // Mostrar que el programa se ha detenido
                }else {
                    textArea2.setText("El programa es un bucle infinito"); // Mostrar que el programa es un bucle infinito
                }
            }
        });

        // Evento del botón reverserButton
        this.reverserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String program = leerFichero("src/Modelo/Reverser.java");  // Darle la ruta del fichero Reverser
                Reverser reverser = new Reverser(program); // Crear un objeto de la clase Reverser
                if (reverser.handle()){ // Si el programa se detiene
                    textArea3.setText("El programa se ha detenido"); // Mostrar que el programa se ha detenido
                }else {
                    textArea3.setText("El programa es un bucle infinito"); // Mostrar que el programa es un bucle infinito
                }
            }
        });
    }

    /**
     * Lee un fichero y devuelve su contenido
     * @param ruta ruta del fichero
     * @return contenido del fichero
     */
    private static String leerFichero(String ruta){ //Lee un fichero y devuelve su contenido
        StringBuilder programa = new StringBuilder(); //Variable para guardar el programa
        try { //Intentar leer el fichero
            File archivo = new File(ruta); //Fichero a leer
            BufferedReader br = new BufferedReader(new FileReader(archivo)); //BufferedReader para leer el archivo
            String linea; //Variable para leer cada linea
            while ((linea = br.readLine()) != null) { //Mientras haya lineas que leer
                programa.append(linea).append("\n"); //Añadir la linea al programa
            }
            br.close(); //Cerrar el BufferedReader
        } catch (IOException e) { //Excepción en caso de error
            e.printStackTrace(); //Imprimir el error
        }
        return programa.toString(); //Devolver el programa

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cliente");
        frame.setContentPane(new Cliente().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}

