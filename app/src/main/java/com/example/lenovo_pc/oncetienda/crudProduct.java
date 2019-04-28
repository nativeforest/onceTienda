package com.example.lenovo_pc.oncetienda;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by lenovo-pc on 23/07/2018.
 */

public class crudProduct {
    SQLiteDatabase cx;
    //ArrayList<Product> List;
    ArrayList<Product> List = new ArrayList<>();
    Product p;
    Context ct;                           ///
    String BDname="BDproduct";
    String table= "create table if not exists product " +
                  "(id integer primary key autoincrement," +
                    "name text," +
                    "feature text," +
                    "price integer)" ;
                   //created_date date)
    //String BDupdate="update Product set name="+name+",feature="+feature+",price="+price+"where id="+id;
    public crudProduct(Context c) {
        this.ct=c;
        cx=c.openOrCreateDatabase(BDname,Context.MODE_PRIVATE,null);
    cx.execSQL(table);
    }

    public boolean insert(Product p){

        ContentValues contvalu= new ContentValues();
        //contvalu.put("date_created", "datetime('now')");
        contvalu.put("name",p.getName());
        contvalu.put("feature",p.getFeature());
        contvalu.put("price",p.getPrice());
        return (cx.insert("Product",null,contvalu))>0;

   }

    public boolean delete(int id){




        return (cx.delete("Product","id="+id,null)>0  );
    }

    public boolean update(Product p){

        /////update/////
       // String BDupdate="update Product set name="+p.getName()+",feature="+p.getFeature()+",price="+p.getPrice()+"where id="+p.getId();
        /////-/update-////
////update2///
        ContentValues contvalu= new ContentValues();
        //contvalu.put("date_created", "datetime('now')");
        contvalu.put("name",p.getName());
        contvalu.put("feature",p.getFeature());
        contvalu.put("price",p.getPrice());
        return (cx.update("Product",contvalu,"id="+p.getId(),null))>0;
//-/-update2-////


    }
    public ArrayList<Product> seeAll(){

        List.clear();
        Cursor cursor=cx.rawQuery("select *from Product",null);
        if(cursor!=null && cursor.moveToFirst()){
      //  if(cursor!=null && cursor.getCount()>0){        //
          do {
             // List.add(new Product(cursor.getInt(0),
               //                    cursor.getInt(1),
                 //                  cursor.getString(2),
                   //                cursor.getString(3)));
              List.add(new Product(
                      cursor.getInt(0),
                      cursor.getInt(3),
                      cursor.getString(1),
                      cursor.getString(2)) );

          }while (cursor.moveToNext());

        }

        return List;
    }

    public Product seeOne(int id){

        Cursor cursor=cx.rawQuery("select *from Product",null);
        cursor.moveToPosition(id);
        p=new Product(cursor.getInt(0),
                cursor.getInt(3),
                cursor.getString(1),
                cursor.getString(2));



         return p;
    }






}
