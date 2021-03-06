package com.br.ksg.webService;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.exempleswipetab.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.InputStream;

/*
 * Created by Marcos on 24/11/2014.
 */
public class DownloadImagemReceita extends AsyncTask<String, Void, Drawable> {
    Context c;
    Activity activity;

    public DownloadImagemReceita(Context c,Activity activity) {
        this.c = c;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Drawable doInBackground(String... params) {
        String urlString = params[0];
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(urlString);

        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if(entity != null) {
                InputStream instream = entity.getContent();
                return Drawable.createFromStream(instream, "src");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Drawable result) {
        super.onPostExecute(result);

        ImageView imageView = (ImageView) activity.findViewById(R.id.img_receita);
        if (result != null)
            imageView.setImageDrawable(result);
    }
}
