package ru.hh.school.testjob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Max on 18.08.13.
 */
public class Alerts {
    private static class EmptyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface view, int buttonId) {

        }

    }
    public static void swowAlertError(String message, Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Ошибка");
        builder.setMessage(message);

        builder.setPositiveButton("OK", new EmptyListener());

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}