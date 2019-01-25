package pawel.wiklo.swinkaskarbonka;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;

//import com.dev2qa.example.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static pawel.wiklo.swinkaskarbonka.Logowanie.GLOBAL_ACCOUNT_ID;

public class GetUserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_list);

        setTitle("User list");

        new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/Skarbonka?fbclid=IwAR2lTNB_fakviS0lFeYGLFXuoJYv2-xRGEn0aA_yN9z0QyQ6bh-QmtqFsy8");

    }

    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(GetUserList.this);

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

                TextView tv = ((TextView) findViewById(R.id.texvView));
                //tv.setText(exampleuserId+"\n"+exampleId+"\n"+exampleTitle+"\n"+exampleCompleted);
                //tv.setText(json.toString());

                for(int i = 0;i<json.length();i++)
                {
                    Log.d("JON1",json.getJSONObject(i).toString());
                    Log.d("JON1",json.getJSONObject(i).getString("first_name"));

                    String first_name = json.getJSONObject(i).getString("first_name");
                    //int value = json.getJSONObject(i).getInt("value");
                    String last_name = json.getJSONObject(i).getString("last_name");
                    //arrayList.add(name+" - "+value+" - "+data);

                    String account_id = json.getJSONObject(i).getString("account_id");

                    if(account_id.equals(GLOBAL_ACCOUNT_ID))
                    {
                        tv.setText( tv.getText()+"\n"+first_name+" "+last_name);
                    }

                }


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