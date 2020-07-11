package com.example.footballfield;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.footballfield.adapters.ListFieldAdapter;
import com.example.footballfield.includes.Helper;
import com.example.footballfield.adapters.SubjectData;
import com.example.footballfield.utils.MyHTTP;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list = findViewById(R.id.list);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        for (int i = 1; i <= 10; i++){
            arrayList.add(new SubjectData("Fields "+Integer.toString(i), "https://www.footboom.net/img/upload/2/5696c-Stadion-Old-Trafford.jpeg"));
        }
        ListFieldAdapter customAdapter = new ListFieldAdapter(this, arrayList);
        list.setAdapter(customAdapter);
    }


    private void auth(String Item){


        StringEntity entity = null;

        try {

            JSONObject jsonParams = new JSONObject();

            jsonParams.put("FIELD 1","");
            String requset = new JSONObject().put("Auth request", jsonParams).toString();
            Log.e("Auth ", "" + requset);
            entity = new StringEntity(requset);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        MyHTTP.post(mContext, entity, new JsonHttpResponseHandler() {
            ProgressDialog dialog;

            @Override
            public void onStart() {
                dialog = new ProgressDialog(mContext);
                dialog.setCancelable(false);
                dialog.setMessage("Loading");
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Log.e("Auth Check Response", "" + response);
                    response = response.getJSONObject("Response");

                    if (response.getString("isAuth").equals("success")) {
//Todo start intent
                    }
                        else{
                            Helper.showDialog(mContext, "fail to auth");

                        }
                    }catch (JSONException e) {
                    e.printStackTrace();
                    Helper.showDialogDataError(mContext);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Helper.showDialogNetworkError(mContext);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Helper.showDialogNetworkError(mContext);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Helper.showDialogNetworkError(mContext);
            }

            @Override
            public void onFinish() {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

        });


    }







}
