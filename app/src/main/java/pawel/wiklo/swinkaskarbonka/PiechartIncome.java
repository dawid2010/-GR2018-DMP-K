package pawel.wiklo.swinkaskarbonka;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static pawel.wiklo.swinkaskarbonka.Logowanie.GLOBAL_ACCOUNT_ID;

public class PiechartIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart_income);

//
//        PieChart pieChart = findViewById(R.id.piechart);
//
//        ArrayList Earnings = new ArrayList();
//
//        Earnings.add(new BarEntry(3580f, 0));
//        Earnings.add(new BarEntry(4432f, 1));
//        Earnings.add(new BarEntry(2314f, 2));
//        Earnings.add(new BarEntry(6645f, 3));
//        Earnings.add(new BarEntry(7522f, 4));
//        Earnings.add(new BarEntry(1487f, 5));
//        Earnings.add(new BarEntry(1231f, 6));
//        Earnings.add(new BarEntry(4381f, 7));
//        Earnings.add(new BarEntry(4523f, 8));
//        Earnings.add(new BarEntry(3491f, 9));
//        Earnings.add(new BarEntry(3724f, 10));
//        Earnings.add(new BarEntry(2744f, 11));
//
//        PieDataSet dataSet = new PieDataSet(Earnings, "Zyski miesięczne");
//
//        ArrayList year = new ArrayList();
//
//        year.add("Styczeń");
//        year.add("Luty");
//        year.add("Marzec");
//        year.add("Kwiecień");
//        year.add("Maj");
//        year.add("Czerwiec");
//        year.add("Lipiec");
//        year.add("Sierpień");
//        year.add("Wrzesień");
//        year.add("Pazdziernik");
//        year.add("Listopad");
//        year.add("Grudzień");
//
//
//
//        PieData data = new PieData(year, dataSet);
//        pieChart.setData(data);
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        pieChart.animateXY(5000, 5000);
//
//        pieChart.setDescription("Przychod na rok 2018");
//        pieChart.setUsePercentValues(true);

        new WebServiceHandler().execute("http://swinkaskarbonka.somee.com/api/Income/"+GLOBAL_ACCOUNT_ID+"?a=1");

    }


    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(PiechartIncome.this);

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

                PieChart pieChart = findViewById(R.id.piechart);

                ArrayList kwoty = new ArrayList();
                ArrayList wydatki = new ArrayList();

                JSONArray json = new JSONArray(result);

                for(int i = 1;i<json.length();i++)
                {
                    Log.d("JON1",json.getJSONObject(i).toString());
                    Log.d("JON1",json.getJSONObject(i).getString("name"));

                    String name = json.getJSONObject(i).getString("name");
                    int value = json.getJSONObject(i).getInt("value");
                    String data = json.getJSONObject(i).getString("date");

                    kwoty.add(new BarEntry(value, i-1));

                    wydatki.add(name);

                }

                PieDataSet dataSet = new PieDataSet(kwoty, "Wydatki miesiąc");

                PieData data = new PieData(wydatki, dataSet);
                pieChart.setData(data);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieChart.animateXY(5000, 5000);

                pieChart.setDescription("Przychod");



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
