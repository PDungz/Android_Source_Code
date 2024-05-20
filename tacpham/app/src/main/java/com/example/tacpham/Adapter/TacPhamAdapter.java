package com.example.tacpham.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacpham.R;
import com.example.tacpham.model.TacPham;

import java.util.List;

public class TacPhamAdapter extends RecyclerView.Adapter<TacPhamAdapter.TacPhamViewHolder> {
    private Context context;
    private List<TacPham> tacPhamList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TacPhamAdapter(Context context, List<TacPham> tacPhamList) {
        this.context = context;
        this.tacPhamList = tacPhamList;
    }

    @NonNull
    @Override
    public TacPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_view, parent, false);
        return new TacPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacPhamViewHolder holder, int position) {
        TacPham tacPham = tacPhamList.get(position);
        holder.bind(tacPham);
    }

    @Override
    public int getItemCount() {
        return tacPhamList.size();
    }

    public class TacPhamViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMaTP, textViewTenTP, textViewNhaXB, textViewSoXB, textViewSoLuong, textViewDonGia;
        private Button buttonEdit, buttonDelete;

        public TacPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMaTP = itemView.findViewById(R.id.textViewMaTP);
            textViewTenTP = itemView.findViewById(R.id.textViewTenTP);
            textViewNhaXB = itemView.findViewById(R.id.textViewNhaXB);
            textViewSoXB = itemView.findViewById(R.id.textViewSoXB);
            textViewSoLuong = itemView.findViewById(R.id.textViewSoLuong);
            textViewDonGia = itemView.findViewById(R.id.textViewDonGia);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

        public void bind(TacPham tacPham) {
            textViewMaTP.setText(tacPham.getMaTP());
            textViewTenTP.setText(tacPham.getTenTP());
            textViewNhaXB.setText(tacPham.getNhaXB());
            textViewSoXB.setText(String.valueOf(tacPham.getSoXB()));
            textViewSoLuong.setText(String.valueOf(tacPham.getSoLuong()));
            textViewDonGia.setText(String.valueOf(tacPham.getDonGia()));
        }
    }
}
