package com.example.cryptojcomp.Banker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptojcomp.R;
import com.example.cryptojcomp.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<User> list;
    Context context;

    public UserAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user=list.get(position);
        holder.name.setText(user.getUsername());
        holder.eid.setText(user.getId());
        holder.uadd.setText(user.getAddress());
        holder.bal.setText(user.getBalance());
        holder.ccn.setText(user.getCcn());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,eid,uadd,ccn,bal;

        public ViewHolder(@NonNull View v) {
            super(v);
            name=v.findViewById(R.id.name);
            eid=v.findViewById(R.id.eid);
            uadd=v.findViewById(R.id.useradd);
            ccn=v.findViewById(R.id.ccn);
            bal=v.findViewById(R.id.bal);
        }
    }
}
