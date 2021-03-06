package sg.edu.rp.c347.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText etName, etDescription, etSeconds;
    RadioGroup rgNotif;
    RadioButton rb;

    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        etName = (EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etSeconds = (EditText)findViewById(R.id.etSeconds);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rgNotif = (RadioGroup)findViewById(R.id.rgNotif);
                int selected = rgNotif.getCheckedRadioButtonId();
                rb = (RadioButton)rgNotif.findViewById(selected) ;
                Log.e("sdsd", rb.getText().toString());
                String name = etName.getText().toString();
                String desc = etDescription.getText().toString();
                int seconds = Integer.parseInt(etSeconds.getText().toString());
                DBHelper dbh = new DBHelper(AddActivity.this);
                dbh.insertTask(name, desc);
                dbh.close();
                Toast.makeText(AddActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                dbh.close();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, seconds);

                Intent intent = new Intent(AddActivity.this,
                        ScheduledNotification.class);
                intent.putExtra("name",name);
                intent.putExtra("desc",desc);
//                intent.putExtra("rbID",rbID);


                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);


                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
