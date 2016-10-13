package com.server18.relaxationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    ArrayList<String> ss = new ArrayList<>();
    DatabaseHandler databaseHandler;
    ArrayAdapter<String> adapter;

    String corse;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

            databaseHandler =new DatabaseHandler(this);

        List<ModalData> studata = databaseHandler.getAllContacts();

        for(ModalData addStudent : studata){

          String  id = addStudent.getAuto_id();
            corse = addStudent.getVideo_id();
          ss.add(corse);
        }
      //  Bundle b = getIntent().getExtras();
        //String[] resultArr = b.getStringArray("selectedItems");

     /*   ArrayList<ModalData> myList = (ArrayList<ModalData>) getIntent().getSerializableExtra("Contact_list");

        for(ModalData modalData : myList){
            ss.add(modalData.getVideo_id());


        }*/
        ListView lv = (ListView) findViewById(R.id.outputList);

         adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ss);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);

                databaseHandler.deleteSingleRow(s);
                adapter.notifyDataSetChanged();

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {

          databaseHandler.deleteAllContacts();
            ss.clear();
            adapter.notifyDataSetChanged();


        }

        return super.onOptionsItemSelected(item);
    }
}
