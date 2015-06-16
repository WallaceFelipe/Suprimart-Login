package com.suprimart.suprimart;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// you can make this class as another java file so it will be separated from your main activity.
public class getEnderecos extends AsyncTask<String, String, String> {

    ArrayList<Endereco> lista = new ArrayList();

    public Context c = null;
    ListView LV = null;
    public ProgressDialog pDialog = null;
    int elo = 0;
    //url de onde as informacoes serao tiradas
    String yourJsonStringUrl = "http://suprimart.com/eloja/app/endereco.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    public void setCliente(int elo){
        this.elo = elo;
        this.yourJsonStringUrl += "?elo_codigo="+elo;
    }

    @Override
    protected void onPreExecute() {

        pDialog.setMessage("Carregando...");
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

            // get the array of users
            dataJsonArr = json.getJSONArray("endereco");

            // loop through all users
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);


                // Storing each json item in variable

                Log.e("ENDERECO: ", c.toString());
                int codigo = c.getInt("end_codigo");
                String endereco = c.getString("end_endereco");
                String numero = c.getString("end_numero");
                String complemento = c.getString("end_complemento");
                String bairro = c.getString("end_bairro");
                String cep = c.getString("end_cep");

                lista.add(new Endereco(codigo, endereco, numero, bairro, complemento, cep, this.elo));

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {
        pDialog.dismiss();

        Endereco[] vetor=(Endereco[]) lista.toArray(new Endereco[lista.size()]);
        /*
        Produto proList[] =  new Produto[]
                {
                        this.pro,
                        new Produto("Nome do produto", "Tamanho do Produto", 45.4, 1, 1),
                        new Produto("Nome do produto", "Tamanho do Produto", 45.4, 1, 1),
                        new Produto("Nome do produto", "Tamanho do Produto", 45.4, 1, 1),
                        new Produto("Nome do produto", "Tamanho do Produto", 45.4, 1, 1),
                        new Produto("Nome do produto", "Tamanho do Produto", 45.4, 1, 1)
                };

        */
        EnderecoAdapter adapter = new EnderecoAdapter(c,
                R.layout.endereco_item, vetor);
        LV.setAdapter(adapter);

    }
}