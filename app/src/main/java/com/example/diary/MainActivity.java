package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
static ArrayList<String> arr = new ArrayList<>();
 ListView listview;
 private Button button;

   static ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.diary",MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sp.getStringSet("diary",null);
       if(set==null){
            arr.add("Diary1");
       }
       else{
           arr=new ArrayList<>(set);
       }


       // arr.add("Diary1");
       ad = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,arr);
        listview.setAdapter(ad);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("noteid",position);
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Are you sure?").setMessage("Do You Want To Delete This Diary")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arr.remove(position);
                                ad.notifyDataSetChanged();
                                SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.diary",MODE_PRIVATE);
                                HashSet<String> set=new HashSet<>(MainActivity.arr);
                                sp.edit().putStringSet("diary",set).apply();
                            }
                        }
                        ).setNegativeButton("No",null).show();
                return true;
            }
        });

    }
    public void onclick(View v){
        Toast.makeText(this, "Adding New Diary", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}