package com.example.exemplo_sqlite02.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exemplo_sqlite02.Entity.User;
import com.example.exemplo_sqlite02.R;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter
{
    private LayoutInflater inflater;
    private ArrayList<User> users;

    public UsersAdapter(LayoutInflater inflater, ArrayList<User> users)
    {
        this.inflater = inflater;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = users.get(position);

        convertView = inflater.inflate(R.layout.users_layout, null);

        //TextView id = convertView.findViewById(R.id.id);
        //id.setText(user.getId());

        TextView name = convertView.findViewById(R.id.name);
        name.setText(user.getName());

        TextView age = convertView.findViewById(R.id.age);
        age.setText(user.getAge());

        return convertView;
    }
}
