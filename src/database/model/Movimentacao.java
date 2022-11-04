package database.model;

import java.sql.Date;

public class Movimentacao {
    private int id;
    private int idUsuario;
    private String tipo;
    private double valor;
    private Date data_movimentacao;

    public Movimentacao(int id, int idUsuario, String tipo, double valor) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.valor = valor;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_movimentacao() {
        return data_movimentacao;
    }
}
