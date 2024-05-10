package Modelo;

import Herramientas.AnalizadorCodigo;

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

    public boolean handle(){ //Este metodo se encarga de verificar si el programa se detiene
        int i; //Se declara una variable de tipo entero
        try {
            i= Integer.parseInt(imput); //Se convierte el imput a entero
        } catch (NumberFormatException e) { //Si hay un error
            return true; //Retorna verdadero
        }
        AnalizadorCodigo analizadorCodigo = new AnalizadorCodigo(program); //Se crea un objeto de la clase AnalizadorCodigo
        return analizadorCodigo.analizar(i); //Se analiza el codigo
    }
}
