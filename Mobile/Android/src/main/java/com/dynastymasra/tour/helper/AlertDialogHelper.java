package com.dynastymasra.tour.helper;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.domain.Content;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class AlertDialogHelper {
    private static final int MESSAGE_ALERT = 1;
    private static final int CONFIRM_ALERT = 2;
    private static final int DECISION_ALERT = 3;
    private static final int INFORMATION_ALERT = 4;

    public static void messageAlert(Context context, String title, String message) {
        showAlertDialog(MESSAGE_ALERT, context, title, message, null, "OK");
    }

    public static void informationAlert(Context context, String title, String message, DialogInterface.OnClickListener callBack) {
        showAlertDialog(INFORMATION_ALERT, context, title, message, callBack, "OK");
    }

    public static void confirmationAlert(Context context, String title, String message, DialogInterface.OnClickListener callBack) {
        showAlertDialog(CONFIRM_ALERT, context, title, message, callBack, "OK");
    }

    public static void decisionAlert(Context context, String title, String message, DialogInterface.OnClickListener posCallback,
                                     String... buttonNames) {
        showAlertDialog(DECISION_ALERT, context, title, message, posCallback, buttonNames);
    }

    public static void showAlertDialog(int alertType, Context context, String title, String message, DialogInterface
            .OnClickListener posCallback, String... buttonNames) {
        if (title == null) title = context.getResources().getString(R.string.app_name);
        if (message == null) message = "Message";

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert);

        switch (alertType) {
            case MESSAGE_ALERT:
                setNegativeButton(builder, "Ok");
                break;
            case CONFIRM_ALERT:
                builder.setPositiveButton("Yes", posCallback);
                setNegativeButton(builder, "No");
                break;
            case DECISION_ALERT:
                builder.setPositiveButton("Yes", posCallback);
                setNegativeButton(builder, "No");
                break;
            case INFORMATION_ALERT:
                builder.setPositiveButton("Ok", posCallback).create().show();
                break;
        }
    }

    private static void setNegativeButton(AlertDialog.Builder builder, String label) {
        builder.setNegativeButton(label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
}
