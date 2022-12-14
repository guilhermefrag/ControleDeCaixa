package database.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Movimentacao {

  private int id;
  private int id_usuario;
  private String tipo;
  private Double valor;
  private Date data_movimentacao;

  public Movimentacao() {}

  public Movimentacao(int id, int id_usuario, String tipo, Double valor) {
    this.id = id;
    this.id_usuario = id_usuario;
    this.tipo = tipo;
    this.valor = valor;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getIdUsuario() {
    return id_usuario;
  }

  public void setIdUsuario(int id_usuario) {
    this.id_usuario = id_usuario;
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

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public void setDataMovimentacao(Date data_movimentacao) {
    this.data_movimentacao = data_movimentacao;
  }

  public Date getDataMovimentacao() {
    return data_movimentacao;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub

    SimpleDateFormat std = new SimpleDateFormat();
    return (
      " Data movimentacao: " +
      std.format(this.getDataMovimentacao()) +
      " - Valor: " +
      this.getValor() +
      " - Tipo: " +
      this.getTipo()
    );
  }
}
