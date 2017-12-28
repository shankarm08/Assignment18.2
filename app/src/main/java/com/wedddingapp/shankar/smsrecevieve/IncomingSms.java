package com.wedddingapp.shankar.smsrecevieve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shan on 12/28/2017.
 */

public class IncomingSms extends BroadcastReceiver
{
   final SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent)
    {
       final Bundle bundle = intent.getExtras();

        try
        {
            if(bundle != null)
            {
                Object[] pdusObj = (Object[])bundle.get("pdus");

                for(int i = 0; i < pdusObj.length ; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])pdusObj[i]);

                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: "+ phoneNumber + "; message: " + message);

                    Toast.makeText(context,"Sender Number : "+phoneNumber+"\n"+
                            "Message : "+message,Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }
}
