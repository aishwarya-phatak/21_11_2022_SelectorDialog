package com.example.selectordialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtFavCities;
    Button btnSelectSkills, btnSelectCities;

    String [] cities = {
            "Pune","Mumbai","Banglore","Hyderabad","Indore"
    };

    String [] skills = {
            "C", "C++", "java", "Android", "iOS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();

    }

    private void initViews(){
        txtFavCities = findViewById(R.id.txtFavCities);
        btnSelectCities = findViewById(R.id.btnSelectCities);
        btnSelectSkills = findViewById(R.id.btnSelectSkills);
    }

    private void initListeners(){
        btnSelectSkills.setOnClickListener(new BtnSelectSkillsClickListener());
        btnSelectCities.setOnClickListener(new BtnSelectCitiesClickListener());
    }

    class BtnSelectSkillsClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            SelectorDialog selectorDialog = new SelectorDialog(
                    MainActivity.this,
                    skills
            );

            selectorDialog.setTitle("Skills");
            selectorDialog.setOnOptionsClickListener(new MyOnSkillsOptionsSetListener());
            selectorDialog.show();
        }
    }

    class MyOnSkillsOptionsSetListener implements SelectorDialog.OnOptionsClickListener{
        @Override
        public void OnOptionsSet(ArrayList<String> selectedSkills) {
            txtFavCities.setText("");
            for(String skill : selectedSkills){
                txtFavCities.append(skill + "\n");
            }
        }
    }

    class BtnSelectCitiesClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            SelectorDialog selectorDialog = new SelectorDialog(
                    MainActivity.this,
                    cities
            );
            selectorDialog.setTitle("Cities");
            selectorDialog.setOnOptionsClickListener(new MyOnCitiesOptionsSetListener());
            selectorDialog.show();
        }
    }

    class MyOnCitiesOptionsSetListener implements  SelectorDialog.OnOptionsClickListener{
        @Override
        public void OnOptionsSet(ArrayList<String> selectedCities) {
            txtFavCities.setText("");
            for(String city : selectedCities){
                txtFavCities.append(city + "\n");
            }
        }
    }
}