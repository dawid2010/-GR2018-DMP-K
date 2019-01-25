package pawel.wiklo.swinkaskarbonka;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Rejestracja extends AppCompatActivity {

    public void rejestracja_domostwa(View view) {
        Intent intent = new Intent(this, RejestracjaDomostwa.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
    }

    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EditText EditTextEmail = findViewById(R.id.editText4);
                    EditText EditTextPassword = findViewById(R.id.editText5);
                    EditText EditTextPasswordRepeat = findViewById(R.id.editText6);
                    EditText EditTextNazwiko = findViewById(R.id.editText7);
                    EditText EditTextImie = findViewById(R.id.editText8);
                    EditText EditTextKod = findViewById(R.id.generowanyKod);


                    String FirstName = EditTextImie.getText().toString();
                    String LastName = EditTextNazwiko.getText().toString();
                    String Password = EditTextPassword.getText().toString();
                    String PasswordRepeat = EditTextPasswordRepeat.getText().toString();
                    String Email = EditTextEmail.getText().toString();
                    String Kod = EditTextKod.getText().toString();


                    if(Password.toString().equals(PasswordRepeat.toString()))
                    {
                        String urlCustom = "http://swinkaskarbonka.somee.com/api/Skarbonka?frist_name="+FirstName.toString()+"&last_name="+LastName.toString()+"&password="+Password.toString()+"&email="+Email.toString()+"&kod="+Kod.toString();
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
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Podane hasła różnią się",
                                Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


    public void ZarejesstrujClick(View view) {
        sendPost();
    }
}
