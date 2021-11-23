package com.example.retrovolley.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrovolley.API.APIRequestData;
import com.example.retrovolley.API.RetroServer;
import com.example.retrovolley.Model.ResponseModel;
import com.example.retrovolley.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    private EditText etJudul, etPenulis, etTahun_terbit;
    private Button btnSimpan;
    private String judul, penulis, tahun_terbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etJudul = findViewById(R.id.et_judul);
        etPenulis = findViewById(R.id.et_penulis);
        etTahun_terbit = findViewById(R.id.et_tahunterbit);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = etJudul.getText().toString();
                penulis = etPenulis.getText().toString();
                tahun_terbit = etTahun_terbit.getText().toString();

                if(judul.trim().equals("")){
                    etJudul.setError("Judul Buku Wajib Diisi");
                }else if(penulis.trim().equals("")){
                    etPenulis.setError("Nama Penulis Wajib Diisi");
                }else if(tahun_terbit.trim().equals("")){
                    etTahun_terbit.setError("Tahun Terbit Wajib Diisi");
                } else {
                    createData();
                }
            }
        });
    }
    private void createData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(judul, penulis, tahun_terbit);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}