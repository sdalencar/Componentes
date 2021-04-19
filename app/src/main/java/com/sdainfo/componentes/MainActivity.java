package com.sdainfo.componentes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText tcInput;
    private Button button;
    private TextView textView, textView2;
    private RadioGroup radioGroup;
    private CheckBox cb1, cb2, cb3;
    private List<String> chb = new ArrayList<String>();

    private Switch aSwitch;
    private ToggleButton toggleButton;
    private CheckBox checkBoxChecar;
    private TextView textViewEstado;

    private ProgressBar bar1, bar2;
    private int valorBarra;

    private SeekBar seekBar2;
    private TextView tv2, tv3, tv4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.seekbar);
/*
        tcInput = findViewById(R.id.textInputEditText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textViewEstoque);
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        radioGroup = findViewById(R.id.rg);

        textViewEstado = findViewById(R.id.tvVerifica);
        checkBoxChecar = findViewById(R.id.checkBoxVerifica);
        toggleButton = findViewById(R.id.toggleTesteVerifica);
        aSwitch = findViewById(R.id.switchTesteVerifica);

        bar1 = findViewById(R.id.progressBarra);
        bar2 = findViewById(R.id.progressBar2);
        bar1.setVisibility(View.GONE);
        radionButton();

 */
        //outro layout
        seekBar2 = findViewById(R.id.seekBar1);
        tv2 = findViewById(R.id.tvRsultado1);
        tv3 = findViewById(R.id.tvStart);
        tv4 = findViewById(R.id.tvStop);


        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv2.setText("Progresso : " + seekBar.getProgress() + "/" + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv3.setText("Inicio : " + seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv4.setText("final : " + seekBar.getProgress());
            }
        });



    }


    public void enviar(View v) {
        /*String nome = tcInput.getText().toString();
        textView.setText(nome);
        */

        //checkBox();

        /*
        if (toggleButton.isChecked()){
            textViewEstado.setText("Ligado");
        }else {
            textViewEstado.setText("Desigado");
        }
        */
      /*
        if (aSwitch.isChecked()){
            textViewEstado.setText("Ligado");
        }else {
            textViewEstado.setText("Desigado");
        }
  */
        /*
        if (checkBoxChecar.isChecked()){
            textViewEstado.setText("Ligado");
        }else {
            textViewEstado.setText("Desigado");
        }

         */
        //toast();
        //alertas();

        barrasProgresso();
    }

    private void barrasProgresso(){
        this.valorBarra = this.valorBarra + 10;
        bar1.setProgress(valorBarra);
        bar2.setProgress(valorBarra);
        bar1.setVisibility(View.INVISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {//em segundo plano, não atualiza a tela
                for(int i = 0; i <= 100; i++){
                    System.out.println((i));
                    final int progresso = i;//pra passar o valor pra proxima classe mais interna
                    runOnUiThread(new Runnable() {// atualiza a tela
                        @Override
                        public void run() {
                            bar2.setProgress(progresso);
                            if (progresso == 100){
                                bar2.setVisibility(View.GONE);
                            }
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }


    public void toast() {
        // Toast.makeText(getApplicationContext(), "texto da mensagem",  Toast.LENGTH_LONG).show(); padrao

        //customizado pode ser usado layouts, imagens
        ImageView imageDeFundo = new ImageView(getApplicationContext());
        imageDeFundo.setImageResource(android.R.drawable.star_big_off);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(imageDeFundo);
        toast.show();


    }

    public void alertas(){
        AlertDialog.Builder alertaDialogo = new AlertDialog.Builder(this); //this pega o contexto da class o local e getContext da aplicação o global
        alertaDialogo.setTitle("titulo");
        alertaDialogo.setMessage("mensagem");
        alertaDialogo.setIcon(android.R.drawable.ic_dialog_info);


        alertaDialogo.setCancelable(false);//não deixa sair da mensagem

        alertaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Sim" , Toast.LENGTH_LONG).show();
            }
        });

        alertaDialogo.setNeutralButton("neutro", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "neutro" , Toast.LENGTH_LONG).show();
            }
        });

        alertaDialogo.setNegativeButton("não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "não" , Toast.LENGTH_LONG).show();
            }
        });

        alertaDialogo.create();
        alertaDialogo.show();



    }


    private void checkBox() {
        chb.clear();
        if (cb1.isChecked()) {
            chb.add(cb1.getText().toString());
        } else if (cb2.isChecked()) {
            chb.add(cb2.getText().toString());
        } else if (cb3.isChecked()) {
            chb.add(cb3.getText().toString());
        }
        textView.setText(chb.toString());
    }

    private void radionButton() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton1) {
                    textView2.setText("um");
                } else if (i == R.id.radioButton2) {
                    textView2.setText("dois");
                } else if (i == R.id.radioButton3) {
                    textView2.setText("três");
                }
            }
        });
    }


}