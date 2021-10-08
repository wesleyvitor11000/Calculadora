package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import TesteCalculadora.Calculadora;

public class MainActivity extends AppCompatActivity {

    //ESTRTURAIS
    Calculadora calculadora = new Calculadora();

    TextView TVOperacoes;
    TextView TVResultado;

    //TEMPORARIAS
    String tempOperacao = "";
    String valorTemporario = "";
    int ultimoOperador = 1;
    int proxOperador = 0;
    int termos = 1; ////////////////////0000000
    boolean deletado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        TVOperacoes = findViewById(R.id.tv_operations);
        TVResultado = findViewById(R.id.tv_result);

        //BOTÕES NUMÉRICOS:
        AppCompatButton zeroB = findViewById(R.id.zero_button);
        AppCompatButton oneB = findViewById(R.id.one_button);
        AppCompatButton twoB = findViewById(R.id.two_button);
        AppCompatButton threeB = findViewById(R.id.three_button);
        AppCompatButton fourB = findViewById(R.id.four_button);
        AppCompatButton fiveB = findViewById(R.id.five_button);
        AppCompatButton sixB = findViewById(R.id.six_button);
        AppCompatButton sevenB = findViewById(R.id.seven_button);
        AppCompatButton eightB = findViewById(R.id.eight_button);
        AppCompatButton nineB = findViewById(R.id.nine_button);

        AppCompatButton comma = findViewById(R.id.comma_button);

        //BOTÕES DE OPERACÇÕES:
        AppCompatButton sumB = findViewById(R.id.sum_button);
        AppCompatButton subB = findViewById(R.id.subtract_button);
        AppCompatButton multpB = findViewById(R.id.multp_button);
        AppCompatButton divB = findViewById(R.id.div_button);

        //BOTÕES DE FUNÇÕES
        AppCompatImageButton delB = findViewById(R.id.del_button);
        AppCompatButton eqlB = findViewById(R.id.equal_button);



