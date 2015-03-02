package network;

import android.app.DownloadManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.android.actionbarcompat.basic.MyApplication;

/**
 * Created by steffenfb on 26/02/15.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance=null;
    private RequestQueue mRequestQueue;


    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(MyApplication.getContext());

    }

    public static VolleySingleton getsInstance(){
        if(sInstance == null){
            sInstance = new VolleySingleton();

        }
       return sInstance;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

}
