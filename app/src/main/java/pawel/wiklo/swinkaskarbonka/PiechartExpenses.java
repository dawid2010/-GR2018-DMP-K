package pawel.wiklo.swinkaskarbonka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PiechartExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart_expenses);

        PieChart pieChart = findViewById(R.id.piechart);

        ArrayList kwoty = new ArrayList();

        kwoty.add(new BarEntry(1200f, 0));
        kwoty.add(new BarEntry(600f, 1));
        kwoty.add(new BarEntry(700f, 2));
        kwoty.add(new BarEntry(800f, 3));
        kwoty.add(new BarEntry(300f, 4));
        PieDataSet dataSet = new PieDataSet(kwoty, "Wydatki miesiąc");


        ArrayList wydatki = new ArrayList();

        wydatki.add("Mieszkania");
        wydatki.add("Samochód");
        wydatki.add("Uczelnia");
        wydatki.add("Jedzenie");
        wydatki.add("Rozrywka");



        PieData data = new PieData(wydatki, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);

        pieChart.setDescription("Wydatki na miesiac grudzien");
        pieChart.setUsePercentValues(true);

    }


}
