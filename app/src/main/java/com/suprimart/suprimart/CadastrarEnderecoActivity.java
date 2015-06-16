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

import java.util.ArrayList;


public class CadastrarEnderecoActivity extends ActionBarActivity {

    ArrayList<Integer> Carrinho = new ArrayList();
    public int elo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_endereco);

        //Pega o carrinho proveniente da Activity anterior
        Intent intent = getIntent();
        Carrinho = intent.getIntegerArrayListExtra("carrinho");

        this.elo = intent.getIntExtra("elo_codigo", -1);
        final Context a = this;

        EditText elo   = (EditText)findViewById(R.id.eloja);
        elo.setTag((String) String.valueOf(this.elo));
        final int codigo = this.elo;
        Button but_cadastrarEndereco = (Button) findViewById(R.id.but_salvar);

        but_cadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Pega os valores dos inputs
                EditText cEndereco   = (EditText)findViewById(R.id.endereco);
                EditText cNumero   = (EditText)findViewById(R.id.numero);
                EditText cComplemento   = (EditText)findViewById(R.id.complemento);
                EditText cBairro   = (EditText)findViewById(R.id.bairro);
                EditText cCep   = (EditText)findViewById(R.id.cep);
                EditText cEloja   = (EditText)findViewById(R.id.eloja);

                //

                Log.e("ERRO: ", (String) cEloja.getTag());

                CadEndereco g = new CadEndereco();
                g.setUrl( cEndereco.getText().toString(),
                        cComplemento.getText().toString(),
                        cNumero.getText().toString(),
                        cCep.getText().toString(),
                        cBairro.getText().toString(),
                        codigo);


                g.car = Carrinho;
                g.intent = new Intent(CadastrarEnderecoActivity.this, VerEnderecoActivity.class);
                g.pDialog = new ProgressDialog(CadastrarEnderecoActivity.this);
                g.c = a;
                g.execute();

                /*
                Intent intent = new Intent(CadastrarEnderecoActivity.this, VerEnderecoActivity.class);
                intent.putExtra("carrinho", elo_codigo);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);*/
            }
        });

        //TODO testando esse botao
        //Instanacia o botao CADASTRAR ENDERECO
        Button but_cancelar = (Button) findViewById(R.id.but_cancelar);
        but_cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(CadastrarEnderecoActivity.this, VerEnderecoActivity.class);
                intent.putExtra("elo_codigo", codigo);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar_endereco, menu);
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
