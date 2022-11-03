package models;

public class Conta {
    private int id;
    private int idUsuario;
    private double valorTotal;

    public Conta(int id, int idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.valorTotal = 0;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
