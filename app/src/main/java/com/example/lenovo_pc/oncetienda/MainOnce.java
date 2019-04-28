package com.example.lenovo_pc.oncetienda;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainOnce extends AppCompatActivity {
crudProduct cp;
Adapter a;
//ArrayList<Product>List;
    ArrayList<Product> List = new ArrayList<>();
Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplication(),"HI ON-",Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_once);
        cp= new crudProduct( this);
        List=cp.seeAll();
        a=new Adapter(this,List,cp);
        final ListView list=(ListView) findViewById(R.id.List);
        Button add=(Button)findViewById(R.id.add);
        list.setAdapter(a);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
// vista xml
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test", "listener classonce");
                //dialogo xml
                final Dialog dialog= new Dialog(MainOnce.this);
                dialog.setTitle("new register");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText name=(EditText) dialog.findViewById(R.id.name);
                final EditText feature=(EditText) dialog.findViewById(R.id.feature);
                final EditText price=(EditText) dialog.findViewById(R.id.price);
                Button save= dialog.findViewById(R.id.d_add);
                Button cancel= dialog.findViewById(R.id.d_cancel);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            p= new Product(Integer.parseInt(price.getText().toString()),
                                    name.getText().toString(),
                                    feature.getText().toString());
                             cp.insert(p);
                             List= cp.seeAll();

                             a.notifyDataSetChanged();
                             dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                        }

                    }



                });
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dialog.dismiss();
    }
});
            }
        });




    }
}
