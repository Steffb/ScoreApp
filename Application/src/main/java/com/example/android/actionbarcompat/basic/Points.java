package com.example.android.actionbarcompat.basic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Points extends ActionBarActivity {

    TextView namefield;
    TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        namefield = (TextView) findViewById(R.id.textView3);
        namefield.setText(second.getname(this));
        points =(TextView) findViewById(R.id.textView5);
        points.setText(""+setPoints(this));//setPoints(this)

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_points, menu);
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

    public void increasePoints(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("mydata", MODE_PRIVATE);
        Log.d("w","works here");

        int points = sharedPreferences.getInt("points", -1);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putInt("points",points+10);
        editor.commit();
        Log.d("w","committed");
        TextView p =(TextView) findViewById(R.id.textView5);
        p.setText(""+setPoints(this));//setPoints(this)


    }

    public int setPoints(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("mydata",MODE_PRIVATE);
        Log.d("w","works here");

        int points = sharedPreferences.getInt("points", -1);

        Log.d("w","works here2");

        if(points<0){
            //create it
            Log.d("w","works here3");
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            editor.putInt("points",0);
            editor.commit();
            return 0;

        }else{
            Log.d("w","works here4");
            return points;

        }



    }
}
