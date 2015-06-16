package com.suprimart.suprimart;


import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;



public class MainActivity extends ActionBarActivity {

    private ListView listView1;
    public static ArrayList<Integer> Carrinho = new ArrayList();
    private ArrayList<String> resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //Pega a lista de produtos direto do servidor
        AsyncTaskParseJson a = new AsyncTaskParseJson();
        a.c = this;
        a.LV = (ListView) findViewById(R.id.list);
        a.pDialog = new ProgressDialog(MainActivity.this);
        a.execute();
        //fim

        //Instancia o botao CARRINHO
        Button but_carrinho = (Button) findViewById(R.id.but_carrinho);
        but_carrinho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentC = new Intent(MainActivity.this, VerCarrinho.class);
                intentC.putExtra("carrinho", Carrinho);
                startActivity(intentC);
            }
        });

        /*Instanacia o botao MINHA CONTA
        Button but_minhaconta = (Button) findViewById(R.id.but_minhaconta);
        but_carrinho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MinhaConta.class);
                intent.putExtra("carrinho", Carrinho);
                startActivity(intent);
            }
        });*/


    }



    //TODO WORKING CODE
    /*
    public void getJson(View view){
        //callServer("get-json", "");
    }


    private void degenerateJSON(String data){



        Produto [] pro = null;

        try{
            JSONObject jo = new JSONObject(data);
            JSONArray ja = jo.getJSONArray("produto");

            // loop through all users
            for (int i = 0; i < ja.length(); i++) {

                JSONObject c = ja.getJSONObject(i);

                // Storing each json item in variable
                String pro_nome = c.getString("pro_nome");
                String pro_tamanho = c.getString("pro_tamanho");
                Double pro_preco = c.getDouble("pro_preco");
                int pro_fotoprincipal = c.getInt("pro_fotoprincipal");
                int pro_codigo = c.getInt("pro_codigo");

                pro[i].Nome = pro_nome;
                pro[i].Preco = pro_preco;
                pro[i].Tamanho = pro_tamanho;
                pro[i].FotoPrincipal = pro_fotoprincipal;
                pro[i].Codigo = pro_codigo;

            }


            Produto proList[] = pro;
            ProdutoAdapter adapter = new ProdutoAdapter(this,
                    R.layout.produto, proList);


            listView1 = (ListView)findViewById(R.id.list);
            listView1.setAdapter(adapter);


        }
        catch(JSONException e){ e.printStackTrace(); }

    }
    @SuppressLint("NewApi")
    private void callServer(final String method, final String data){
        new Thread(){
            public void run(){
                String answer = HttpConnection.getSetDataWeb("http://suprimart.com/eloja/app/produto.php", method, data);

                Log.i("Script", "ANSWER: "+answer);

                if(data.isEmpty()){
                    degenerateJSON(answer);
                }
            }
        }.start();
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void AddCarrinho(View v){
        Button but_carrinho = (Button) findViewById(R.id.but_carrinho);
        Carrinho.add(Integer.parseInt((String) v.getTag()));
        but_carrinho.setText("Carrinho (" + Carrinho.size() + ")");
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
