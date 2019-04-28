package com.example.lenovo_pc.oncetienda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Region;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lenovo-pc on 23/07/2018.
 */

public class Adapter extends BaseAdapter {

//ArrayList<Product> List;
    ArrayList<Product> List = new ArrayList<>();
crudProduct cp;
Product p;
Activity a;
int id=0;
public Adapter(Activity a,ArrayList<Product> list,crudProduct cp){
    this.List=list;
    this.a=a;
    this.cp=cp;


}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Product getItem(int i) {
    p=List.get(i);
    return null;
    }

    @Override
    public long getItemId(int i) {
    p=List.get(i);
    return p.id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
    View v=view;
    if(v==null){
        LayoutInflater li=(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    v =li.inflate(R.layout.item,null);
    }
    p=List.get(position);
        TextView name=(TextView) v.findViewById(R.id.t_name);
        TextView feature=(TextView) v.findViewById(R.id.t_feature);
        TextView price=(TextView) v.findViewById(R.id.t_price);
        Button update=(Button) v.findViewById(R.id.update);
        Button delete=(Button) v.findViewById(R.id.delete);
        name.setText(p.getName());
        feature.setText(p.getFeature());
        price.setText(""+p.getPrice());
        update.setTag(position);
        delete.setTag(position);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////editar//////
                int pos=Integer.parseInt(view.getTag().toString());
                Log.v("test", "listener classonce");
                //dialogo xml
                final Dialog dialog= new Dialog(a);
                dialog.setTitle("Edit register");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText name=(EditText) dialog.findViewById(R.id.name);
                final EditText feature=(EditText) dialog.findViewById(R.id.feature);
                final EditText price=(EditText) dialog.findViewById(R.id.price);
                Button save= dialog.findViewById(R.id.d_add);
                Button cancel= dialog.findViewById(R.id.d_cancel);
                save.setText("Update");
                p=List.get(pos);
                setId(p.getId());
                name.setText(p.getName());
                price.setText(""+p.getPrice());
                feature.setText(p.getFeature());
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            p= new Product(getId(),Integer.parseInt(price.getText().toString()),
                                    name.getText().toString(),
                                    feature.getText().toString());
                            cp.update(p);
                            List= cp.seeAll();

                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"Error",Toast.LENGTH_SHORT).show();
                        }

                    }



                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                ////-/editar-////

// dialogo xml  para editar
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=Integer.parseInt(view.getTag().toString());
                p=List.get(pos);
                setId(p.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("are u sure  u want to eliminate");
                del.setCancelable(false);
                del.setPositiveButton(" yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      cp.delete(getId());
                      List=cp.seeAll();
                      notifyDataSetChanged();
                    }
                });
                //confirmar si o no
del.setNegativeButton(" NO ", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
});
                del.show();
            }
        });


    return v;
    }
}
