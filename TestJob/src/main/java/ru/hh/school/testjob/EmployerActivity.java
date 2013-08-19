package ru.hh.school.testjob;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmployerActivity extends Activity {

    public static final String ANSWER_MAIL = "Answer Mail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_employer);

        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.full_name);
        TextView birthDay = (TextView) findViewById(R.id.birth_day);
        TextView sex = (TextView) findViewById(R.id.sex);
        TextView office = (TextView) findViewById(R.id.office);
        TextView wages = (TextView) findViewById(R.id.wages);
        TextView phoneNumber = (TextView) findViewById(R.id.phone_number);
        TextView eMail = (TextView) findViewById(R.id.email);

        name.setText(intent.getStringExtra(ApplicantActivity.FIELD_NAME));
        birthDay.setText(intent.getStringExtra(ApplicantActivity.FIELD_BIRTH_DAY));
        sex.setText(intent.getStringExtra(ApplicantActivity.FIELD_SEX));
        office.setText(intent.getStringExtra(ApplicantActivity.FIELD_OFFICE));
        wages.setText(intent.getStringExtra(ApplicantActivity.FIELD_WAGES));
        phoneNumber.setText(intent.getStringExtra(ApplicantActivity.FIELD_PHONE_NUMBER));
        eMail.setText(intent.getStringExtra(ApplicantActivity.FIELD_EMAIL));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity2, menu);
        return true;
    }

    public void sendAnswer(View view) {

        Intent intent = new Intent(this, ApplicantActivity.class);
        intent.putExtra(ANSWER_MAIL, ((EditText) findViewById(R.id.answer_mail)).getText().toString());
        startActivity(intent);

    }

}
