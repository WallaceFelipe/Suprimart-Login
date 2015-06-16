package com.suprimart.suprimart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

// you can make this class as another java file so it will be separated from your main activity.
public class GetLogin extends AsyncTask<String, String, String> {

    ArrayList<Integer> lista = new ArrayList();

    public Context c = null;
    Intent intent = null;
    public ArrayList<Integer> car = new ArrayList();
    public int elo_codigo = 0;
    EditText LV = null;
    public ProgressDialog pDialog = null;

    //url de onde as informacoes serao tiradas
    String yourJsonStringUrl = "http://suprimart.com/eloja/app/login.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    public GetLogin(String email, String senha){

        this.yourJsonStringUrl += "?cli_email=" + email + "&cli_senha=" + senha;
        Log.e("URL: ", this.yourJsonStringUrl);

    }

    @Override
    protected void onPreExecute() {

        pDialog.setMessage("Verificando credenciais...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    public String doInBackground(String... arg0) {

        try {
            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);
            Log.e("LOGIN: ", String.valueOf(json.getInt("sucesso")));
            this.elo_codigo = json.getInt("sucesso");

        } catch (JSONException e) {
            e.printStackTrace();
        }



            /* get the array of users
            dataJsonArr = json.getJSONArray("produto");

            // loop through all users
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                // Storing each json item in variable
                String pro_nome = c.getString("pro_nome");
                String pro_tamanho = c.getString("pro_tamanho");
                Double pro_preco = c.getDouble("pro_preco");
                int pro_fotoprincipal = c.getInt("pro_fotoprincipal");
                int pro_codigo = c.getInt("pro_codigo");

                lista.add(new Produto(pro_nome, pro_tamanho, pro_preco, pro_fotoprincipal, pro_codigo));

            }*/


        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg)  {
        pDialog.dismiss();
        LV.setTag(this.elo_codigo);

        if(this.elo_codigo > 0){
            intent.putExtra("carrinho", this.car);
            intent.putExtra("elo_codigo", this.elo_codigo);
            c.startActivity(this.intent);
        }



    }
}