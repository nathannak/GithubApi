package com.alin.githubapi.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UiUtils {

    public static String baseUrl = "https://api.github.com/search/";
    private static ProgressDialog progressDialog;

    /**
     * Hides the software keyboard
     *
     * @param context Activity Context
     * @param view    UiView that is making the request
     */
    public static void hideSoftKeyboard(Context context, View view)
    {
        hideSoftKeyboard(context, view, false);
    }

    /**
     * Hides the software keyboard and clear the focus
     *
     * @param context    Activity Context
     * @param view       UiView that is making the request
     * @param clearFocus Whether to remove focus on given field
     */
    public static void hideSoftKeyboard(Context context, View view, boolean clearFocus)
    {
        if (context == null || view == null)
            return;

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

        if (clearFocus)
            view.clearFocus();
    }

    public static void showDialog(Context context, String message, String title) {
        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setTitle(title);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
