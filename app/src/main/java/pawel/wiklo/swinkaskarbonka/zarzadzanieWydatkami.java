package pawel.wiklo.swinkaskarbonka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class zarzadzanieWydatkami extends AppCompatActivity {

    public void getAddWydatek(View view) {
        Intent intent = new Intent(this, dodawanieWydatkow.class);
        startActivity(intent);
    }
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zarzadzanie_wydatkami);

        listView=(ListView)findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Zakupy codzienne - Aktualny - 21.12.2018 - 75.58");
        arrayList.add("Internet - Stały - 23.12.2018 - 85.58");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);
    }
}
