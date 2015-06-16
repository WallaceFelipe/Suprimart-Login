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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class VerPedidoActivity extends ActionBarActivity {

    ArrayList<Integer> Carrinho = new ArrayList();
    public int elo_codigo;
    public int endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_pedido);

        //Pega o carrinho proveniente da Activity anterior
        Intent intent = getIntent();
        Carrinho = intent.getIntegerArrayListExtra("carrinho");
        this.elo_codigo = intent.getIntExtra("elo_codigo", -1);
        this.endereco = intent.getIntExtra("endereco", -1);

        Log.e("ENDERECO PASSADO: ", String.valueOf(this.endereco));



        //Insere o pedido no BD
        CadPedido g = new CadPedido();

        g.setUrl(this.elo_codigo, Carrinho, this.endereco);
        g.car = Carrinho;

        //define as textviews
        g.tTotal = (TextView)findViewById(R.id.total);
        g.tSubtotal = (TextView)findViewById(R.id.ped_subtotal);
        g.tFrete = (TextView)findViewById(R.id.ped_frete);
        g.tEndereco = (TextView)findViewById(R.id.endereco);
        g.tComplemento = (TextView)findViewById(R.id.complemento);
        g.tBairro = (TextView)findViewById(R.id.bairro);
        g.tTopo = (TextView)findViewById(R.id.pag_titulo);

        g.intent = new Intent(VerPedidoActivity.this, MainActivity.class);
        g.pDialog = new ProgressDialog(VerPedidoActivity.this);
        g.c = this;
        g.execute();

        //Instanacia o botao Produtos
        Button but_voltar = (Button) findViewById(R.id.but_produto);
        but_voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(VerPedidoActivity.this, MainActivity.class);
                //intent.putExtra("elo_codigo", elo_codigo);
                //intent.putExtra("carrinho", Carrinho);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_pedido, menu);
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
