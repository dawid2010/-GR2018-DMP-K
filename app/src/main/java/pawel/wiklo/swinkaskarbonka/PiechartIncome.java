package pawel.wiklo.swinkaskarbonka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PiechartIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart_income);


        PieChart pieChart = findViewById(R.id.piechart);

        ArrayList Earnings = new ArrayList();

        Earnings.add(new BarEntry(3580f, 0));
        Earnings.add(new BarEntry(4432f, 1));
        Earnings.add(new BarEntry(2314f, 2));
        Earnings.add(new BarEntry(6645f, 3));
        Earnings.add(new BarEntry(7522f, 4));
        Earnings.add(new BarEntry(1487f, 5));
        Earnings.add(new BarEntry(1231f, 6));
        Earnings.add(new BarEntry(4381f, 7));
        Earnings.add(new BarEntry(4523f, 8));
        Earnings.add(new BarEntry(3491f, 9));
        Earnings.add(new BarEntry(3724f, 10));
        Earnings.add(new BarEntry(2744f, 11));

        PieDataSet dataSet = new PieDataSet(Earnings, "Zyski miesięczne");

        ArrayList year = new ArrayList();

        year.add("Styczeń");
        year.add("Luty");
        year.add("Marzec");
        year.add("Kwiecień");
        year.add("Maj");
        year.add("Czerwiec");
        year.add("Lipiec");
        year.add("Sierpień");
        year.add("Wrzesień");
        year.add("Pazdziernik");
        year.add("Listopad");
        year.add("Grudzień");



        PieData data = new PieData(year, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);

        pieChart.setDescription("Przychod na rok 2018");
        pieChart.setUsePercentValues(true);
    }
}
