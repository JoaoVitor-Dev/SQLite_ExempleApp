package com.example.exemplo_sqlite02.Adapter;

import android.view.LayoutInflater;
import com.example.exemplo_sqlite02.Entity.User;

import java.util.ArrayList;

public class UsersAdapter
{
    private LayoutInflater inflater;
    private ArrayList<User> users;

    public UsersAdapter(LayoutInflater inflater, ArrayList<User> users)
    {
        this.inflater = inflater;
        this.users = users;
    }
}
