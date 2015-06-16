package com.suprimart.suprimart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// you can make this class as another java file so it will be separated from your main activity.
public class CadEndereco extends AsyncTask<String, String, String> {

    ArrayList<Integer> lista = new ArrayList();

    public Context c = null;
    Intent intent = null;
    public ArrayList<Integer> car = new ArrayList();
    public int elo_codigo = 0;
    EditText LV = null;
    public ProgressDialog pDialog = null;

    //url de onde as informacoes serao tiradas
    public String yourJsonStringUrl = "http://suprimart.com/eloja/app/end_cadastrar.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    public void setUrl(String end, String com, String num, String cep, String bairro, int eloja){

        this.yourJsonStringUrl += "?endereco=" + end
                + "&complemento=" + com
                + "&numero=" + num
                + "&cep=" + cep
                + "&bairro=" + bairro
                + "&elo_codigo=" + eloja;

        this.elo_codigo = eloja;
        Log.e("URL: ", this.yourJsonStringUrl);

    }

    @Override
    protected void onPreExecute() {

        pDialog.setMessage("Salvando...");
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
            Log.e("LOGIN: ", json.getString("mensagem"));

            //this.elo_codigo = json.getInt("sucesso");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg)  {
        pDialog.dismiss();

        if(this.elo_codigo > 0){
            Log.e("CadEndereco.java:", String.valueOf(this.elo_codigo));
            intent.putExtra("carrinho", this.car);
            intent.putExtra("elo_codigo", this.elo_codigo);

            c.startActivity(this.intent);
        }



    }
}