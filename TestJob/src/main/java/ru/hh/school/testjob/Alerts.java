package ru.hh.school.testjob;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Max on 18.08.13.
 */
public class Alerts {
    public static void swowAlertError(String message, Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Ошибка");
        builder.setMessage(message);

        builder.setPositiveButton("OK", new EmptyListener());

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}