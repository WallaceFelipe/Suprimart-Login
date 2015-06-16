package com.suprimart.suprimart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// you can make this class as another java file so it will be separated from your main activity.
public class CadPedido extends AsyncTask<String, String, String> {

    ArrayList<Integer> lista = new ArrayList();

    public Context c = null;
    Intent intent = null;


    public TextView tTotal = null;
    public TextView tSubtotal = null;
    public TextView tFrete = null;
    public TextView tEndereco = null;
    public TextView tComplemento = null;
    public TextView tBairro = null;
    public TextView tTopo = null;


    public ArrayList<Integer> car = new ArrayList();
    public ArrayList<Integer> new_car = new ArrayList();

    public int elo_codigo = 0;
    EditText LV = null;
    Pedido ped = new Pedido();
    public ProgressDialog pDialog = null;
    int end = 0;
    //url de onde as informacoes serao tiradas
    public String yourJsonStringUrl = "http://suprimart.com/eloja/app/ped_cadastrar.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    public void setUrl(int eloja, ArrayList<Integer> pro, int end){

        //adiciona a eloja na url
        this.yourJsonStringUrl += "?elo_codigo="+eloja;

        this.end = end;

        //adiciona o endereco na url
        this.yourJsonStringUrl += "&end_codigo="+String.valueOf(end);

        //adiciona os produtos na url
        int a = 0;
        for(a = 0; a < pro.size(); a++){
            this.yourJsonStringUrl += "&pro[]=" + String.valueOf(pro.get(a));
        }

        this.elo_codigo = eloja;
        Log.e("URL: ", this.yourJsonStringUrl);

    }

    @Override
    protected void onPreExecute() {

        pDialog.setMessage("Processando...");
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
            Log.e("PED_FINAL: ", json.getString("pedido"));

            dataJsonArr = json.getJSONArray("pedido");
            Log.e("ARRAY: ",  dataJsonArr.toString());

            JSONObject c = dataJsonArr.getJSONObject(0);
            Log.e("ARRAY: ",  c.toString());

                // Storing each json item in variable
                ped.Codigo = c.getInt("ped_codigo");
                ped.Subtotal = c.getDouble("ped_subtotal");
                ped.Total = c.getDouble("ped_total");
                ped.Frete = c.getDouble("ped_frete");

                Log.e("PED TOTAL:", ped.toString());

                ped.end = new Endereco(this.end,
                        c.getString("end_endereco"),
                        c.getString("end_numero"),
                        c.getString("end_bairro"),
                        c.getString("end_complemento"),
                        c.getString("end_cep"),
                        this.elo_codigo);




        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg)  {
        pDialog.dismiss();

        Log.e("MSG: ", String.valueOf(ped.Total));
        tTotal.setText(String.valueOf(ped.Total));
        tSubtotal.setText(String.valueOf(ped.Subtotal));
        tFrete.setText(String.valueOf(ped.Frete));
        /*
        tEndereco.setText(ped.end.Endereco + ", N: "+ ped.end.Numero + " - CEP: " + ped.end.Cep);
        tBairro.setText(ped.end.Bairro);
        tComplemento.setText(ped.end.Complemento);

        */
        //tTopo.setText("Ver Pedido #" + String.valueOf(ped.Codigo));
        tTopo.setText("Pedido Registrado com Sucesso.");


        /*
        t[0].setText(String.valueOf(ped.Total));
        t[1].setText(String.valueOf(ped.Subtotal));
        t[2].setText(String.valueOf(ped.Frete));
        t[3].setText(String.valueOf(ped.end.Endereco + ", N: " + ped.end.Numero));
        t[4].setText(String.valueOf(ped.end.Complemento));
        t[5].setText(String.valueOf(ped.end.Bairro));
        t[6].setText(String.valueOf("Ver Pedido #"+ped.Codigo));*/


    }
}