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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getData(View view) {
        new WebServiceHandler().execute("https://jsonplaceholder.typicode.com/todos/1");
        Log.d("DebugLog","1");
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
            Log.d("DebugLog","5");
            // reprezentacja obiektu JSON w Javie
            JSONObject json = new JSONObject(result);
            Log.d("DebugLog","6");
            Log.d("DebugLog","result:" + json);

            String exampleuserId = json.getString("userId");
            Log.d("DebugLog","result2:" + exampleuserId);
            String exampleId = json.getString("userId");
            Log.d("DebugLog","result2:" + exampleId);
            String exampleTitle = json.getString("title");
            Log.d("DebugLog","result2:" + exampleTitle);
            String exampleCompleted = json.getString("completed");
            Log.d("DebugLog","result2:" + exampleCompleted);

            TextView tv = ((TextView) findViewById(R.id.texvView));
                tv.setText(exampleuserId+"\n"+exampleId+"\n"+exampleTitle+"\n"+exampleCompleted);


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