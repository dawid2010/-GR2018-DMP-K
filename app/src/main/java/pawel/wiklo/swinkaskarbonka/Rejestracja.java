package pawel.wiklo.swinkaskarbonka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
