package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private OnItemClickListener itemClickListener; // متغير المستمع

    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }public interface OnItemClickListener {
        void onItemClick(int position);
    } public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }



    public void setData(List<String> dataList) {
        List<DataClass> data = new ArrayList<>();
        for (String item : dataList) {
            // إنشاء كائن DataClass باستخدام النصوص من dataList
            DataClass dataItem = new DataClass(item, "", "", "");
            data.add(dataItem);
        }
        this.dataList = data;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Existing code...

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    DataClass data = dataList.get(adapterPosition);
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("Image", data.getDataImage());
                    intent.putExtra("Description", data.getDataDesc());
                    intent.putExtra("Title", data.getDataTitle());
                    intent.putExtra("Key", data.getKey());
                    intent.putExtra("Language", data.getDataLang());
                    context.startActivity(intent);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(adapterPosition);
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recTitle, recDesc, recLang;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
//        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
    }
}
