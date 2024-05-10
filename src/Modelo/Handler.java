package Modelo;

// Esta interfaz es parte de la implementación del patrón Chain of Responsibility
public interface Handler {
    public void setNext(Handler handler);
    public boolean handle();
}
