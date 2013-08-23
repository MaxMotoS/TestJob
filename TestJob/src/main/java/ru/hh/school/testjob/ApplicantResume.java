package ru.hh.school.testjob;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Max S.
 * Date: 21.08.13
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */

public class ApplicantResume extends FragmentActivity
        implements DatePickerFragment.OnDateSelectedListener {

    public static final String FIELD_NAME = "Name";
    public static final String FIELD_BIRTHDAY = "Birthday";
    public static final String FIELD_SEX = "Sex";
    public static final String FIELD_POSITION = "Office";
    public static final String FIELD_SALARY = "Salary";
    public static final String FIELD_PHONE = "Phone";
    public static final String FIELD_EMAIL = "E-mail";
    public static final String FIELD_RESPONSE = "Response";
    public static final int DIALOG_DATE = 1;

    EditText name;
    EditText birthday;
    Spinner sex;
    EditText position;
    EditText salary;
    EditText phone;
    EditText email;
    TextView responseLabel;
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.resume_applicant);

        name = (EditText) findViewById(R.id.name);
        birthday = (EditText) findViewById(R.id.birthday);
        sex = (Spinner) findViewById(R.id.sex);
        position = (EditText) findViewById(R.id.position);
        salary = (EditText) findViewById(R.id.salary);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        responseLabel = (TextView) findViewById(R.id.response_label);
        responseText = (TextView) findViewById(R.id.response_text);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.getStringExtra(FIELD_RESPONSE) != null) {

            name.setText(intent.getStringExtra(FIELD_NAME));
            birthday.setText(intent.getStringExtra(FIELD_BIRTHDAY));
// TODO реализовать поиск значения позиции в массиве пола
            position.setText(intent.getStringExtra(FIELD_POSITION));
            salary.setText(intent.getStringExtra(FIELD_SALARY));
            phone.setText(intent.getStringExtra(FIELD_PHONE));
            email.setText(intent.getStringExtra(FIELD_EMAIL));
            responseText.setText(intent.getStringExtra(FIELD_RESPONSE));

            responseLabel.setVisibility(View.VISIBLE);
            responseText.setVisibility(View.VISIBLE);
        }
    }

    public void sendResume(View view) {

        if (fillFields()) {

            Intent intent = new Intent(this, EmployerResume.class);

            intent.putExtra(FIELD_NAME, name.getText().toString());
            intent.putExtra(FIELD_BIRTHDAY, birthday.getText().toString());
            intent.putExtra(FIELD_SEX, sex.getAdapter().getItem(sex.getSelectedItemPosition()).toString());
            intent.putExtra(FIELD_POSITION, position.getText().toString());
            intent.putExtra(FIELD_SALARY, salary.getText().toString());
            intent.putExtra(FIELD_PHONE, phone.getText().toString());
            intent.putExtra(FIELD_EMAIL, email.getText().toString());

            startActivity(intent);

        }

    }

    private boolean fillFields() {

        if (name.getText().toString().equals("") ||
                birthday.getText().toString().equals("") ||
                sex.getSelectedItemPosition() == 0 ||
                position.getText().toString().equals("") ||
                salary.getText().toString().equals("") ||
                phone.getText().toString().equals("") ||
                email.getText().toString().equals("")) {

            Alerts.showAlertError("Все поля должны быть заполнены", this);
            return false;

        }

        String email = ((EditText) findViewById(R.id.email)).getText().toString();
// TODO Реализовать проверку е-мейла через регулярные выражения
        if (email.indexOf('.', email.indexOf('@')) < 0) {

            Alerts.showAlertError("проверьте поле E-Mail", this);
            return false;

        }

        return true;

    }

    public void selectDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSelected(int year, int month, int day) {
        birthday.setText(String.format("%02d.%02d.%04d", day, month, year));
    }
}
