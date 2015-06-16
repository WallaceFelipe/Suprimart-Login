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
import android.widget.ListView;

import java.util.ArrayList;


public class VerEnderecoActivity extends ActionBarActivity {

    ArrayList<Integer> Carrinho = new ArrayList();
    public int elo_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_endereco);

        //Pega o carrinho proveniente da Activity anterior
        Intent intent = getIntent();
        Carrinho = intent.getIntegerArrayListExtra("carrinho");
        this.elo_codigo = intent.getIntExtra("elo_codigo", -1);

        Log.e("CODIGO FINAL:", String.valueOf(this.elo_codigo));

        //le os enderecos do BD
        getEnderecos a = new getEnderecos();
        a.setCliente(this.elo_codigo);
        a.c = this;
        a.LV = (ListView) findViewById(R.id.list_enderecos);
        a.pDialog = new ProgressDialog(VerEnderecoActivity.this);
        a.execute();
        //FIM

        //Instanacia o botao CADASTRAR ENDERECO
        Button but_cadastrarEndereco = (Button) findViewById(R.id.but_novoendereco);
        but_cadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(VerEnderecoActivity.this, CadastrarEnderecoActivity.class);
                intent.putExtra("elo_codigo", elo_codigo);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);

            }
        });

        /*Instanacia os botoes Entregar
        Button but_entregar = (Button) findViewById(R.id.but_entregar);
        but_cadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //EntregarCompra()
            }
        });*/

    }

    public void EntregarCompra(View v){


        Log.e("Entregar em: ",(String) v.getTag());

        Intent intent = new Intent(VerEnderecoActivity.this, VerPedidoActivity.class);
        intent.putExtra("elo_codigo", this.elo_codigo);
        intent.putExtra("endereco", Integer.parseInt((String) v.getTag()));
        intent.putExtra("carrinho", this.Carrinho);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_endereco, menu);
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
