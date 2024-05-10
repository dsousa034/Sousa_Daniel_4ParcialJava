package Modelo;

// Esta clase es parte de la implementación del patrón Chain of Responsibility
public abstract class Maquina implements Handler{
    protected Handler next;

    public Maquina(){
        this.next = null;
    }

    public void setNext(Handler handler){
        this.next = handler;
    }

    public boolean handle(){ //Este metodo se encarga de manejar el siguiente
        if(next != null){ //Si el siguiente no es nulo
            next.handle(); //Se maneja el siguiente
            return true;
        }
        //Si el siguiente es nulo
        return false; //Retorna falso
    }
}
