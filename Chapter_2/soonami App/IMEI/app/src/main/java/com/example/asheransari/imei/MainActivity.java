package com.example.asheransari.imei;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private TextView loading_tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        //trigger 'loadIMEI'
//        loadIMEI();
//        /** Fading Transition Effect */
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        Toast.makeText(MainActivity.this,getIMEI(MainActivity.this),Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this,getDeviceUniqueID(MainActivity.this),Toast.LENGTH_LONG).show();

        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String countryCode = tm.getSimCountryIso();

        Toast.makeText(MainActivity.this,countryCode,Toast.LENGTH_LONG).show();
    }

    public String getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
//    to get device unique id

    public String getDeviceUniqueID(Activity activity){
        String device_unique_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return device_unique_id;
    }


    ////this is another code...




    /**
     * Called when the 'loadIMEI' function is triggered.
     */
//    public void loadIMEI() {
//        // Check if the READ_PHONE_STATE permission is already available.
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // READ_PHONE_STATE permission has not been granted.
//            requestReadPhoneStatePermission();
//        } else {
//            // READ_PHONE_STATE permission is already been granted.
//            doPermissionGrantedStuffs();
//        }
//    }
//
//
//
//    /**
//     * Requests the READ_PHONE_STATE permission.
//     * If the permission has been denied previously, a dialog will prompt the user to grant the
//     * permission, otherwise it is requested directly.
//     */
//    private void requestReadPhoneStatePermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.READ_PHONE_STATE)) {
//            // Provide an additional rationale to the user if the permission was not granted
//            // and the user would benefit from additional context for the use of the permission.
//            // For example if the user has previously denied the permission.
//            new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("Permission Request")
//                    .setMessage(getString(R.string.permission_read_phone_state_rationale))
//                    .setCancelable(false)
//                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            //re-request
//                            ActivityCompat.requestPermissions(MainActivity.this,
//                                    new String[]{Manifest.permission.READ_PHONE_STATE},
//                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//                        }
//                    })
////                    .setIcon(R.drawable)
//                    .show();
//        } else {
//            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
//                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//        }
//    }
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//
//        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {
//            // Received permission result for READ_PHONE_STATE permission.est.");
//            // Check if the only required permission has been granted
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
//                //alertAlert(getString(R.string.permision_available_read_phone_state));
//                doPermissionGrantedStuffs();
//            } else {
//                alertAlert(getString(R.string.permissions_not_granted_read_phone_state));
//            }
//        }
//    }
//
//    private void alertAlert(String msg) {
//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle("Permission Request")
//                .setMessage(msg)
//                .setCancelable(false)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do somthing here
//                    }
//                })
////                .setIcon(R.drawable.onlinlinew_warning_sign)
//                .show();
//    }
//
//
//    public void doPermissionGrantedStuffs() {
//        //Have an  object of TelephonyManager
//        TelephonyManager tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
//        String IMEINumber=tm.getDeviceId();
//
//        /************************************************
//         * **********************************************
//         * This is just an icing on the cake
//         * the following are other children of TELEPHONY_SERVICE
//         *
//         //Get Subscriber ID
//         String subscriberID=tm.getDeviceId();
//
//         //Get SIM Serial Number
//         String SIMSerialNumber=tm.getSimSerialNumber();
//
//         //Get Network Country ISO Code
//         String networkCountryISO=tm.getNetworkCountryIso();
//
//         //Get SIM Country ISO Code
//         String SIMCountryISO=tm.getSimCountryIso();
//
//         //Get the device software version
//         String softwareVersion=tm.getDeviceSoftwareVersion()
//
//         //Get the Voice mail number
//         String voiceMailNumber=tm.getVoiceMailNumber();
//
//
//         //Get the Phone Type CDMA/GSM/NONE
//         int phoneType=tm.getPhoneType();
//
//         switch (phoneType)
//         {
//         case (TelephonyManager.PHONE_TYPE_CDMA):
//         // your code
//         break;
//         case (TelephonyManager.PHONE_TYPE_GSM)
//         // your code
//         break;
//         case (TelephonyManager.PHONE_TYPE_NONE):
//         // your code
//         break;
//         }
//
//         //Find whether the Phone is in Roaming, returns true if in roaming
//         boolean isRoaming=tm.isNetworkRoaming();
//         if(isRoaming)
//         phoneDetails+="\nIs In Roaming : "+"YES";
//         else
//         phoneDetails+="\nIs In Roaming : "+"NO";
//
//
//         //Get the SIM state
//         int SIMState=tm.getSimState();
//         switch(SIMState)
//         {
//         case TelephonyManager.SIM_STATE_ABSENT :
//         // your code
//         break;
//         case TelephonyManager.SIM_STATE_NETWORK_LOCKED :
//         // your code
//         break;
//         case TelephonyManager.SIM_STATE_PIN_REQUIRED :
//         // your code
//         break;
//         case TelephonyManager.SIM_STATE_PUK_REQUIRED :
//         // your code
//         break;
//         case TelephonyManager.SIM_STATE_READY :
//         // your code
//         break;
//         case TelephonyManager.SIM_STATE_UNKNOWN :
//         // your code
//         break;
//
//         }
//         */
//        // Now read the desired content to a textview.
//        loading_tv2 = (TextView) findViewById(R.id.abc);
//        loading_tv2.setText(IMEINumber);
//    }
}
