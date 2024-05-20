package com.example.tacpham;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tacpham.Adapter.TacPhamAdapter;
import com.example.tacpham.DAO.TacPhamDAO;
import com.example.tacpham.model.TacPham;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMaTacPham, editTextTenTacPham, editTextTacGia, editTextNamXuatBan, editTextSoLuong,editTextdonGia;
    private Button buttonThem;
    private RecyclerView recyclerViewListTacPham;
    private TacPhamAdapter tacPhamAdapter;
    private List<TacPham> tacPhamList;
    private TacPhamDAO tacPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMaTacPham = findViewById(R.id.editTextMaTacPham);
        editTextTenTacPham = findViewById(R.id.editTextTenTacPham);
        editTextTacGia = findViewById(R.id.editTextTacGia);
        editTextNamXuatBan = findViewById(R.id.editTextNamXuatBan);
        editTextSoLuong = findViewById(R.id.editTextSoLuong);
        editTextdonGia = findViewById(R.id.editTextdonGia);

        buttonThem = findViewById(R.id.buttonThem);
        recyclerViewListTacPham = findViewById(R.id.recyclerViewListTacPham);
        tacPhamDAO = new TacPhamDAO(this);
        tacPhamList = new ArrayList<>();

        // Khởi tạo adapter và thiết lập RecyclerView
        tacPhamAdapter = new TacPhamAdapter(this, tacPhamList);
        recyclerViewListTacPham.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListTacPham.setAdapter(tacPhamAdapter);
        tacPhamDAO.open();

        // Load danh sách tác phẩm từ cơ sở dữ liệu và cập nhật RecyclerView
        loadTacPhamList();

        // Xử lý sự kiện khi nhấn nút "Thêm"
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String maTacPham = editTextMaTacPham.getText().toString();
                String tenTacPham = editTextTenTacPham.getText().toString();
                String nhaXB = editTextTacGia.getText().toString();
                int soXB = 0;
                int soLuong = 0;
                double donGia = 0;

                if (!editTextNamXuatBan.getText().toString().isEmpty()) {
                    soXB = Integer.parseInt(editTextNamXuatBan.getText().toString());
                }

                if (!editTextSoLuong.getText().toString().isEmpty()) {
                    soLuong = Integer.parseInt(editTextSoLuong.getText().toString());
                }

                if (!editTextdonGia.getText().toString().isEmpty()) {
                    donGia = Double.parseDouble(editTextdonGia.getText().toString());
                }

                // Tạo đối tượng TacPham mới
                TacPham tacPham = new TacPham(maTacPham, tenTacPham, nhaXB, soXB, soLuong, donGia);

                // Thêm tác phẩm vào cơ sở dữ liệu và cập nhật RecyclerView
                long result = tacPhamDAO.addTacPham(tacPham);
                if (result != -1) {
                    tacPhamList.add(tacPham);
                    tacPhamAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Đã thêm tác phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không thể thêm tác phẩm", Toast.LENGTH_SHORT).show();
                }

                // Xóa dữ liệu trong các EditText
                clearEditTexts();
            }
        });


        tacPhamAdapter.setOnItemClickListener(new TacPhamAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                TacPham tacPhamToEdit = tacPhamList.get(position);
                showEditTacPhamDialog(tacPhamToEdit, position);
            }

            @Override
            public void onDeleteClick(int position) {
                showDeleteConfirmationDialog(position);
            }
        });
    }

    private void loadTacPhamList() {
        tacPhamList.clear();
        tacPhamList.addAll(tacPhamDAO.getListTacPham());
        tacPhamAdapter.notifyDataSetChanged();
    }

    private void showEditTacPhamDialog(final TacPham tacPhamToEdit, final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_tacpham, null);
        dialogBuilder.setView(dialogView);

        final TextView textViewMaTacPham = dialogView.findViewById(R.id.textViewMaTacPham);
        final EditText editTextTenTacPham = dialogView.findViewById(R.id.editTextTenTacPham);
        final EditText editTextTacGia = dialogView.findViewById(R.id.editTextTacGia);
        final EditText editTextNamXuatBan = dialogView.findViewById(R.id.editTextNamXuatBan);
        final EditText editTextSoLuong = dialogView.findViewById(R.id.editTextSoLuong);
        final EditText editTextdonGia = dialogView.findViewById(R.id.editTextdonGia);


        textViewMaTacPham.setText(tacPhamToEdit.getMaTP());
        editTextTenTacPham.setText(tacPhamToEdit.getTenTP());
        editTextTacGia.setText(tacPhamToEdit.getNhaXB());
        editTextNamXuatBan.setText(String.valueOf(tacPhamToEdit.getSoXB()));
        editTextSoLuong.setText(String.valueOf(tacPhamToEdit.getSoLuong()));
        editTextdonGia.setText(String.valueOf(tacPhamToEdit.getDonGia()));

        dialogBuilder.setTitle("Sửa tác phẩm");

        dialogBuilder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String maTacPham = editTextMaTacPham.getText().toString();
                String tenTacPham = editTextTenTacPham.getText().toString();
                String nhaXB = editTextTacGia.getText().toString();
                int soXB = Integer.parseInt(editTextNamXuatBan.getText().toString());
                int soLuong = Integer.parseInt(editTextSoLuong.getText().toString());
                double donGia = Double.parseDouble(editTextdonGia.getText().toString());

                TacPham editedTacPham = new TacPham(maTacPham, tenTacPham, nhaXB, soXB, soLuong, donGia);

                boolean updated = tacPhamDAO.updateTacPham(editedTacPham);

                if (updated) {
                    tacPhamList.set(position, editedTacPham);
                    tacPhamAdapter.notifyItemChanged(position);
                    Toast.makeText(MainActivity.this, "Đã sửa tác phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không thể sửa tác phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setNegativeButton("Hủy", null);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Xác nhận xóa tác phẩm");
        builder.setMessage("Bạn có chắc chắn muốn xóa tác phẩm này?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean deleted = tacPhamDAO.deleteTacPham(tacPhamList.get(position).getMaTP());

                if (deleted) {
                    tacPhamList.remove(position);
                    tacPhamAdapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, "Đã xóa tác phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không thể xóa tác phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.create().show();
    }

    private void clearEditTexts() {
        editTextMaTacPham.setText("");
        editTextTenTacPham.setText("");
        editTextTacGia.setText("");
        editTextNamXuatBan.setText("");
        editTextSoLuong.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đảm bảo đóng cơ sở dữ liệu khi hoạt động bị hủy
        tacPhamDAO.close();
    }
}
