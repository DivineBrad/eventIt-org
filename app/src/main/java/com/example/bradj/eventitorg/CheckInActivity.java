package com.example.bradj.eventitorg;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bradj.eventitorg.Model.Entity.RegisteredEvent;
import com.example.bradj.eventitorg.Model.Service.ApiUtils;
import com.example.bradj.eventitorg.Model.Service.RegisteredEventService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInActivity extends AppCompatActivity {
    private TextView mTextView;
    private RegisteredEventService registeredEventService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        registeredEventService= ApiUtils.getRegisteredEventService();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        mTextView=(TextView)findViewById(R.id.txtResult);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);

            NdefMessage message = (NdefMessage) rawMessages[0]; // only one message transferred
//            mTextView.setText(new String(message.getRecords()[0].getPayload()));
            int regEveId=Integer.parseInt(new String(message.getRecords()[0].getPayload()));
            Log.i("", ""+regEveId);
            RegisteredEvent registeredEvent=new RegisteredEvent();
            registeredEvent.setRegId(regEveId);
            registeredEvent.setCheckedin(1);
            registeredEventService.updateRegisteredEvent(regEveId).enqueue(new Callback<RegisteredEvent>() {
                @Override
                public void onResponse(Call<RegisteredEvent> call, Response<RegisteredEvent> response) {
                    if(response.isSuccessful())
                        finish();
                    else
                        Toast.makeText(CheckInActivity.this, "Error Checking in activity", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<RegisteredEvent> call, Throwable t) {
                    Toast.makeText(CheckInActivity.this, "Error Checking in activity", Toast.LENGTH_SHORT).show();
                }
            });
        } else
            mTextView.setText("Waiting for NDEF Message");

    }
}
