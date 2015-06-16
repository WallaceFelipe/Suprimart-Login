package com.suprimart.suprimart;

/**
 * Created by Joo on 10/06/2015.
 */
public class Pedido {

    public double Subtotal;
    public double Total;
    public double Frete;
    public int Codigo;

    public Endereco end;

    public Pedido(double subtotal, double total, double frete, int codigo, Endereco end) {
        Subtotal = subtotal;
        Total = total;
        Frete = frete;
        Codigo = codigo;
        this.end = end;
    }
    public Pedido() {
        Subtotal = 0.0;
        Total = 0.0;
        Frete = 0.0;
        Codigo = 0;
        this.end = null;
    }
}
