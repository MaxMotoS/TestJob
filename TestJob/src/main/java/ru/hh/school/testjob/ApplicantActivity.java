package ru.hh.school.testjob;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ApplicantActivity extends Activity {

    public static final String FIELD_NAME = "Name";
    public static final String FIELD_BIRTH_DAY = "Birth Day";
    public static final String FIELD_SEX = "Sex";
    public static final String FIELD_OFFICE = "Office";
    public static final String FIELD_WAGES = "Wages";
    public static final String FIELD_PHONE_NUMBER = "Phone Number";
    public static final String FIELD_EMAIL = "E-mail";
    public static final int DIALOG_DATE = 1;

    int myYear = 2013;
    int myMonth = 8;
    int myDay = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();

        String answer = intent.getStringExtra(EmployerActivity.ANSWER_MAIL);
        if (answer != null) {
            setContentView(R.layout.activity_applicant_answer);
            TextView textView_answer = (TextView) findViewById(R.id.answer);
            textView_answer.setText(answer);
        } else {
            setContentView(R.layout.activity_applicant);
            Spinner sex_spinner = (Spinner) findViewById(R.id.sex);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sex_spinner.setAdapter(adapter);
        }
    }

    public void sendCV(View view) {

        if (fillFields()) {
            Intent intent = new Intent(this, EmployerActivity.class);
            intent.putExtra(FIELD_NAME, ((EditText) findViewById(R.id.full_name)).getText().toString());
            intent.putExtra(FIELD_BIRTH_DAY, ((EditText) findViewById(R.id.birth_day)).getText().toString());

            Spinner sex_spinner = (Spinner) findViewById(R.id.sex);
            int sex_spinner_position = sex_spinner.getSelectedItemPosition();
            String sex_current_string = sex_spinner.getAdapter().getItem(sex_spinner_position).toString();

            intent.putExtra(FIELD_SEX, sex_current_string);
            intent.putExtra(FIELD_OFFICE, ((EditText) findViewById(R.id.office)).getText().toString());
            intent.putExtra(FIELD_WAGES, ((EditText) findViewById(R.id.wages)).getText().toString());
            intent.putExtra(FIELD_PHONE_NUMBER, ((EditText) findViewById(R.id.phone_number)).getText().toString());
            intent.putExtra(FIELD_EMAIL, ((EditText) findViewById(R.id.email)).getText().toString());
            startActivity(intent);
        }

    }

    private boolean fillFields() {
        if (((EditText) findViewById(R.id.full_name)).getText().toString().equals("") ||
                ((EditText) findViewById(R.id.birth_day)).getText().toString().equals("") ||
                ((Spinner) findViewById(R.id.sex)).getSelectedItemPosition() == 0 ||
                ((EditText) findViewById(R.id.office)).getText().toString().equals("") ||
                ((EditText) findViewById(R.id.wages)).getText().toString().equals("") ||
                ((EditText) findViewById(R.id.phone_number)).getText().toString().equals("") ||
                ((EditText) findViewById(R.id.email)).getText().toString().equals("")) {
            Alerts.showAlertError("Все поля должны быть заполнены", this);
            return false;
        }

        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        if (email.indexOf('.', email.indexOf('@')) < 0) {
            Alerts.showAlertError("проверьте поле E-Mail", this);
            return false;
        }
        return true;
    }
    public void onclick(View view) {
        showDialog(DIALOG_DATE);
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog dpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return dpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            ((EditText) findViewById(R.id.birth_day)).setText(String.format("%02d.%02d.%04d", myDay, myMonth, myYear));
        }
    };

}
