package castor.pe.desappcastor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        ListView groupListView = (ListView) findViewById(R.id.groupListView);

        String[] items = { "Omar Diaz Esquivel","Alex Romero Paredes","Alberto Sanchez Seña","Lisseth Funes Tasayco","Marcos Lévano Arrue"};

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        //groupListView.setAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GroupActivity.this, R.layout.text_list_view, items);
        groupListView.setAdapter(adapter);
    }
}
