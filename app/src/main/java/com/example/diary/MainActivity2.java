package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashSet;

public class MainActivity2 extends AppCompatActivity {
   EditText editText;
   Button button;
    int noteid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText=findViewById(R.id.editTextTextMultiLine2);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid",-1);
        if(noteid!=-1){
            editText.setText(MainActivity.arr.get(noteid));
        }
        else{
            MainActivity.arr.add("");
            noteid=MainActivity.arr.size()-1;
            MainActivity.ad.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                MainActivity.arr.set(noteid,String.valueOf(s));
                MainActivity.ad.notifyDataSetChanged();
                SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.diary",MODE_PRIVATE);
                HashSet<String>set=new HashSet<>(MainActivity.arr);
                sp.edit().putStringSet("diary",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s){

            }
        });
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Saving", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}