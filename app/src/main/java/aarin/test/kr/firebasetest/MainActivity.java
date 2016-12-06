package aarin.test.kr.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import static aarin.test.kr.firebasetest.R.id.fcm_title;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView fcmTitle;
    TextView fcmMsg;
    Button sendBtn;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("test");

        fcmTitle = (TextView) findViewById(fcm_title);
        fcmMsg = (TextView) findViewById(R.id.fcm_message);
        sendBtn = (Button) findViewById(R.id.send_btn);
        inputText = (EditText) findViewById(R.id.input_text);

        MyFirebaseMessagingService fms = new MyFirebaseMessagingService();
        fms.onCreate();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().getExtras() != null) {
                    for (String key : getIntent().getExtras().keySet()) {
                        Object value = getIntent().getExtras().get(key);
                        Log.d(TAG, "Key: " + key + " Value: " + value);
                    }
                }
            }
        });

    }


}
