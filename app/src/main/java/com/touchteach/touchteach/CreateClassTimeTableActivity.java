package com.touchteach.touchteach;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.coustomViews.PersianTimePickerDialog;
import com.touchteach.touchteach.tools.Class;

import java.util.Map;
//todo when click back settings is cleared
public class CreateClassTimeTableActivity extends AppCompatActivity {
    private Class setTimeClass;
    private CheckBox[] dayLabel = new CheckBox[7];
    private TextView[] startLabel = new TextView[7];
    private TextView[] endLabel = new TextView[7];
    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_time_table);
        setTimeClass = Class.intentLoad(getIntent());
        setView();
    }

    public void setTime(final View view){
        new PersianTimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (view instanceof TextView){
                    TextView textView = (TextView) view;
                    //todo use toString after Override it
                    textView.setText(i+":"+i1);

                    dayLabel[findDayArrayIndex(view)].setChecked(true);
                }
            }
        }).show();
    }

    public void saveClass(View view){
//        todo check correct input

        setTimeClass.clearTimeTable();
        for (int i=0 ; i<7 ; i++){
            if (dayLabel[i].isChecked() && !startLabel[i].getText().toString().isEmpty() && !endLabel[i].getText().toString().isEmpty()) {
                String start = startLabel[i].getText().toString();
                String end = endLabel[i].getText().toString();
                try {
                    String[] split = start.split(":");
                    int sh = Integer.parseInt(split[0]);
                    int sm = Integer.parseInt(split[1]);

                    split = end.split(":");
                    int eh = Integer.parseInt(split[0]);
                    int em = Integer.parseInt(split[1]);

                    setTimeClass.addDayToTimeTable(Class.DAY_TAGS[i], sh, sm, eh, em);
                }catch (Exception e){
                    Log.d("touch teach", start);
                    Log.d("touch teach", end);
                }
            }
        }

        progressBar.setVisibility(View.VISIBLE);

        setTimeClass.save(new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                progressBar.setVisibility(View.INVISIBLE);
                //todo return message to recourse
                Toast.makeText(CreateClassTimeTableActivity.this, "کلاس با موفقیت ثبت شد", Toast.LENGTH_LONG).show();
                CreateClassTimeTableActivity.this.finish();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
    }

    public void setView(){
        progressBar = (ProgressBar) findViewById(R.id.create_class_time_table_pb);

        // set day check box
        dayLabel[0] = (CheckBox)findViewById(R.id.create_class_time_table_tv_shanbe_label);
        dayLabel[1] = (CheckBox)findViewById(R.id.create_class_time_table_tv_ekshanbe_label);
        dayLabel[2] = (CheckBox)findViewById(R.id.create_class_time_table_tv_doshanbe_label);
        dayLabel[3] = (CheckBox)findViewById(R.id.create_class_time_table_tv_seshanbe_label);
        dayLabel[4] = (CheckBox)findViewById(R.id.create_class_time_table_tv_charshanbe_label);
        dayLabel[5] = (CheckBox)findViewById(R.id.create_class_time_table_tv_panjshanbe_label);
        dayLabel[6] = (CheckBox)findViewById(R.id.create_class_time_table_tv_jone_label);

        // set start time of day
        startLabel[0] = (TextView) findViewById(R.id.create_class_time_table_tv_s_shanbe);
        startLabel[1] = (TextView) findViewById(R.id.create_class_time_table_tv_s_ekshanbe);
        startLabel[2] = (TextView) findViewById(R.id.create_class_time_table_tv_s_doshanbe);
        startLabel[3] = (TextView) findViewById(R.id.create_class_time_table_tv_s_seshanbe);
        startLabel[4] = (TextView) findViewById(R.id.create_class_time_table_tv_s_charshanbe);
        startLabel[5] = (TextView) findViewById(R.id.create_class_time_table_tv_s_panjshanbe);
        startLabel[6] = (TextView) findViewById(R.id.create_class_time_table_tv_s_jone);

        // set end time of day
        endLabel[0] = (TextView) findViewById(R.id.create_class_time_table_tv_e_shanbe);
        endLabel[1] = (TextView) findViewById(R.id.create_class_time_table_tv_e_ekshanbe);
        endLabel[2] = (TextView) findViewById(R.id.create_class_time_table_tv_e_doshanbe);
        endLabel[3] = (TextView) findViewById(R.id.create_class_time_table_tv_e_seshanbe);
        endLabel[4] = (TextView) findViewById(R.id.create_class_time_table_tv_e_charshanbe);
        endLabel[5] = (TextView) findViewById(R.id.create_class_time_table_tv_e_panjshanbe);
        endLabel[6] = (TextView) findViewById(R.id.create_class_time_table_tv_e_jone);
    }

    public void uncheckDay(View view){
        if (!(view instanceof CheckBox))
            return;

        if (((CheckBox)view).isChecked())
            return;

        int index = findDayArrayIndex(view);
        if (index<0)
            return;

        startLabel[index].setText("");
        endLabel[index].setText("");
    }

    private int findDayArrayIndex(View view){
        int id = view.getId();
        for (int i=0 ; i < 7 ; i++){
            if (dayLabel[i].getId() == id || startLabel[i].getId() == id || endLabel[i].getId() == id)
                return i;
        }
        return -1;
    }
}
