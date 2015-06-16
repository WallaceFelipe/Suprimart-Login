package com.suprimart.suprimart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MinhaConta extends ActionBarActivity {

    public static ArrayList<Integer> Carrinho = new ArrayList();
    private GetCarrinho c = new GetCarrinho();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        //Pega o carrinho proveniente da Activity anterior
        Intent intent = getIntent();
        Carrinho = intent.getIntegerArrayListExtra("carrinho");

        //Pega a lista de produtos direto do servidor

        c.car = Carrinho;
        c.c = this;
        c.LV = (ListView) findViewById(R.id.list_carrinho);
        c.pDialog = new ProgressDialog(MinhaConta.this);
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
                Intent intent = new Intent(MinhaConta.this, EntrarActivity.class);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);
            }
        });

        /*Instanacia o botao MINHA CONTA
        Button but_minhaconta = (Button) findViewById(R.id.but_minhaconta);
        but_carrinho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MinhaConta.this, VerCarrinho.class);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);

            }
        });*/





    }

    public void RemoverCarrinho(View v){
        Carrinho.remove(Carrinho.indexOf(Integer.parseInt((String) v.getTag())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_minha_conta, menu);
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
