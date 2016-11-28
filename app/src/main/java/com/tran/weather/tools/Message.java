package com.tran.weather.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
