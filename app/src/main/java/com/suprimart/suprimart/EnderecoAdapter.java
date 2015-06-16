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

public class EnderecoAdapter extends ArrayAdapter<Endereco> {

    Context contexto;
    int layoutResourceId;
    Endereco dado[] = null;

    public EnderecoAdapter(Context contexto, int resource, Endereco[] dado) {

        super(contexto, resource, dado);
        this.contexto = contexto;
        this.layoutResourceId = resource;
        this.dado = dado;
    }

    static class ProdutoHolder
    {

        TextView Endereco;
        Button Entregar;

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
            holder.Endereco = (TextView)row.findViewById(R.id.endereco);
            holder.Entregar = (Button) row.findViewById(R.id.but_entregar);
            //holder.cCodigo = (TextView)row.findViewById(R.id.pro_codigo);

            row.setTag(holder);
        }
        else
        {
            holder = (ProdutoHolder)row.getTag();
        }

        Endereco pro = dado[posicao];
        holder.Endereco.setText(pro.Endereco + ", N: "+pro.Numero);
        holder.Entregar.setTag(String.valueOf(pro.Codigo));

        return row;
    }


}
