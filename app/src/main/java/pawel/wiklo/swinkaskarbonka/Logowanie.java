package pawel.wiklo.swinkaskarbonka;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logowanie extends AppCompatActivity {

    EditText password;
    EditText email;
    TextView info;

    public void logowanie(View view) {

        new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/Skarbonka?fbclid=IwAR2lTNB_fakviS0lFeYGLFXuoJYv2-xRGEn0aA_yN9z0QyQ6bh-QmtqFsy8");

    }

    public void rejestracja(View view) {
        Intent intent = new Intent(this, Rejestracja.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        email=(EditText) findViewById(R.id.emailText);
        password=(EditText)findViewById(R.id.passwordText);
        info=(TextView)findViewById(R.id.infoText);
    }

    public void getJson(View view) {
        new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/Skarbonka?fbclid=IwAR2lTNB_fakviS0lFeYGLFXuoJYv2-xRGEn0aA_yN9z0QyQ6bh-QmtqFsy8");
        Log.d("DebugLog","1");
    }
    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(Logowanie.this);

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

                //Map<String,String> mapa = new HashMap<>();

                for(int i = 1;i<json.length();i++) {
                    Log.d("JON1", json.getJSONObject(i).toString());
                    Log.d("JON1", json.getJSONObject(i).getString("password"));
                    Log.d("JON1", json.getJSONObject(i).getString("email"));

                    //mapa.put(json.getJSONObject(i).getString("email"),json.getJSONObject(i).getString("password"));
                    String name = json.getJSONObject(i).getString("email");
                    Log.d("JON1", String.valueOf(email.getText().toString().equals(name)));
                    String pass = json.getJSONObject(i).getString("password");
                    Log.d("JON1", String.valueOf(email.getText().toString().equals(pass)));

                    Log.d("JON1", email.getText().toString());
                    Log.d("JON1", password.getText().toString());

                    if(email.getText().toString().equals(name) && password.getText().toString().equals(pass) ) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{

                        info.setText("Niepoprawny email lub hasło");
                    }

                }

                String login;
                String haslo;

                //email.getText();

                //mapa.containsKey(email.getText());
                //mapa.containsValue(password.getText().toString());
                //Log.d("JON1", String.valueOf(mapa.containsKey(email.getText().toString())));
                //Log.d("JON1", String.valueOf(mapa.containsKey(password.getText().toString())));

//        if(mapa.containsKey(email.getText())==true && mapa.containsValue(password.getText())==true ) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }
//        else{
//
//           info.setText("Niepoprawny email lub hasło");
//        }



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
