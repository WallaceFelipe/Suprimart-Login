package com.suprimart.suprimart;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joo on 08/06/2015.
 */
public class Produto {

    public String Nome, Tamanho;
    public double Preco;
    public int FotoPrincipal, Codigo;

    public Produto(String nome, String tamanho, double preco, int fotoPrincipal, int codigo) {
        Nome = nome;
        Tamanho = tamanho;
        Preco = preco;
        FotoPrincipal = fotoPrincipal;
        Codigo = codigo;
    }

    public Produto(){
        Nome = "";
        Tamanho = "";
        Preco = 0.0;
        FotoPrincipal = 0;
        Codigo = 0;
    }
}