        //FUNÇÕES DOS BOTÕES NUMÉRICOS:
        zeroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { inserirCaractereOperacao("0", true);}
        });

        oneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("1", true);}
        });

        twoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("2", true);}
        });

        threeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("3", true);}
        });

        fourB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserirCaractereOperacao("4", true);
            }
        });

        fiveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("5", true);}
        });

        sixB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("6", true);}
        });

        sevenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("7", true);}
        });

        eightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("8", true);}
        });

        nineB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {inserirCaractereOperacao("9", true);}
        });

        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!valorTemporario.isEmpty() && !valorTemporario.contains(".")) {
                    inserirCaractereOperacao(".", true);
                } //trocar por ,*************
            }
        });

        //FUNÇÕES DOS BOTÕES DE OPERAÇÕES
        sumB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxOperador = 1;
                inserirCaractereOperacao("+", false);
                limparResultado();
                termos++;
                ultimoOperador = 1;
                limparValorTemporario();
            }
        });

        subB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxOperador = 2;
                inserirCaractereOperacao("-", false);
                limparResultado();
                termos++;
                ultimoOperador = 2;
                limparValorTemporario();
            }
        });

        multpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxOperador = 3;
                inserirCaractereOperacao("x", false);
                limparResultado();
                termos++;
                ultimoOperador = 3;
                limparValorTemporario();
            }
        });

        divB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxOperador = 4;
                inserirCaractereOperacao("÷", false);
                limparResultado();
                termos++;
                ultimoOperador = 4;
                limparValorTemporario();
            }
        });

        //FUNÇÕES DE BOTÕES DE FUNÇÃO
        delB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCaractere();
            }
        });

        eqlB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempResult = limparTudo();
                inserirCaractereOperacao(tempResult, true);
            }
        });



    }

    private String limparTudo(){

        String tempResult = atualizarResultado() ;

        limparValorTemporario();
        limparResultado();

        tempOperacao = "";
        ultimoOperador = 1;
        proxOperador = 0;
        termos = 1;
        deletado = false;

        calculadora.limparTudo();
        TVOperacoes.setText("");

        return tempResult;

    }

    private void inserirCaractereOperacao(String caractere, boolean num){

        System.out.println("INSERINDO CARACTERE (CARACTERE = " + caractere + " numero?: " + num);

        if(valorTemporario.isEmpty()){
            if(valorTemporario.isEmpty() && !num){
                deleteCaractere();
            }
        }else{
            if(Float.parseFloat(valorTemporario) == 0 && !caractere.equals(".") && !valorTemporario.contains(".") && num){
                 deleteCaractere();
            }
        }

        boolean newTemp = false;

        if(valorTemporario.isEmpty()) { //deletado?
            newTemp = true;
        }

        System.out.println("TERMOS  ===== " + termos + "    VALOR É NULO? === " + valorTemporario.isEmpty() + " VALOR ===" + valorTemporario);

        if(!num && termos<=1 && valorTemporario.isEmpty()){
            System.out.println("ENTROU NA PRIMEIRA COMPARAÇÃO");
            if(!caractere.equals("-")) {
                System.out.println("N É sinal de menos");
                return;///ultima alteracao
            }
        }

        if(num){
            inserirNoValorTemporario(caractere, newTemp);
        }

        tempOperacao = tempOperacao.concat(caractere);
        trocarPorVirgula("", true);
    }

    private void inserirNoValorTemporario(String num, boolean newTemp){

        ///O ERRO DE N ATUALIZAR O RESULTADO QUANDO APAGA ESTÁ POR AQUI, EU ACHO

        System.out.println("INSERINDO VALOR TEMPORÁRIO (NUM = " + num + " newTemp = " + newTemp);

        valorTemporario = valorTemporario.concat(num);
        calculadora.valorTemporario(ultimoOperador, valorTemporario, newTemp);
        deletado = false;

        if(termos>1){
            atualizarResultado();
        }else{
            limparResultado();
        }
    }

    private void limparValorTemporario(){
        valorTemporario = "";
    }

    public void limparResultado(){
        TVResultado.setText("");
    }

    public String trocarPorVirgula(String resultado, boolean mudarApenasOperacao){

        System.out.println("ALTERANDO PONTOS PARA VÍRGULAS(");
        System.out.println("VALORES RECEBIDOS: \n RESULTADO: " + resultado + "\n tempOP: " + tempOperacao + " )");

        System.out.println("ENQUANTO VALOR TEMPORÁRIO: " + valorTemporario);


        tempOperacao = tempOperacao.replace(".", ",");
        TVOperacoes.setText(tempOperacao);

        if(mudarApenasOperacao){
            return "";
        }

        return resultado.replace(".", ",");


    }

    private String atualizarResultado() {
        String resultadoText = "";
        try {
            resultadoText = String.valueOf(calculadora.calcularResultado());
        }catch(Exception e){
            TVResultado.setText("Erro");
            return "";
        }

        float resultadoRacional = Float.parseFloat(resultadoText);
        int resultadoInteiro = (int)resultadoRacional;

        if((resultadoRacional - resultadoInteiro)!=0){
            String resultadoTextTemp = trocarPorVirgula(resultadoText, false);
            TVResultado.setText(resultadoTextTemp);
            return resultadoText;
        }else{
            resultadoText  = String.valueOf(resultadoInteiro);
            TVResultado.setText(resultadoText);
            return resultadoText;
        }
    }

    public void deleteCaractere(){

        System.out.println("DELETANDO CARACTERE.");

        if(valorTemporario.isEmpty()){

            System.out.println("DELETE TIPO 1");

            if(termos <= 1){
                ultimoOperador = 1;
                calculadora.deletarTemporario();
                System.out.println("ERRO TERMO <= 1");
                return;
            }

            termos--;

            boolean nuloValido = false;

            float ultimoValor = calculadora.getUltimoValor();
            int ultimoValorInt = (int)ultimoValor;

            int ultimoOperadorTemp = calculadora.getUltimoOperador();

            if(ultimoOperadorTemp == 3 || ultimoOperadorTemp == 4 || ultimoOperador == 3 || ultimoOperador == 4){
                nuloValido = true;
            }


            //o valor retornado para string, msm quando inteiro, continha o ".0" ao final, o q, juntando-se ao fato
            //de que a main trabalha com strings, faz com q os caracteres n sejam removidos corretamente
            if((ultimoValor - ultimoValorInt) == 0){
                valorTemporario = String.valueOf(ultimoValorInt);
            }else{
                valorTemporario = String.valueOf(ultimoValor);
            }

            if(!nuloValido && ultimoValor == 0){
                valorTemporario = "";
            }



            if(tempOperacao.isEmpty()){return;}

            tempOperacao = tempOperacao.substring(0, tempOperacao.length()-1);
            TVOperacoes.setText(tempOperacao);

        }else {
            System.out.println("DELETE TIPO 2");

            System.out.println("VALOR TEMPORARIO ANTES DO DELETE: " + valorTemporario);

            valorTemporario = valorTemporario.substring(0, valorTemporario.length() - 1);

            System.out.println("Delete tipo 2, valor temporario : " + valorTemporario + ", termos : " + termos);

            if(!tempOperacao.isEmpty()){
                tempOperacao = tempOperacao.substring(0, tempOperacao.length() - 1);
            }

            TVOperacoes.setText(tempOperacao);

            if(valorTemporario.isEmpty()) {
                calculadora.deletarTemporario();
                limparResultado();
                deletado = true;
            }
            else{
                inserirNoValorTemporario("", false);

                if(termos > 1) {
                    atualizarResultado();
                }else{
                    limparResultado();
                }
            }

        }

        //if(termos <= 1) limparResultado();
    }

}