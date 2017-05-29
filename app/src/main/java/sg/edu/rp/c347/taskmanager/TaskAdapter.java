package sg.edu.rp.c347.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> objects;
    private Context context;
    TextView tvName, tvDescription;


    public TaskAdapter(Context context, int resource,
                           ArrayList<Task> objects) {
        super(context, resource, objects);
        // Store the ArrayList of objects passed to this adapter
        this.objects = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    // getView() is called every time for every row
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //  Change R.layout.rowxyz if file is rowxyz.xml .
        View rowView = inflater.inflate(R.layout.row, parent, false);
        // Get the TextView object
        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDescription = (TextView)rowView.findViewById(R.id.tvDescription);

        // Parameter "pos" is the index of the
        //  row ListView is requesting.
        //  We get back the object at the same index.
        Task object = objects.get(pos);
        // Set the TextView to show the object info

        tvName.setText(object.getTaskName() + "");
        tvDescription.setText(object.getTaskDescription() + "");
        // Return this row that is being populated.
        return rowView;
    }
}



