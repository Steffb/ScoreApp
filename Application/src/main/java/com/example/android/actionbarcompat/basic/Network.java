package com.example.android.actionbarcompat.basic;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import network.VolleySingleton;


public class Network extends ActionBarActivity {

    TextView mTextView;
    //String url ="http://echo.jsontest.com/key/value/one/two";
    String url ="http://www.google.com";
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("network", "starting network");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        queue = VolleySingleton.getsInstance().getmRequestQueue();

        mTextView = (TextView) findViewById(R.id.message);
        //netTest();
        //jsontest();
        getpic();


    }

    public void post(View view) throws IOException, JSONException {
        JSONObject jsonobj = new JSONObject();

        jsonobj.put("name","steffen");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppostreq = new HttpPost("http://validate.jsontest.com/?json=");
        StringEntity se = new StringEntity(jsonobj.toString());
        se.setContentType("application/json;charset=UTF-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
        httppostreq.setEntity(se);
        HttpResponse httpresponse = httpclient.execute(httppostreq);
        String responseText = null;

        responseText = EntityUtils.toString(httpresponse.getEntity());

        Toast.makeText(MyApplication.getContext(),responseText,Toast.LENGTH_SHORT).show();
        JSONObject json = new JSONObject(responseText);




    }

    public void jsonpost(View view) throws JSONException, AuthFailureError {
        JSONObject j = new JSONObject();

        j.put("name","steffen");


        Log.d("o", "Find me");
        Log.d("o",j.toString());


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,"http://validate.jsontest.com/?json=",j,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {


                    Toast.makeText(MyApplication.getContext(),response.toString(),Toast.LENGTH_SHORT).show();

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Log.d("o",request.getBody().toString());
        Log.d("o",request.getHeaders().toString());


        Log.d("o",request.toString());

        queue.add(request);
    }

    public void jsontest(View view){
        Log.d("network","jsontest running");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,"http://echo.jsontest.com/key/value/one/two",null, new Response.Listener<JSONObject>(){

            public void onResponse(JSONObject response){

                try {
                    String s = response.getString("one");
                    Toast.makeText(MyApplication.getContext(),s,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network, menu);
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


public void netTest(){
    // Instantiate the RequestQueue.
    //RequestQueue queue = Volley.newRequestQueue(this);
    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    mTextView.setText("Response is: "+ response.substring(0,500));
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mTextView.setText("That didn't work!");
        }
            });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void getpic(){
        File f = new File("/storage/emulated/0/Pictures/test.jpg");
        Log.d("network","this file can be read"+ f.canRead());
        ImageView mImgView1 = (ImageView)findViewById(R.id.mImgView1);
        Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
        mImgView1.setImageBitmap(bmp);
    }

    public void sendpicture(View view){
        File f = new File("/storage/emulated/0/Pictures/test.jpg");
        Log.d("network","this file can be read"+ f.canRead());
        ImageView mImgView1 = (ImageView)findViewById(R.id.mImgView1);
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());


    //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
    byte [] byte_arr = stream.toByteArray();
    String image_str = Base64.encodeBytes(byte_arr);

    final ArrayList<NameValuePair> nameValuePairs = new  ArrayList<NameValuePair>();

    nameValuePairs.add(new BasicNameValuePair("image",image_str));

    Thread t = new Thread(new Runnable() {

        @Override
        public void run() {
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2/Upload_image_ANDROID/upload_image.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                final String the_string_response = convertResponseToString(response);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MyApplication.getContext(), "Response " + the_string_response, Toast.LENGTH_LONG).show();
                    }
                });

            }catch(final Exception e){
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MyApplication.getContext(), "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                System.out.println("Error in http connection "+e.toString());
            }
        }
    });
    t.start();
}

    public String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException{

        String res = "";
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = response.getEntity().getContent();
        final int contentLength = (int) response.getEntity().getContentLength(); //getting content length…..
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(), "contentLength : " + contentLength, Toast.LENGTH_LONG).show();
            }
        });

        if (contentLength < 0){
        }
        else{
            byte[] data = new byte[512];
            int len = 0;
            try
            {
                while (-1 != (len = inputStream.read(data)) )
                {
                    buffer.append(new String(data, 0, len)); //converting to string and appending  to stringbuffer…..
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                inputStream.close(); // closing the stream…..
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            res = buffer.toString();     // converting stringbuffer to string…..

            final String finalRes = res;
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(MyApplication.getContext(), "Result : " + finalRes, Toast.LENGTH_LONG).show();
                }
            });
            //System.out.println("Response => " +  EntityUtils.toString(response.getEntity()));
        }
        return res;
    }



}
