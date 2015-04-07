package com.dynastymasra.tour.service;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.dynastymasra.tour.MainActivity;
import com.dynastymasra.tour.MainApplication;
import com.dynastymasra.tour.activity.HomeActivity;
import com.dynastymasra.tour.domain.Data;
import com.dynastymasra.tour.domain.url.Url;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class DataService extends AsyncTask<Void, Void, ResponseEntity<Data>>{
    private static final String TAG = "DataService";
    private Context context;

    public DataService(Context context) {
        this.context = context;
    }

    @Override
    protected ResponseEntity<Data> doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            restTemplate.getMessageConverters().add(jackson2HttpMessageConverter);

            ResponseEntity<Data> responseEntity = restTemplate.getForEntity(Url.URL, Data.class);
            Log.i(TAG, "responseBody=>" + responseEntity.getBody().toString());
            return responseEntity;
        } catch (Exception ex) {
            Log.e(TAG, "Error=>" + ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    protected void onPostExecute(ResponseEntity<Data> dataResponseEntity) {
        if (dataResponseEntity.getStatusCode() == HttpStatus.OK) {
            Log.i(TAG, "Response Data Sevice Success");
            ((MainApplication) context.getApplicationContext()).setDataApp(dataResponseEntity.getBody());
            Intent intent = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            ((MainActivity) context).finish();
        } else {
            Log.i(TAG, "Response Data Service Failure");
            ((MainApplication) context.getApplicationContext()).setDataApp(null);
            Intent intent = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            ((MainActivity) context).finish();
        }
    }
}
