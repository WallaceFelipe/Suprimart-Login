package com.suprimart.suprimart;

/**
 * Created by Joo on 09/06/2015.
 */
public class Endereco {

    public String Endereco;
    public String Numero;
    public String Bairro;
    public String Complemento;
    public String Cep;
    public int Cliente;
    public int Codigo;

    public Endereco(int codigo, String e, String n, String b, String c, String cep, int cli){
        this.Codigo = codigo;
        this.Endereco = e;
        this.Numero = n;
        this.Bairro = b;
        this.Complemento = c;
        this.Cep = cep;
        this.Cliente = cli;
    }
}
