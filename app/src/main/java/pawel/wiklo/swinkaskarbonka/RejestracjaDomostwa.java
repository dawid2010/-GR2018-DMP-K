package pawel.wiklo.swinkaskarbonka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RejestracjaDomostwa extends AppCompatActivity {

    public void generator(View view){
        char[] str = new char[100];

        for (int i = 0; i < 7; i++)
        {
            str[i] = (char) (((int)(Math.random() * 26)) + (int)'A');
        }

         generowany.setText(new String(str, 0, 7));
    }

    EditText generowany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja_domostwa);
        generowany=(EditText)findViewById(R.id.generowanyKod);
    }


    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EditText EditTextName = findViewById(R.id.editText4);
                    EditText EditTextKod = findViewById(R.id.generowanyKod);


                    String Name = EditTextName.getText().toString();
                    String Kod = EditTextKod.getText().toString();

                        String urlCustom = "http://swinkaskarbonka.somee.com/api/Account?name="+Name.toString()+"&kod="+Kod.toString();
                        Log.i("URL", urlCustom);
                        //URL url = new URL("http://swinkaskarbonka.somee.com/api/Outcome?value=650&name=studia&account_id=1&data=2019-01-21");
                        URL url = new URL(urlCustom);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept","application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);

                        JSONObject jsonParam = new JSONObject();

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
    public void ZarejetrujDom(View view) {
        sendPost();
    }
}
