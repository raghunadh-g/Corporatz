package com.maniraghu.flashchatnewfirebase.ui.dashboard.SmilePlease;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maniraghu.flashchatnewfirebase.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SmilePleaseRecyclerView extends RecyclerView.Adapter<SmilePleaseRecyclerView.SmilePleaseViewHolder> {
    private Context mContext;
    private List<Upload> mPost;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private boolean mProcessLike=false;
    private long likes=100;
    public SmilePleaseRecyclerView(Context mContext, List<Upload> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public SmilePleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.smile_please_post_items,parent,false);
        return new SmilePleaseRecyclerView.SmilePleaseViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull final SmilePleaseViewHolder holder, int position) {
        final Upload uploads=mPost.get(position);
        final String post_key=uploads.getmPostId();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Likes");
        mAuth=FirebaseAuth.getInstance();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.noOfLikes.setText(String.valueOf(dataSnapshot.child(post_key).getChildrenCount())+" likes");
               // likes=dataSnapshot.child(post_key).getChildrenCount();
                if(dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())){
                    holder.like.setImageResource(R.drawable.ic_thumb_up_blue_24dp);
                }
                else holder.like.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.username.setText(uploads.getmUsername());
        holder.time.setText(uploads.getmTime());
        holder.desc.setText(uploads.getmDesc());

        Picasso.with(mContext)
                .load(uploads.getmImageUrl())
                .placeholder(R.drawable.grad)
                .fit()
                .centerCrop()
                .into(holder.imgView);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProcessLike=true;
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (mProcessLike) {
                                if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
                                    mDatabase.child(post_key).child(mAuth.getCurrentUser().getUid()).removeValue();
                                    holder.like.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                                    mProcessLike=false;
                                } else {
                                    mDatabase.child(post_key).child(mAuth.getCurrentUser().getUid()).setValue("Random");
                                    holder.like.setImageResource(R.drawable.ic_thumb_up_blue_24dp);
                                    mProcessLike = false;
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public  class SmilePleaseViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView desc;
        public TextView username;
        public ImageView imgView;
        public TextView time;
        public TextView noOfLikes;
        public ImageButton like;
        public SmilePleaseViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            username=itemView.findViewById(R.id.smile_username);
            desc = itemView.findViewById(R.id.smile_description);
            imgView = itemView.findViewById(R.id.smile_image);
            time=itemView.findViewById(R.id.smile_post_time);
            like=itemView.findViewById(R.id.smile_like_button);
            noOfLikes=itemView.findViewById(R.id.smile_likes);
        }
    }
}
