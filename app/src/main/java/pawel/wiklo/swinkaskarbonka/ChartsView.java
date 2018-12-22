package pawel.wiklo.swinkaskarbonka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChartsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts_view);
    }

    public void barchartIncome(View view) {
        Intent intent = new Intent(this, BarchartIncome.class);
        startActivity(intent);
    }

    public void barchartExpenses(View view) {
        Intent intent = new Intent(this, BarchartExpenses.class);
        startActivity(intent);
    }

    public void piechartIncome(View view) {
        Intent intent = new Intent(this, PiechartIncome.class);
        startActivity(intent);
    }

    public void piechartExpenses(View view) {
        Intent intent = new Intent(this, PiechartExpenses.class);
        startActivity(intent);
    }
}
