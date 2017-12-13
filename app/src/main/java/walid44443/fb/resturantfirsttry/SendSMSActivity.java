package walid44443.fb.resturantfirsttry;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendSMSActivity extends AppCompatActivity {

    EditText phone, order;
    Button send;

    final private int REQUEST_SEND_SMS = 123;
    final private int REQUEST_REC_SMS = 321;
    BroadcastReceiver smsSentReceiver;
    IntentFilter intentFilter;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //---display the SMS received in the TextView---
            order = findViewById(R.id.order);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        phone = findViewById(R.id.phone);
        order = findViewById(R.id.order);
        send = findViewById(R.id.send);

        Intent i = getIntent();
        if (i.hasExtra("phone")) {
            phone.setText(i.getDoubleExtra("phone",0)+"");
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_SEND_SMS);
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    REQUEST_REC_SMS);
        }

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        registerReceiver(intentReceiver, intentFilter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //---register the receiver---
        //registerReceiver(intentReceiver, intentFilter);

    }

    @Override
    public void onPause() {
        super.onPause();

        //---unregister the receiver---
        unregisterReceiver(intentReceiver);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SendSMSActivity.this, "SEND Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SendSMSActivity.this, "SEND Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_REC_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SendSMSActivity.this, "RECEIVE Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SendSMSActivity.this, "RECEIVE Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void onClick(View v) {
        order = (EditText) findViewById(R.id.order);
        sendSMS(phone.getText().toString(), order.getText().toString());
        order.setText("");
        startActivity(new Intent(SendSMSActivity.this,MainActivity.class));
        Toast.makeText(this, "Message have send", Toast.LENGTH_LONG);

    }

    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        Log.i("phone",phoneNumber);
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }


}