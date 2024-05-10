package Modelo;

import Herramientas.AnalizadorCodigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HaltChecker extends Maquina{

    private String program;
    private String imput;

    public HaltChecker(String program, String imput){
        super();
        this.program = program;
        this.imput = imput;

    }

    public HaltChecker(Maquina next,String program, String imput){
        this(program, imput);
        this.setNext(next);
    }

    public boolean handle(){
        int i;
        try {
            i= Integer.parseInt(imput);
        } catch (NumberFormatException e) {
            return true;
        }
        AnalizadorCodigo analizadorCodigo = new AnalizadorCodigo(program);
        return analizadorCodigo.analizar(i);
    }
}
