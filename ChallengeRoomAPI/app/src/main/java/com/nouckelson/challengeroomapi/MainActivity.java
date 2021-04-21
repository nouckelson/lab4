package com.nouckelson.challengeroomapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nouckelson.challengeroomapi.room.Book;
import com.nouckelson.challengeroomapi.room.BookDAO;
import com.nouckelson.challengeroomapi.room.Connections;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText isbn,title,author;
    Button btnCreate;
    BookDAO bookDAO;
    RecyclerView rcv;
    myAdapter adapter;
    List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isbn=findViewById(R.id.isbn);
        title=findViewById(R.id.title);
        author=findViewById(R.id.author);
        rcv=findViewById(R.id.rclview);
        btnCreate=findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(this);

        bookDAO= Connections.getInstance(getApplicationContext()).getDatabase().getBookDAO();
        ActionBar actionBar=getSupportActionBar();
        rcv=(RecyclerView) findViewById(R.id.rclview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        books=bookDAO.getAllBooks();
        adapter=new myAdapter(books,getApplicationContext());
        rcv.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreate:
                Book book=new Book();
                Long ISBN=Long.parseLong(isbn.getText().toString().trim());
                    book.setIsbn(ISBN);
                    book.setTitle(title.getText().toString().trim());
                    book.setAuthor(author.getText().toString().trim());
                    try {
                        bookDAO.insert(book);
                        Toast.makeText(this,"Book successfully created!",Toast.LENGTH_LONG).show();
                        startActivity(getIntent());
                    }catch (Exception e){
                        Toast.makeText(this,"Error:"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                break;
        }
    }





}