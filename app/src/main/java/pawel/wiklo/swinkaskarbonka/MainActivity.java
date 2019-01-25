package pawel.wiklo.swinkaskarbonka;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static pawel.wiklo.swinkaskarbonka.Logowanie.GLOBAL_ACCOUNT_ID;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new WebServiceHandler().execute("api/LR?login=q&password=q");
        Log.d("GLOBAL_ACCOUNT_ID",GLOBAL_ACCOUNT_ID);

    }


    public void getData(View view) {
        //new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/Outcome?fbclid=IwAR056d1tqD9otgE5QqYoPm89rgJY1oaP55kRDmOloroDQYw8F_uwuq-1LSA");
        new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/LR?login=q&password=q");
        Log.d("DebugLog","1");
    }

    public void goToWykresy(View view) {
        Intent intent = new Intent(this, ChartsView.class);
        startActivity(intent);
    }

    public void goToUserList(View view) {
        Intent intent = new Intent(this, GetUserList.class);
        startActivity(intent);
    }

    public void getWydatek(View view) {
        //Intent intent = new Intent(this, addIncome.class);
        Intent intent = new Intent(this, dodawanieWydatkow.class);
        startActivity(intent);
    }

    public void goToDodawaniePrzychodu(View view) {
        Intent intent = new Intent(this, dodawaniePrzychodu.class);
        startActivity(intent);
    }


    private class WebServiceHandler extends AsyncTask<String, Void, String> {

    // okienko dialogowe, które każe użytkownikowi czekać
    private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

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
            Log.d(MainActivity.class.getSimpleName(), e.toString());
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

            JSONObject json = new JSONObject(result);

            Log.d("DebugLog","result:" + json);


                //Log.d("JON1", json.getJSONObject(i).toString());
                //Log.d("JON1", json.getJSONObject(i).getString("account_id"));

                 //GLOBAL_ACCOUNT_ID = json.getJSONObject(i).getString("account_id");








        } catch (Exception e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
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
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }

}