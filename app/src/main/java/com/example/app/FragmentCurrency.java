package com.example.app;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.microedition.khronos.egl.EGLDisplay;

import static java.lang.Double.parseDouble;
public class FragmentCurrency extends Fragment {
    @Nullable
    int secim;
    SharedPreferences sharedPreferences,preferences;
    TextView textView,tv,textView2;
    int storedData=0;
    EditText convertText;
    String turkishlira;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragmentcurrency, container, false);
        textView = (TextView) RootView.findViewById(R.id.textViewcurrency);
        textView2 = (TextView) RootView.findViewById(R.id.textConverter);
        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        final DownloadData downloadData = new DownloadData();
        convertText = (EditText) RootView.findViewById(R.id.editConverter);
        try {
            String url = "http://data.fixer.io/api/latest?access_key=c13b14b61cacd67617d047d3af5c05a2&format=1";
            downloadData.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        storedData = preferences.getInt("input",0);
        Button button = (Button) RootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gets = "";
                gets = convertText.getText().toString();
                textView2.setText(gets);
                double d = Double.valueOf(gets);
                double sonuc = d*Double.valueOf(downloadData.getData());
                textView2.setText(String.valueOf(sonuc));
            }
        });
        return RootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public void getRates2(View view) {
        DownloadData downloadData = new DownloadData();
        try {
            String url = "http://data.fixer.io/api/latest?access_key=c13b14b61cacd67617d047d3af5c05a2&format=1";
            downloadData.execute(url);
        } catch (Exception e) { }
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
            System.out.println("alınan data:" + s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                String rates = jsonObject.getString("rates");
                JSONObject jsonObject1 = new JSONObject(rates);
                String turkishlira = jsonObject1.getString("TRY");
                textView.setText("TRY: " + turkishlira);
                System.out.println("VERI"+ turkishlira);
                String usd = jsonObject1.getString("USD");
                double dolarkuru=Double.valueOf(usd)/Double.valueOf(turkishlira);
                dolarkuru = 1/dolarkuru;
                System.out.println("VERI"+ usd);
                if( storedData == 0 || storedData == 1 || storedData == 2)
                    if(storedData == 1) {
                        textView.setText("USD:" + String.valueOf(dolarkuru));
                        setData(String.valueOf(dolarkuru));
                    }
                    else if(storedData == 2) {
                        textView.setText("EUR: "+turkishlira);
                        setData(String.valueOf(turkishlira));
                    }
                    else
                        textView.setText("Başlangıç Ekranından Para Birimi Seçin");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }
