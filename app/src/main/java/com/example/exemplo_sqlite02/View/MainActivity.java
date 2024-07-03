package com.example.exemplo_sqlite02.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exemplo_sqlite02.DAO.DBManager;
import com.example.exemplo_sqlite02.Entity.User;
import com.example.exemplo_sqlite02.R;
import com.example.exemplo_sqlite02.DAO.UserDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private DBManager databaseManager;
    private Button btnInsert, btnList, btnCancel;
    private TextView textViewResult;
    private EditText edtName;
    private EditText edtAge;

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

    public void clique(View view)
    {
        if(view.getId() == R.id.btnInsert)
        {
            insert();
        }else if(view.getId() == R.id.btnCancel)
        {
            clearFields();
        } else if (view.getId() == R.id.btnList)
        {
            usersActivity();
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

        textViewResult = findViewById(R.id.textViewResult);
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
}







