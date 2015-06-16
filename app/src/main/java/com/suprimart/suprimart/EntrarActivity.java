package com.suprimart.suprimart;

import android.app.ProgressDialog;
import android.content.Context;
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


public class EntrarActivity extends ActionBarActivity {

    public static ArrayList<Integer> Carrinho = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrar);

        //Pega o carrinho proveniente da Activity anterior
        Intent intent = getIntent();
        Carrinho = intent.getIntegerArrayListExtra("carrinho");



        //Instanacia o botaoLOGIN
        Button but_entrar = (Button) findViewById(R.id.but_entrar);
        final Context a = this;

        but_entrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Pega os valores dos inputs de login
                EditText cEmail   = (EditText)findViewById(R.id.cli_email);
                EditText cSenha   = (EditText)findViewById(R.id.cli_senha);

                //TODO fazer verificacao do login
                GetLogin g = new GetLogin(cEmail.getText().toString(), cSenha.getText().toString());
                g.LV = (EditText)findViewById(R.id.elo_codigo);
                g.car = Carrinho;
                g.intent = new Intent(EntrarActivity.this, VerEnderecoActivity.class);
                g.pDialog = new ProgressDialog(EntrarActivity.this);
                g.c = a;
                g.execute();


                //Intent intent = new Intent(EntrarActivity.this, CadastrarClienteActivity.class);
                //intent.putExtra("carrinho", Carrinho);
                //startActivity(intent);
            }
        });


        //Instanacia o botao CRIAR CONTA
        Button but_criarconta = (Button) findViewById(R.id.but_criarconta);
        but_criarconta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent = new Intent(EntrarActivity.this, CadastrarClienteActivity.class);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entrar, menu);
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
