package com.example.apifeeddemo;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {
    private Context mCtx;

    protected ItemAdapter(Context ctx) {
        super(DIFF_CALLBACK);
        this.mCtx=ctx;
    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(Item oldItem, Item newItem) {
            return oldItem.answer_id==newItem.answer_id;
        }

        @Override
        public boolean areContentsTheSame(Item oldItem, Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = getItem(position);
        if (item != null){
            Glide.with(mCtx)
                    .load(item.owner.profile_image)
                    .into(holder.imgView);
            holder.txtView.setText(item.owner.display_name);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView txtView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageview);
            txtView = itemView.findViewById(R.id.textviewName);

        }
    }

}
