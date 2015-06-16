package com.suprimart.suprimart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class VerCarrinho extends ActionBarActivity {

    public static ArrayList<Integer> Carrinho = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        //Pega o carrinho proveniente da Activity anterior
        Intent intent = getIntent();
        Carrinho = intent.getIntegerArrayListExtra("carrinho");

        //Pega a lista de produtos direto do servidor
        GetCarrinho c = new GetCarrinho();
        c.car = Carrinho;
        c.c = this;
        c.LV = (ListView) findViewById(R.id.list_carrinho);
        c.pDialog = new ProgressDialog(VerCarrinho.this);
        c.execute();
        //fim

        //modifica o texto presente no botao carrinho
        Button but_carrinho = (Button) findViewById(R.id.but_carrinho);
        but_carrinho.setText("Carrinho (" + Carrinho.size() + ")");


        //instancia o botao PRODUTOS
        Button but_produtos = (Button) findViewById(R.id.but_produto);
        but_produtos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent data = new Intent();
                //passa a variavel Carrinho de volta pros produtos
                data.putExtra("carrinho", Carrinho);
                setResult(2, data);
                finish();
            }
        });

        //Instanacia o botao FINALIZAR PEDIDO
        Button but_finalizarpedido = (Button) findViewById(R.id.but_finalizarpedido);
        but_finalizarpedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(VerCarrinho.this, EntrarActivity.class);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);
            }
        });

        /*Instanacia o botao MINHA CONTA
        Button but_minhaconta = (Button) findViewById(R.id.but_minhaconta);
        but_carrinho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(VerCarrinho.this, MinhaConta.class);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);
            }
        });*/





    }

    public void MudarTitulo(View v){

        TextView PagTitulo = (TextView)findViewById(R.id.pag_titulo);
        PagTitulo.setText((String) v.getTag());


    }
    public void VerCarrinho(View v){

        TextView PagTitulo = (TextView)findViewById(R.id.pag_titulo);
        PagTitulo.setText((String) v.getTag());
        int i;
        for( i = 0; i < Carrinho.size(); i++){
            Log.e("CARRINHO: ", String.valueOf(Carrinho.get(i)));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_carrinho, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
