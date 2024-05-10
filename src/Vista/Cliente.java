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
        this.contadorFinitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String program = leerFichero("Contadores/ContadorFinito.txt");
                Reverser reverser = new Reverser(program);
                if (reverser.handle()){
                    textArea1.setText("El programa se ha detenido");
                }else {
                    textArea1.setText("El programa es un bucle infinito");
                }
            }
        });

        this.contadorInfinitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String program = leerFichero("Contadores/ContadorInfinito.txt");
                Reverser reverser = new Reverser(program);
                if (reverser.handle()){
                    textArea2.setText("El programa se ha detenido");
                }else {
                    textArea2.setText("El programa es un bucle infinito");
                }
            }
        });

        this.reverserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String program = leerFichero("src/Modelo/Reverser.java");
                Reverser reverser = new Reverser(program);
                if (reverser.handle()){
                    textArea3.setText("El programa se ha detenido");
                }else {
                    textArea3.setText("El programa es un bucle infinito");
                }
            }
        });
    }

    private static String leerFichero(String ruta){
        StringBuilder programa = new StringBuilder();
        try {
            File archivo = new File(ruta);
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                programa.append(linea).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return programa.toString();

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cliente");
        frame.setContentPane(new Cliente().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}

