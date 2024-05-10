package Modelo;

public abstract class Maquina implements Handler{
    protected Handler next;

    public Maquina(){
        this.next = null;
    }

    public void setNext(Handler handler){
        this.next = handler;
    }

    public boolean handle(){
        if(next != null){
            next.handle();
            return true;
        }
        return false;
    }
}
