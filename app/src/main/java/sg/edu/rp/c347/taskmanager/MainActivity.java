package sg.edu.rp.c347.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Task> task;
    TaskAdapter aa;
    Button btnAdd;


    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);
        btnAdd = (Button)findViewById(R.id.btnAdd);




        DBHelper dbh = new DBHelper(this);

        task = dbh.getAllTasks();
        task.clear();
        task.addAll(task);
        dbh.close();

        aa = new TaskAdapter(this, R.layout.row, task);
        lv.setAdapter(aa);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 17);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 17  && data!= null) {

            Log.e("eee", "onActivityResult: ");
            DBHelper dbh = new DBHelper(MainActivity.this);
            task.clear();
            task.addAll(dbh.getAllTasks());
            dbh.close();
            aa.notifyDataSetChanged();
        }
    }
}
