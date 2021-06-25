package com.example.app;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class FragmentPreferences extends Fragment{
    @Nullable
    int secim=0,sehirSecim = 0;
    SharedPreferences sharedPreferences;
    TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpreferences,container,false);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView textView = (TextView) getView().findViewById(R.id.textView2);
        String [] kurlar =  {"SECINIZ","USD/TRY","EUR/TRY"};
        String [] sehirler =  {"SECINIZ","Ankara","London","Istanbul"};
        Spinner spinnerSehir = (Spinner) getView().findViewById(R.id.spinnerWeather);
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, kurlar);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        final ArrayAdapter<String> adapterW = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, sehirler);
        adapterW.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerSehir.setAdapter(adapterW);
        sharedPreferences = this.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getSelectedItem().equals("USD/TRY")){
                    secim = 1;
                    sharedPreferences.edit().putInt("input",secim).apply();
                }
                else if(adapterView.getSelectedItem().equals("EUR/TRY")){
                    secim = 2;
                    sharedPreferences.edit().putInt("input",secim).apply();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("SECILMEDI");
            }
        });
        spinnerSehir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getSelectedItem().equals("Ankara")){
                    sehirSecim = 1;
                    sharedPreferences.edit().putInt("sehir",sehirSecim).apply();
                }
                else if(adapterView.getSelectedItem().equals("London")){
                    sehirSecim = 2;
                    sharedPreferences.edit().putInt("sehir",sehirSecim).apply();
                }
                else if(adapterView.getSelectedItem().equals("Istanbul")){
                    sehirSecim = 3;
                    sharedPreferences.edit().putInt("sehir",sehirSecim).apply();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}
