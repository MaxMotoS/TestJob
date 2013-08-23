package ru.hh.school.testjob;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmployerResume extends Activity {

    TextView name;
    TextView birthday;
    TextView sex;
    TextView position;
    TextView salary;
    TextView phone;
    TextView email;
    EditText mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.resume_employer);

        Intent intent = getIntent();

        name = (TextView) findViewById(R.id.name);
        birthday = (TextView) findViewById(R.id.birthday);
        sex = (TextView) findViewById(R.id.sex);
        position = (TextView) findViewById(R.id.position);
        salary = (TextView) findViewById(R.id.salary);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        mail = (EditText) findViewById(R.id.mail);

        name.setText(intent.getStringExtra(ApplicantResume.FIELD_NAME));
        birthday.setText(intent.getStringExtra(ApplicantResume.FIELD_BIRTHDAY));
        sex.setText(intent.getStringExtra(ApplicantResume.FIELD_SEX));
        position.setText(intent.getStringExtra(ApplicantResume.FIELD_POSITION));
        salary.setText(intent.getStringExtra(ApplicantResume.FIELD_SALARY));
        phone.setText(intent.getStringExtra(ApplicantResume.FIELD_PHONE));
        email.setText(intent.getStringExtra(ApplicantResume.FIELD_EMAIL));

    }

    public void sendMail(View view) {

        Intent intent = new Intent(this, ApplicantResume.class);
        intent.putExtra(ApplicantResume.FIELD_NAME, name.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_BIRTHDAY, birthday.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_SEX, sex.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_POSITION, position.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_SALARY, salary.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_PHONE, phone.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_EMAIL, email.getText().toString());
        intent.putExtra(ApplicantResume.FIELD_RESPONSE, mail.getText().toString());
        startActivity(intent);

    }

}
