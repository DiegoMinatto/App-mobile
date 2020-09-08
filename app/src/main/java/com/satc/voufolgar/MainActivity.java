package com.satc.voufolgar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private EditText editDt;
    private Button btn;
    private Calendar c;
    private DatePickerDialog dpd;
    private String txtEditDt;
    private String txtViewDt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        txtView = (TextView)findViewById(R.id.txtViewData);
        btn = (Button)findViewById(R.id.btnSelData);
        editDt = (EditText)findViewById(R.id.editDt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int dpAno, int dpMes, int dpDia) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            txtEditDt = dpDia + "/"+ (dpMes + 1) + "/" + dpAno;
                            editDt.setText(txtEditDt);
                            Date d = sdf.parse(dpDia + "/"+ (dpMes + 1) + "/" + dpAno);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(d);
                            String msg = "";
                            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                                case 1 : msg = "Sim, é um domingo, você irá folgar";
                                    break;
                                case 2 : msg = "Não, é uma segunda, você não irá folgar";
                                    break;
                                case 3 : msg = "Não, é uma terça, você não irá folgar";
                                    break;
                                case 4 : msg = "Não, é uma quarta, você não irá folgar";
                                    break;
                                case 5 : msg = "Não, é uma squinta, você não irá folgar";
                                    break;
                                case 6 : msg = "Não, é uma sexta, você não irá folgar";
                                    break;
                                case 7 : msg = "Sim, é um sábado, você irá folgar";
                                    break;
                            }
                            txtViewDt = msg;
                            txtView.setText(txtViewDt);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dpd.show();

            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("txtViewDt", txtViewDt);
        outState.putString("editDt", txtEditDt);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtEditDt = savedInstanceState.getString("txtViewDt");
        txtViewDt = savedInstanceState.getString("editDt");
        editDt.setText(txtEditDt);
        txtView.setText(txtViewDt);
    }
}