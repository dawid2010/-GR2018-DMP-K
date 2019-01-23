package pawel.wiklo.swinkaskarbonka;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarchartIncome extends AppCompatActivity {

        protected BarChart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart_income);

        BarChart chart = findViewById(R.id.barchart);

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

        BarDataSet bardataset = new BarDataSet(Earnings, "Zyski miesięczne");
        chart.animateY(5000);
        BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

        chart.setDescription("Przychod na rok 2018");





    }

    }