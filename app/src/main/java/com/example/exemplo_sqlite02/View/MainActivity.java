package com.example.exemplo_sqlite02.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exemplo_sqlite02.Adapter.UsersAdapter;
import com.example.exemplo_sqlite02.DAO.DBManager;
import com.example.exemplo_sqlite02.Entity.User;
import com.example.exemplo_sqlite02.R;
import com.example.exemplo_sqlite02.DAO.UserDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private DBManager databaseManager;
    private Button btnInsert, btnList, btnCancel;
    private EditText edtName;
    private EditText edtAge;
    private ListView ListView;
    private UsersAdapter adapter;
    private List<User> users;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setup();
    }

    public void click(View view)
    {
        if(view.getId() == R.id.btnInsert)
        {
            insert();
        }else if(view.getId() == R.id.btnCancel)
        {
            clearFields();
        } else if (view.getId() == R.id.btnList)
        {
            list();
        } else if (view.getId() == R.id.btnSair)
        {
            finishAffinity();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        databaseManager.close();
    }

    public void insert()
    {
        String name = edtName.getText().toString();

        String age = edtAge.getText().toString();

        User user = new User(name, Integer.parseInt(age));

        long userId = userDAO.insertUser(user);

        Toast.makeText(MainActivity.this, "Usuário ID " +
                ":"+userId+" cadastrado!" , Toast.LENGTH_SHORT).show();

        clearFields();
    }

    public void setup()
    {
        databaseManager = new DBManager(this);

        databaseManager.open();

        userDAO = new UserDAO(databaseManager.getDatabase());

        btnInsert = findViewById(R.id.btnInsert);

        btnList = findViewById(R.id.btnList);

        btnCancel = findViewById(R.id.btnCancel);

        edtName = findViewById(R.id.edtNome);

        edtAge = findViewById(R.id.edtIdade);

        ListView = findViewById(R.id.listUsers);
    }

    public void clearFields()
    {
        edtName.setText(" ");
        edtAge.setText(" ");
    }

    public void usersActivity()
    {
        startActivity(new Intent(MainActivity.this, UsersActivity.class));
    }

    public void list()
    {
        users = userDAO.getAll();


        adapter = new UsersAdapter(this.getLayoutInflater(), (ArrayList<User>)users);

        ListView.setAdapter(adapter);
    }
}







