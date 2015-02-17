package com.example.android.actionbarcompat.basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Resturant extends Activity implements AdapterView.OnItemClickListener{
    ListView l;
    String[] products;
    String[] price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);
        String s = getIntent().getStringExtra("resturantname");
        Log.d("log",s);
        Resources res= getResources();
        products=res.getStringArray(R.array.products);
        price=res.getStringArray(R.array.price);


        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        TextView t = (TextView) findViewById(R.id.textView6);
        t.setText(s);
        l = (ListView) findViewById(R.id.listView2);
        MyAdapter myAdapter= new MyAdapter(this, products,price);
        l.setAdapter(myAdapter);
        l.setOnItemClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resturant, menu);
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

        Log.d("some","list clicked");
        Intent intent = new Intent(this, BuyConf.class);

        startActivity(intent);
    }
}    class MyAdapter extends ArrayAdapter<String>{

    Context context;
    String[] products;
    String[] prices;

    MyAdapter(Context c,String[] products,String[] prices){
        super(c,R.layout.singlerow,R.id.textView7,products);
        this.context = c;
        this.prices=prices;
        this.products=products;

    }public View getView(int position, View convertview, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.singlerow,parent,false);
        TextView myproduct = (TextView) row.findViewById(R.id.textView7);
        TextView myprice = (TextView) row.findViewById(R.id.textView8);

        myprice.setText(prices[position]);
        myproduct.setText(products[position]);


        return row;

    }

        }

