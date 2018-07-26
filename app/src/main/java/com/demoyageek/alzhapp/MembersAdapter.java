package com.demoyageek.alzhapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demoyageek.alzhapp.Encapsulation.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {

    private ArrayList<User> myCircleList;
    Context context;

    MembersAdapter(ArrayList<User> myCircleList, Context context){
        this.myCircleList = myCircleList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return myCircleList.size();
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent,false);
        MembersViewHolder membersViewHolder = new MembersViewHolder(v, context, myCircleList);
        return membersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        User currentUserObj = myCircleList.get(position);
        holder.txtItemTitle.setText(currentUserObj.getName());
        //Picasso part


    }

    public static class MembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtItemTitle;
        CircleImageView circleImageView;
        Context context;
        ArrayList<User> myCircleList;
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;

        public MembersViewHolder(View itemView, Context context, ArrayList<User> myCircleList ){
            super(itemView);
            this.context = context;
            this.myCircleList = myCircleList;

            itemView.setOnClickListener(this);

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();

            txtItemTitle = itemView.findViewById(R.id.txtItemTitle);
            circleImageView = itemView.findViewById(R.id.cardCircleImageView);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Usted ha seleccionado este usuario", Toast.LENGTH_SHORT).show();
        }
    }


}

