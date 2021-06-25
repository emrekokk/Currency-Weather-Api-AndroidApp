package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentTimer extends Fragment {
    @Nullable
    TextView textView;
    EditText dateText;
    Button buttonTime;
    long b;
    SharedPreferences preferences;
    int beforeAfter;
    boolean isPast;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //buttonTime = (Button) view.findViewById(R.id.button);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttimer, container, false);
        textView = (TextView) view.findViewById(R.id.textView4);
        dateText = (EditText) view.findViewById(R.id.dateText);
        buttonTime = (Button) view.findViewById(R.id.button);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Date userDate = new Date();
                Date currDate = new Date();
                System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", currDate);
                String str = String.format("Current Date/Time : %tc", currDate);
                String dateString;
                String fromDate = "00/00/0000";
                dateString = dateText.getText().toString();
                String fromDate2 = dateString;
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date dtt = null;
                try {
                    dtt = df.parse(fromDate2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date ds = new java.sql.Date(dtt.getTime());
                beforeAfter = currDate.compareTo(dtt);
                if(beforeAfter > 0){
                    b = currDate.getTime() - dtt.getTime();
                    isPast = true;
                }else if(beforeAfter < 0){
                    isPast = false;
                    b = dtt.getTime() - currDate.getTime();
                }
                int saniye,dakika,saat,gun;
                b= b/1000;
                saniye = (int) (b % 60);
                b = b / 60;
                dakika = (int) (b % 60);
                b = b / 60;
                saat = (int) (b % 24);
                b = b / 24;
                gun = (int) b;
                String durum;
                if(isPast == true){
                    durum = "Geçti";
                }
                else {
                    durum = "Kaldı";
                }
                if(gun == 0){
                    textView.setText(saat + " Saat " + dakika + " Dakika " + saniye + " Saniye " + durum +" ");
                }else
                    textView.setText(gun +" Gün " + saat + " Saat " + dakika + " Dakika " + saniye + " Saniye " + durum +" ");

            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}