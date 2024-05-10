package Modelo;

public interface Handler {
    public void setNext(Handler handler);
    public boolean handle();
}
