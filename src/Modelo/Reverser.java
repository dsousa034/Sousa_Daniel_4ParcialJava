package Modelo;

public class Reverser extends Maquina{
    private String program;

    public Reverser(String program){
        super();
        this.program = program;
    }

    public Reverser(Maquina next, String program){
        this(program);
        this.setNext(next);
    }

    /**
     * Este metodo se encarga de invertir el programa
     * @return boolean
     */
    public boolean handle(){ //Este metodo se encarga de invertir el programa
        setNext(new HaltChecker(program, program)); //Se crea un objeto de la clase HaltChecker
        if (next.handle()){ //Si el programa se detiene
            while (true){ //Mientras sea verdadero
                break; //Se rompe el ciclo
                //Esto en el problema de parada habria que eliminarlo
            }
        }
        return true;
    }

}
