package sg.edu.rp.c347.taskmanager;

import java.io.Serializable;

/**
 * Created by 15017612 on 26/5/2017.
 */

public class Task implements Serializable{

    private int id;
    private String taskName;
    private String taskDescription;

    public int getId(){
        return id;
    }

    public String getTaskName(){
        return taskName;
    }

    public String getTaskDescription(){
        return taskDescription;
    }

    public Task (int id, String taskName, String taskDescription){
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

}
