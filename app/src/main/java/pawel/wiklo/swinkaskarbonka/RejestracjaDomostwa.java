package pawel.wiklo.swinkaskarbonka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
}
