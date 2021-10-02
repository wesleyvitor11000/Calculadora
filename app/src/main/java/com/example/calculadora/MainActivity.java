package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import TesteCalculadora.Calculadora;

public class MainActivity extends AppCompatActivity {

    private String tempValue = "";
    private Calculadora calculadora;
    String tempOp = "";
    TextView resultado;
    TextView operacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        calculadora = new Calculadora();
        resultado =  findViewById(R.id.tv_result);

        operacoes = findViewById(R.id.tv_operations);

        AppCompatButton somaBtn = findViewById(R.id.plus_button);

        AppCompatButton one = findViewById(R.id.one_button);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempValue = tempValue.concat("1");
                adicionarOperacoes("1");
            }
        });

        somaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float value = 0;
                if (tempValue != null && !tempValue.isEmpty()) {
                    value = Float.parseFloat(tempValue);
                } else {
                    return;
                }

                calculadora.addOperacao(1, value);
                atualizarResultado();
                tempValue = "";
                adicionarOperacoes(" + ");
            }
        });

        resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Você clicou!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        /*resultado.setOnClickListener((v) -> {
            Toast.makeText(MainActivity.this, "Clicou.", Toast.LENGTH_SHORT).show();
        });*/

//        resultado.setTextColor(ContextCompat.getColor(this, R.color.blue));
//        resultado.setTextColor(Color.parseColor("#22222"));
    }

    private void atualizarResultado() {
        String result = String.valueOf(calculadora.calcularResultado());
        resultado.setText(result);

    }

    private void adicionarOperacoes(String s){
        tempOp = tempOp.concat(s);
        operacoes.setText(tempOp);
    }

}