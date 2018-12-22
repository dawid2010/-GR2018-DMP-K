package pawel.wiklo.swinkaskarbonka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarchartExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart_expenses);

        BarChart chart = findViewById(R.id.barchart);

        ArrayList kwoty = new ArrayList();

        kwoty.add(new BarEntry(1200f, 0));
        kwoty.add(new BarEntry(600f, 1));
        kwoty.add(new BarEntry(700f, 2));
        kwoty.add(new BarEntry(800f, 3));
        kwoty.add(new BarEntry(300f, 4));


        ArrayList wydatki = new ArrayList();

        wydatki.add("Mieszkania");
        wydatki.add("Samochód");
        wydatki.add("Uczelnia");
        wydatki.add("Jedzenie");
        wydatki.add("Rozrywka");


        BarDataSet bardataset = new BarDataSet(kwoty, "Wydatki miesiąc");
        chart.animateY(5000);
        BarData data = new BarData(wydatki, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

        chart.setDescription("Wydatki na miesiac grudzien");


    }
}
