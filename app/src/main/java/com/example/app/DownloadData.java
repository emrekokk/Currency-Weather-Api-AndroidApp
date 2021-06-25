package com.example.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
/*
public class DownloadData extends AsyncTask<String, Void, String> {
    SharedPreferences sharedPreferences;

    public int secim = 0;
    public String data = "0000";
    public double kurlar = 0.0;

    public void setSecim(int gelenDeger){
        secim = gelenDeger;
    }
    public int getSecim(){
        return secim;
    }
    public void setData(String yeniData){
        data = yeniData;
    }
    public String getData(){
        return data;
    }

    DownloadData(int sec, String dat){
        secim = sec;
        data = dat;
    }
    public double getKurlar(){
        return kurlar;
    }
    public void setKurlar(double newdouble){
        kurlar = newdouble;
    }
    @Override
    public String doInBackground(String... strings) {
        String result = "";
        URL url;
        HttpURLConnection httpURLConnection;
        try {
            url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();
            while (data > 0) {

                char character = (char) data;
                result += character;
                data = inputStreamReader.read();
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);
        //System.out.println("alınan data:" + s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String base = jsonObject.getString("base");
            //System.out.println("base:" + base);
            String rates = jsonObject.getString("rates");
            JSONObject jsonObject1 = new JSONObject(rates);

            String turkishlira = jsonObject1.getString("TRY");
            String usd = jsonObject1.getString("USD");
            setKurlar(Double.parseDouble(turkishlira));
            if(secim == 1) {
                setData(turkishlira);
                kurlar = Double.valueOf(turkishlira);
            }
            else if(secim == 2)
                setData(usd);
            System.out.println("VERI ALINABILDI MI" + turkishlira);
            System.out.println("Secim alınabildi mi" + secim);
            System.out.println("double veri " + kurlar );
            data = turkishlira;
            setKurlar(Double.valueOf(turkishlira));

            //sharedPreferences.edit().putString("kur",turkishlira);
            setData(turkishlira);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}*/