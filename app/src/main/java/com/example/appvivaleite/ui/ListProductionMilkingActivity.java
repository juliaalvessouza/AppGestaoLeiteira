package com.example.appvivaleite.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomProductionMilkDAO;
import com.example.appvivaleite.model.ProductionMilk;
import com.example.appvivaleite.ui.adapter.ListProductionMilkAdapter;
import com.example.appvivaleite.ui.adapter.ListProductionMilkItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.onItemClickListProductionMilk;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class ListProductionMilkingActivity extends AppCompatActivity {

    private RoomProductionMilkDAO dao;
    private ListProductionMilkAdapter adapter;
    private Button listDateProdMilk;
    private Button dateProd;
    private RecyclerView listProductionMilk;
    private String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_production_milk);
        dao = VivaLeiteDatabase.getInstance(this).getRoomProductionMilkDAO();
        getSupportActionBar().setTitle("Listagem de Produção de Leite");
        listProductionMilk = findViewById(R.id.recycler_list_production_milk);
        startInputs();
        configInputDate();
        btnListProdMilk();
    }

    private void btnListProdMilk() {
        listDateProdMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("VEMM", "A data vem " +  formattedDate);
                configRecyclerListProductionMilk(dao.allProductionMilk(formattedDate));
            }
        });
    }

    private void configInputDate() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Data Produção de Leite");
        MaterialDatePicker materialDatePicker = buider.build();

        dateProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                calendar.setTimeInMillis((Long) selection);
                calendar.add(Calendar.DATE, 1);
                formattedDate = formatter.format(calendar.getTime());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pdf, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        File pasta = new File(Environment.getExternalStorageDirectory() + "/Lista");
        int id = item.getItemId();

        if (id == R.id.menu_item_pdf) {
            Bitmap bitmapRecycler = fotoDatela(listProductionMilk);
            CriaPDF cria = new CriaPDF(pasta, getApplicationContext());
            String chamada = cria.salvaPDF(bitmapRecycler, "listaproducao");
                if(chamada.equals("sucesso")){
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso na pasta Lista", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), chamada, Toast.LENGTH_SHORT).show();
                }
        }
        if(id == R.id.menu_item_cancel){
            Intent ic = new Intent(ListProductionMilkingActivity.this, MainRegisterActivity.class);
            startActivity(ic);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap fotoDatela(RecyclerView view){
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bitmapPronto = null;

        if(adapter !=  null){
            Paint paint = new Paint();
            int tamLista = adapter.getItemCount();
            int altura = 0;
            int alturaVolatil = 0;
            final int tamMax = (int)(Runtime.getRuntime().maxMemory() / 1024);
            final int tamCash = tamMax / 8;
            LruCache<String, Bitmap> bitmapCash = new LruCache<>(tamCash);

            for(int i = 0; i< tamLista; i++){
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0,0,holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();

                Bitmap cashBitmap = holder.itemView.getDrawingCache();
                if(cashBitmap != null){
                    bitmapCash.put(String.valueOf(i), cashBitmap);
                }
                altura += holder.itemView.getMeasuredHeight();

            }
            bitmapPronto = Bitmap.createBitmap(view.getMeasuredWidth(), altura, Bitmap.Config.ARGB_8888);

            Canvas pagina = new Canvas(bitmapPronto);
            pagina.drawColor(Color.WHITE);

            for(int i = 0; i< tamLista; i++){
                Bitmap bitmap = bitmapCash.get(String.valueOf(i));
                pagina.drawBitmap(bitmap, 0, alturaVolatil, paint);
                alturaVolatil += bitmap.getHeight();
                bitmap.recycle();
            }
        }
        return bitmapPronto;
    }


    private void configRecyclerListProductionMilk(List<ProductionMilk> productionMilks) {
        configurationAdapter(productionMilks, listProductionMilk);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListProductionMilkItemTouchHelperCallback(adapter, dao, productionMilks, ListProductionMilkingActivity.this));
        itemTouchHelper.attachToRecyclerView(listProductionMilk);
    }

    private void configurationAdapter(List<ProductionMilk> productionMilks, RecyclerView listProductionMilk) {
        adapter = new ListProductionMilkAdapter(this, productionMilks);
        listProductionMilk.setAdapter(adapter);
        adapter.setOnItemClickListProductionMilk(new onItemClickListProductionMilk() {
            @Override
            public void onItemClickListProductionMilk(ProductionMilk productionMilk, int position) {
                Toast.makeText(getApplicationContext(), "Para deletar, arraste para o lado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startInputs() {
        dateProd = findViewById(R.id.btn_date_list_production_milk);
        listDateProdMilk = findViewById(R.id.btn_start_list_prod_milk);
    }

}