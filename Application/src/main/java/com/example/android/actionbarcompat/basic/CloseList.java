package com.example.android.actionbarcompat.basic;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CloseList extends ActionBarActivity implements AdapterView.OnItemClickListener{
    ListView l;
    String[] stores = {"woodstock","Habbit","Blaze"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_list);
        l = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,stores);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_close_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp = (TextView) view;
        Toast.makeText(this,temp.getText(),Toast.LENGTH_SHORT).show();
        if(temp.getText().equals("woodstock")){
            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Resturant.class);
            String message = temp.getText().toString();
            intent.putExtra("resturantname", message);
            startActivity(intent);

        }
    }
}
