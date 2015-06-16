package com.suprimart.suprimart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    Context contexto;
    int layoutResourceId;
    Produto dado[] = null;

    public ProdutoAdapter(Context contexto, int resource, Produto[] dado) {
        super(contexto, resource, dado);
        this.contexto = contexto;
        this.layoutResourceId = resource;
        this.dado = dado;
    }

    static class ProdutoHolder
    {
        ImageView cFoto;
        TextView cTitulo;
        TextView cTamanho;
        TextView cPreco;
        TextView cCodigo;
        Button cCarrinho;

    }

    public View getView(int posicao, View convertView, ViewGroup parent) {

        View row = convertView;
        ProdutoHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ProdutoHolder();
            //holder.cFoto = (ImageView)row.findViewById(R.id.pro_foto);
            holder.cTitulo = (TextView)row.findViewById(R.id.pro_nome);
            holder.cTamanho = (TextView)row.findViewById(R.id.pro_tamanho);
            holder.cPreco = (TextView)row.findViewById(R.id.pro_preco);
            holder.cCarrinho = (Button) row.findViewById(R.id.pro_carrinho);
            //holder.cCodigo = (TextView)row.findViewById(R.id.pro_codigo);

            row.setTag(holder);
        }
        else
        {
            holder = (ProdutoHolder)row.getTag();
        }

        Produto pro = dado[posicao];
        holder.cTitulo.setText(pro.Nome);
        holder.cTamanho.setText(pro.Tamanho);
        holder.cPreco.setText("R$ " + String.valueOf(pro.Preco));
        holder.cCarrinho.setTag(String.valueOf(pro.Codigo));

        return row;
    }


}
