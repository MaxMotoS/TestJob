package ru.hh.school.testjob;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: max
 * Date: 18.08.13
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
    }

    public void openCvForm(View view) {
        Intent intent = new Intent(this, ApplicantResume.class);
        startActivity(intent);
    }

}