package com.example.footballfield.includes;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;

import com.example.footballfield.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
    static boolean reset;
    static HashMap<String, String> operatorMap;

    public static String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            Log.e("method code:", "" + sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String humanReadableByteCount(long bytes) {
        int unit = 1000;
        if (bytes < unit) return bytes + " Б";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = String.valueOf(("КMГТПЕ").charAt(exp - 1));
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);

    }

    public static String getCurDate() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.YEAR) + "-" + String.format("%02d", date.get(Calendar.MONTH) + 1) + "-" + String.format("%02d", date.get(Calendar.DAY_OF_MONTH));
    }

    public static String getCurDate(int diff) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, diff);
        return date.get(Calendar.YEAR) + "-" + String.format("%02d", date.get(Calendar.MONTH) + 1) + "-" + String.format("%02d", date.get(Calendar.DAY_OF_MONTH));
    }

    public static String dateTime(int seconds) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(seconds * 1000L);
        return date.get(Calendar.YEAR) + "-" +
                String.format("%02d", (date.get(Calendar.MONTH) + 1)) + "-" +
                String.format("%02d", date.get(Calendar.DAY_OF_MONTH)) + " " +
                String.format("%02d", date.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", date.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", date.get(Calendar.SECOND));
    }

    public static String dateFormatter(int seconds) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(seconds * 1000L);
        return String.format("%02d", date.get(Calendar.DAY_OF_MONTH)) + " " + getMonthName(date.get(Calendar.MONTH)) + ", " + String.format("%02d", date.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", date.get(Calendar.MINUTE));
    }

    public static String getTodayDate() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.DAY_OF_MONTH) + " " + getMonthName(date.get(Calendar.MONTH)) + ".";
    }

    public static String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case Calendar.JANUARY:
                monthName = "янв";
                break;
            case Calendar.FEBRUARY:
                monthName = "фев";
                break;
            case Calendar.MARCH:
                monthName = "мар";
                break;
            case Calendar.APRIL:
                monthName = "апр";
                break;
            case Calendar.MAY:
                monthName = "май";
                break;
            case Calendar.JUNE:
                monthName = "июн";
                break;
            case Calendar.JULY:
                monthName = "июл";
                break;
            case Calendar.AUGUST:
                monthName = "авг";
                break;
            case Calendar.SEPTEMBER:
                monthName = "сен";
                break;
            case Calendar.OCTOBER:
                monthName = "окт";
                break;
            case Calendar.NOVEMBER:
                monthName = "ноя";
                break;
            case Calendar.DECEMBER:
                monthName = "дек";
                break;
        }
        return monthName;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

//Todo add set string
    public static void showDialog(Context mContext, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        try {
            dialog.show();
        } catch (Exception e) {

        }
    }

    public static void showDialog(Context mContext, String title, String msg, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (!title.isEmpty()) {
            builder.setTitle(title);
        }
        builder.setMessage(msg);
        builder.setCancelable(cancelable);
        builder.setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        try {
            dialog.show();
        } catch (Exception e) {

        }
    }

    public static void showDialogNetworkError(Context mContext) {
        showDialog(mContext, "network Error");
    }

    public static void showDialogDataError(Context mContext) {

        showDialog(mContext, "DataError");
    }


    public static void showDialogDataError(Context mContext, String msg) {
        showDialog(mContext, msg);
    }


    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MMM, HH:mm");
        return dateFormat.format(new Date());
    }

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }



    private static String stringToAsterisk(String input) {
        if (input == null || input.length() == 0) return "";
        int length = input.length();
        char[] chars = new char[length];
        while (length > 0) chars[--length] = '*';
        chars[0] = input.charAt(0);
        chars[input.length() - 1] = input.charAt(input.length() - 1);
        return new String(chars);
    }


    public static boolean isJSONValid(String string) {
        try {
            new JSONObject(string);
        } catch (JSONException ex) {
            try {
                new JSONArray(string);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isJSONObjectValid(String string) {
        try {
            new JSONObject(string);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }



}
