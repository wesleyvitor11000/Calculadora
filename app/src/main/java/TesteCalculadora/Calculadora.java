package TesteCalculadora;
import java.util.ArrayList;

public class Calculadora{

    private class Operacao{

        private int operador; // 1 = +; 2 = -, 3 = * e 4 = /
        private float valor;

        public Operacao(int opr, float val) {
            operador = opr;
            valor = val;
        }

    }

    private ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

    public void addOperacao (int opd, float val) {

        Operacao op = new Operacao(opd, val);

        operacoes.add(op);

    }

    public float calcularResultado() {

        ArrayList<Float> tempRes = new ArrayList<Float>();
        float result = 0;

        for(Operacao op: operacoes) {

            switch(op.operador) {

                case 1:
                    tempRes.add(op.valor);
                    break;
                case 2:
                    tempRes.add(-op.valor);

                case 3:
                    float tempVal = tempRes.get(tempRes.size()-1);

                    tempRes.remove(tempRes.size()-1);
                    tempRes.add(tempVal*op.valor);
                    break;

                case 4:

                    float tempVal2 = tempRes.get(tempRes.size()-1);

                    tempRes.remove(tempRes.size()-1);
                    tempRes.add(tempVal2/op.valor);

            }

        }

        for(float f: tempRes) {
            result += f;
        }

        return result;
    }

    public float calcularResultadoOld () {

        float result = operacoes.get(0).valor;

        ArrayList<Operacao> mul_e_div = new ArrayList<Operacao>();
        ArrayList<Operacao> sum_e_sub = new ArrayList<Operacao>();

        //separa divisão e multiplicação dos demais
        for(Operacao op : operacoes) {
            if(op.operador == 3 || op.operador == 4) {
                mul_e_div.add(op);
            }
        }


        //separando array de somas e subtracoes
        for(Operacao op : operacoes) {
            if(op.operador == 1 || op.operador == 2) {
                sum_e_sub.add(op);
            }
        }


        //executando operacoes de div e mult:
        for(Operacao op: mul_e_div) {
            if(op.operador == 3) {
                result *= op.valor;
            }else {
                result /= op.valor; ////exceções valor = 0
            }
        }


        //executando operacoes de soma e sub:
        for(Operacao op: sum_e_sub) {
            if(op.operador == 1) {
                result += op.valor;
            }else {
                result -= op.valor; ////exceções valor = 0
            }
        }


        return result;
    }

    ///*************************************************///
    private float multiplicar(float a, float b) {
        return a*b;
    }

    private float dividir(float a, float b) {
        return a/b; ///inserir exceção b = 0********************************************
    }


}
