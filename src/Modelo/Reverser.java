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

    public boolean handle(){
        setNext(new HaltChecker(program, program));
        if (next.handle()){
            while (true){
                break; //Esto en el problema de parada habria que eliminarlo
            }
        }
        return true;
    }

}
