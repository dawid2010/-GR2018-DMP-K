package pawel.wiklo.swinkaskarbonka;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static pawel.wiklo.swinkaskarbonka.Logowanie.GLOBAL_ACCOUNT_ID;

public class dodawaniePrzychodu extends AppCompatActivity {


    ListView listView;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_przychodu);
        listView=(ListView)findViewById(R.id.listview);

        arrayList = new ArrayList<>();
        //arrayList.add("Zakupy");
        //arrayList.add("Obuwie");
        //arrayList.add("Zabawki");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);


        new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/Income/"+GLOBAL_ACCOUNT_ID+"?a=1");
        Log.d("DebugLog","1");


        Intent intent = getIntent();
        String kwotaValue = intent.getStringExtra("value");
        String nameValue = intent.getStringExtra("name");
        String dataValue = intent.getStringExtra("data");

        EditText EditTextKwota = findViewById(R.id.editTextKwota);
        EditTextKwota.setText(kwotaValue);

        EditText EditTextData = findViewById(R.id.editTextData);
        EditTextData.setText(dataValue);

        EditText EditTextNazwa = findViewById(R.id.editTextNazwa);
        EditTextNazwa.setText(nameValue);

    }

    public void kwotaButton(View view) {
        sendPost();
    }

    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EditText EditTextData = findViewById(R.id.editTextData);
                    EditText EditTextNazwa = findViewById(R.id.editTextNazwa);
                    EditText EditTextKwota = findViewById(R.id.editTextKwota);

                    String Data = EditTextData.getText().toString();
                    String Nazwa = EditTextNazwa.getText().toString();
                    String Kwota = EditTextKwota.getText().toString();

                    String urlCustom = "http://swinkaskarbonka.somee.com/api/Income?value="+Kwota+"&name="+Nazwa+"&account_id="+GLOBAL_ACCOUNT_ID+"&data="+Data;

                    //URL url = new URL("http://swinkaskarbonka.somee.com/api/Outcome?value=650&name=studia&account_id=1&data=2019-01-21");
                    URL url = new URL(urlCustom);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
//                    jsonParam.put("timestamp", 1488873360);
//                    jsonParam.put("uname", message.getUser());
//                    jsonParam.put("message", message.getMessage());
//                    jsonParam.put("latitude", 0D);
//                    jsonParam.put("longitude", 0D);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    public void ocrButton(View view) {

        EditText EditTextData = findViewById(R.id.editTextData);
        EditText EditTextNazwa = findViewById(R.id.editTextNazwa);

        String Data = EditTextData.getText().toString();
        String Nazwa = EditTextNazwa.getText().toString();

        Intent intent = new Intent(this, OcrCaptureActivity.class);
        intent.putExtra("name", Nazwa);
        intent.putExtra("data", Data);
        startActivity(intent);
    }


    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(dodawaniePrzychodu.this);

        // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        // mamy w niej dostęp do elementów UI
        @Override
        protected void onPreExecute() {
            // wyświetlamy okienko dialogowe każące czekać
            dialog.setMessage("Czekaj...");
            dialog.show();
        }

        // główna operacja, która wykona się w osobnym wątku
        // nie ma w niej dostępu do elementów UI
        @Override
        protected String doInBackground(String... urls) {

            try {
                Log.d("DebugLog","2");
                // zakładamy, że jest tylko jeden URL
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                // pobranie danych do InputStream
                InputStream in = new BufferedInputStream(connection.getInputStream());

                // konwersja InputStream na String
                // wynik będzie przekazany do metody onPostExecute()
                Log.d("DebugLog","3");
                return streamToString(in);

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(GetUserList.class.getSimpleName(), e.toString());
                return null;
            }

        }

        // metoda wykonuje się po zakończeniu metody głównej,
        // której wynik będzie przekazany;
        // w tej metodzie mamy dostęp do UI
        @Override
        protected void onPostExecute(String result) {
            Log.d("DebugLog","4");
            // chowamy okno dialogowe
            dialog.dismiss();

            try {
                Log.d("DebugLog","5");
                // reprezentacja obiektu JSON w Javie
                //JSONObject json = new JSONObject(result);
   /*             Log.d("DebugLog","6");
                Log.d("DebugLog","result:" + json);

                String exampleuserId = json.getString("userId");
                Log.d("DebugLog","result2:" + exampleuserId);
                String exampleId = json.getString("userId");
                Log.d("DebugLog","result2:" + exampleId);
                String exampleTitle = json.getString("title");
                Log.d("DebugLog","result2:" + exampleTitle);
                String exampleCompleted = json.getString("completed");
                Log.d("DebugLog","result2:" + exampleCompleted);*/


                JSONArray json = new JSONArray(result);

                //TextView tv = ((TextView) findViewById(R.id.texvView));
                //tv.setText(exampleuserId+"\n"+exampleId+"\n"+exampleTitle+"\n"+exampleCompleted);
                //tv.setText(json.toString());

                //json.getJSONObject(1).toString();
                Log.d("JON1",json.getJSONObject(1).toString());
                Log.d("JON1",json.getJSONObject(2).toString());

                arrayList = new ArrayList<>();

                for(int i = 0;i<json.length();i++)
                {
                    Log.d("JON1",json.getJSONObject(i).toString());
                    Log.d("JON1",json.getJSONObject(i).getString("name"));

                    String name = json.getJSONObject(i).getString("name");
                    int value = json.getJSONObject(i).getInt("value");
                    String data = json.getJSONObject(i).getString("date");
                    arrayList.add(name+" - "+value+" - "+data);
                }

                //arrayList = new ArrayList<>();
                //arrayList.add(json.toString());
                //arrayList.add("Obuwie");
                //arrayList.add("Zabawki");
                ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,arrayList);

                listView.setAdapter(arrayAdapter);


            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(GetUserList.class.getSimpleName(), e.toString());
                Log.d("DebugLog","failed");
            }
        }
    }
    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(GetUserList.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }
}
