package com.nouckelson.challengeroomapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nouckelson.challengeroomapi.room.Book;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myviewholder> {
    List<Book> data;
    Context context;
    public  myAdapter(List<Book> data, Context context){
        this.data=data;
        this.context=context;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater=LayoutInflater.from(parent.getContext());
       View view=inflater.inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final Book temp=data.get(position);

           holder.t1.setText(data.get(position).getTitle());
           holder.t2.setText(data.get(position).getAuthor());
           holder.img.setImageResource(R.drawable.ic_baseline_book_24);
           holder.img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(context,EditActivity.class);
                   intent.putExtra("isbn",temp.getIsbn());
                   intent.putExtra("title",temp.getTitle());
                   intent.putExtra("author",temp.getAuthor());
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent);
               }
           });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
