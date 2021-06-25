package com.example.app;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class FragmentWeather extends Fragment {
    @Nullable
    int secim;
    SharedPreferences sharedPreferences,preferences;
    TextView textView,textViewName;
    String city_name = "";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentweather,container,false);
        textView = (TextView) view.findViewById(R.id.textWeather);
        textViewName = (TextView) view.findViewById(R.id.textCityName);
        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        DownloadData downloadData = new DownloadData();
        int storedData = preferences.getInt("sehir",0);
        try {
            String URL_istanbul = "http://api.openweathermap.org/data/2.5/weather?id=745044&appid=f47d7ab5677f05ad5dbd8824eb37daec";
            String URL_ankara = "http://api.openweathermap.org/data/2.5/weather?id=323786&appid=f47d7ab5677f05ad5dbd8824eb37daec";
            String URL_london = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=f47d7ab5677f05ad5dbd8824eb37daec";
            String url="";
                if(storedData == 1){
                    url = URL_ankara;
                    city_name = "Ankara";
                }
                else if(storedData == 2){
                    url = URL_london;
                    city_name = "London";
                }
                else if (storedData == 3){
                    url = URL_istanbul;
                    city_name = "İstanbul";
                }
            downloadData.execute(url);
        } catch (Exception e) {
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private class DownloadData extends AsyncTask<String, Void, String> {
        public String data;
        public String getData(){
            return data;
        }
        public void setData(String b){
            data = b;
        }
        @Override
        protected String doInBackground(String... strings) {
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
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          try {
                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("name");
                System.out.println("name:" + base);
                textViewName.setText(city_name);
                String main = jsonObject.getString("main");
                JSONObject jsonObject1 = new JSONObject(main);
                String sicaklik = jsonObject1.getString("temp");
                String Celcius = (String.valueOf(Math.ceil(Double.valueOf(sicaklik)-273)));
                textView.setText(Celcius+" C");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

