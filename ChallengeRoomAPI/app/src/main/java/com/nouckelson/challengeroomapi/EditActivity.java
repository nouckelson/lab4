package com.nouckelson.challengeroomapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nouckelson.challengeroomapi.room.Book;
import com.nouckelson.challengeroomapi.room.BookDAO;
import com.nouckelson.challengeroomapi.room.Connections;

public class EditActivity extends AppCompatActivity  implements View.OnClickListener{
    Button btnApply,btnEdit,btnDelete;
    EditText isbn,title,author;
    BookDAO bookDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        isbn=findViewById(R.id.isbn);
        title=findViewById(R.id.title);
        author=findViewById(R.id.author);

        btnApply=findViewById(R.id.btnApply);
        btnEdit=findViewById(R.id.btnEdit);
        btnDelete=findViewById(R.id.btnDelete);

        btnApply.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        bookDAO= Connections.getInstance(getApplicationContext()).getDatabase().getBookDAO();
        isbn.setText(String.valueOf(getIntent().getLongExtra("isbn",1L)));
        title.setText(getIntent().getStringExtra("title"));
        author.setText(getIntent().getStringExtra("author"));
    }

    @Override
    public void onClick(View v) {
        Long ISBN=Long.parseLong(isbn.getText().toString().trim());
        switch (v.getId()) {
            case R.id.btnApply:

                try {
                    Book book=bookDAO.getBookByISBN(ISBN);
                    book.setTitle(title.getText().toString());
                    book.setAuthor(author.getText().toString());
                    bookDAO.update(book);
                    Toast.makeText(this,"Book has been successfully updating!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this,MainActivity.class));
                }catch (Exception e){
                    Toast.makeText(this,"Error:"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnEdit:
                title.setEnabled(true);
                author.setEnabled(true);
                btnApply.setVisibility(v.VISIBLE);
                break;
            case R.id.btnDelete:
                try {
                    Book book=bookDAO.getBookByISBN(ISBN);
                        bookDAO.delete(ISBN);
                        Toast.makeText(this,"Book has been  successfully deleting!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(this,MainActivity.class));
                }catch (Exception e){
                    Toast.makeText(this,"Error:"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}