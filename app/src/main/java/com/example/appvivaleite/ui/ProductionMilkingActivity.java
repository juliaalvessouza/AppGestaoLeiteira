package com.example.appvivaleite.ui;

import static com.example.appvivaleite.ui.MilkingActivityConstantes.KEY_COW_MILKING;
import static com.example.appvivaleite.ui.MilkingActivityConstantes.POSITION_INVALID;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomProductionMilkDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.ProductionMilk;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class ProductionMilkingActivity extends AppCompatActivity {

    private TextView nameCow;
    private TextInputLayout qtadeLeiteCow;
    private Button btnFinishProdLeite;
    private RoomProductionMilkDAO dao;
    private int dataPosition = POSITION_INVALID;
    private RadioGroup radioGroupProdMilk;
    private RadioButton radioButtonProdMilk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_milking);
        getSupportActionBar().setTitle("Lançar produção leite");
        dao = VivaLeiteDatabase.getInstance(this).getRoomProductionMilkDAO();
        startInputs();

        Intent dataCowMilking = getIntent();
        if(dataCowMilking.hasExtra(KEY_COW_MILKING)){
            Cow cowMilking = (Cow) dataCowMilking.getSerializableExtra(KEY_COW_MILKING);
            nameCow.setText(cowMilking.getNomeCow());
            btnFinishProdLeite = findViewById(R.id.btn_check_prod_leite);
            btnFinishProdLeite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductionMilk productionMilking = createProductionMilking();
                    dao.save(productionMilking);
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cancel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_cancel:
                Intent ic = new Intent(ProductionMilkingActivity.this, MilkingActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private ProductionMilk createProductionMilking() {
        int radioId = radioGroupProdMilk.getCheckedRadioButtonId();
        radioButtonProdMilk = findViewById(radioId);
        String nameCowProdMik = nameCow.getText().toString();
        String qtadeProdMilk = qtadeLeiteCow.getEditText().getText().toString();
        String period = radioButtonProdMilk.getText().toString();
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.getDefault());
        String formatter = formmat1.format(ldt);
        ProductionMilk productionMilking = new ProductionMilk(nameCowProdMik, qtadeProdMilk, period, formatter);
        return productionMilking;
    }

    private void startInputs() {
        nameCow = findViewById(R.id.input_nome_cow_qtade_leite);
        qtadeLeiteCow = findViewById(R.id.input_qtade_litros_leite_cow);
        radioGroupProdMilk = findViewById(R.id.rGroupProdMilk);
    }
}
