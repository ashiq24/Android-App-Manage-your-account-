package com.example.ashiq.mangeaccountapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    String filename;
    String selectedUrl,selectedname;
    ArrayAdapter list;
    public  ArrayList<String> Names;
    public HashMap<String,String> Urls;
    Button addButton,deletButton,goButton;
    ListView account_list;
    EditText name,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Names=new ArrayList<>();
        Urls=new HashMap<>();
        addButton = (Button) findViewById(R.id.addButton);
        goButton = (Button) findViewById(R.id.goButton);
        deletButton = (Button) findViewById(R.id.deletButton);

        account_list = (ListView) findViewById(R.id.account_list);

        name = (EditText) findViewById(R.id.name);
        url = (EditText) findViewById(R.id.url);


        filename = "item.txt";
        String mytext;


        try {
            BufferedReader fread = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            while ((mytext = fread.readLine()) != null) {
                String[] mystring = mytext.split("#");
                Names.add(mystring[0]);
                Urls.put(mystring[0], mystring[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Names);
        account_list.setAdapter(list);
        account_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //view.setBackgroundColor(Color.BLUE);
                        selectedname = String.valueOf(parent.getItemAtPosition(position));
                        selectedUrl = Urls.get(selectedname);

                    }
                }

        );
    }
    void onadd( View view)
    {
        Button b=(Button) view;
        if(b.getText().equals("add"))
        {
            String n=name.getText().toString();
            String u=url.getText().toString();
            Names.add(n);
            Urls.put(n,u);

            list.notifyDataSetChanged();

            b.setText("add new");
            name.setText("");
            url.setText("");
            name.setHint("name");
            url.setHint("Url");
            name.setVisibility(View.INVISIBLE);
            url.setVisibility(View.INVISIBLE);

        }else
        {
            b.setText("add");
            name.setVisibility(View.VISIBLE);
            url.setVisibility(View.VISIBLE);
        }
    }

    void ongo( View view)
    {
        if(selectedUrl==null)
        {
            Toast.makeText(MainActivity.this, "NO URLS SELECTED", Toast.LENGTH_SHORT).show();
            selectedUrl=null;
        }else
        {
            Toast.makeText(MainActivity.this, selectedUrl, Toast.LENGTH_SHORT).show();
            Intent webveiwclass=new Intent(this,WebClass.class);
            webveiwclass.putExtra("Website",selectedUrl);
            selectedname=null;
            selectedUrl=null;
            startActivity(webveiwclass);

        }
    }

    void ondelet( View view)
    {
        if(selectedUrl==null)
        {
            Toast.makeText(MainActivity.this, "NO URLS SELECTED FOR DELET", Toast.LENGTH_LONG).show();
        }else
        {
            Names.remove(selectedname);
            Urls.remove(selectedname);
            list.notifyDataSetChanged();
            /*list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Names);
            account_list.setAdapter(list);*/
        }
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        doit();
        super.onPause();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        doitbefore();


    }

    @Override
    protected void onStop() {
        super.onStop();

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }


    void doit()
    {
        FileOutputStream fos;
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            PrintWriter pr=new PrintWriter(fos);
            for(String s:Names)
            {
                pr.println(s+"#"+Urls.get(s));
            }
            pr.close();
            fos.close();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "we could not save the last change", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {

        }
    }
    void doitbefore()
    {
        filename = "item.txt";
        String mytext;
        Names=new ArrayList<>();
        Urls=new HashMap<>();

        try {
            BufferedReader fread = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            while ((mytext = fread.readLine()) != null) {
                String[] mystring = mytext.split("#");
                Names.add(mystring[0]);
                Urls.put(mystring[0], mystring[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Names);
        account_list.setAdapter(list);

    }


}










