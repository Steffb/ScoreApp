package com.example.android.actionbarcompat.basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class second extends ActionBarActivity {

    TextView namefield;

    private File imagefile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("log","creating acitivye second");
        setContentView(R.layout.activity_menu);
        namefield = (TextView) findViewById(R.id.namefield);

        namefield.setText(getname(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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

    public void mymap(View v){
        Log.d("second","mymap running");
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    public void seeScore(View v ){
        Intent intent = new Intent(this, Points.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }public void toCloselist(View v){
        Intent intent = new Intent(this, CloseList.class);
        startActivity(intent);
    }

    public static String getname(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("mydata",MODE_PRIVATE);
        String name =  sharedPreferences.getString("name","");
        return name;

    }
    public void toNetwork(View veiw){
        Intent intent = new Intent(this,Network.class);
        startActivity(intent);
    }
    public void process(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        Uri tempuri = Uri.fromFile(imagefile);
        Log.d("second","this is the uri "+tempuri.toString());

        intent.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        startActivityForResult(intent,0);
        Log.d("second","process running");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("second","onActivityresult");


        if(requestCode == 0){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(true){
                        Log.d("second","first switch");

                        Toast.makeText(this,"Thanks for your contribution",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, PostBill.class);

                        startActivity(intent);

                    }else{
                        Toast.makeText(this,"There was an error ",Toast.LENGTH_LONG).show();

                    }

                    break;

                case Activity.RESULT_CANCELED:


                    break;

                default:
                    break;


            }
        }


    }
}
