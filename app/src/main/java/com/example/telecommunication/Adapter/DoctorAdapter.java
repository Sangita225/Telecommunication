package com.example.telecommunication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telecommunication.MessageActivity;
import com.example.telecommunication.Model.User;
import com.example.telecommunication.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{
    private Context mContext;
    private List<User> mUsers;

    public DoctorAdapter(Context mContext, List<User> mUsers){
        this.mUsers = mUsers;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.all_doctors_layout, parent, false);
        return new DoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user = mUsers.get(position);
        holder.fullname.setText("Name: "+user.getFullname());
        holder.address.setText("Address: "+user.getAddress());
        holder.gender.setText("Gender: "+user.getGender());
        holder.clinic.setText("Clinic/Hospital: "+user.getClinic());
        holder.speciality.setText("Speciality: "+user.getSpeciality());
        holder.experience.setText("Experience: "+user.getExperience());
        holder.usertype.setText(user.getUsertype());


        if (user.getImageURL().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullname;
        public ImageView profile_image;
        public TextView address;
        public TextView gender;
        public TextView clinic;
        public TextView speciality;
        public TextView experience;
        public TextView usertype;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            profile_image=itemView.findViewById( R.id.dimage );
            fullname= itemView.findViewById( R.id.dfullname );
            address= itemView.findViewById( R.id.daddress );
            gender= itemView.findViewById( R.id.dgender );
            clinic= itemView.findViewById( R.id.dhospital );
            speciality= itemView.findViewById( R.id.dspeciality );
            experience= itemView.findViewById( R.id.dexperience );
            usertype= itemView.findViewById( R.id.dusertype );





        }
    }
}
