package pawel.wiklo.swinkaskarbonka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Logowanie extends AppCompatActivity {

    EditText password;
    EditText email;
    TextView info;

    public void logowanie(View view) {
        if(email.getText().toString().equals("1") && password.getText().toString().equals("1")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{

           info.setText("Niepoprawny email lub hasło");
        }
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
}
