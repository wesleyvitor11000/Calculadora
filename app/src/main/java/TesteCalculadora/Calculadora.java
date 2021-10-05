package TesteCalculadora;
import java.util.ArrayList;

public class Calculadora{

    private class Operacao{

        private int operador; // 1: +; 2: -, 3: * e 4: /
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

    public void valorTemporario(int op, String valorTemp, boolean newTemp){

        System.out.println("ATUALIZANDO TEMPORARIO ( OP = " + op + " VALOR = " + valorTemp + " NEW? = " + newTemp);

        float valor = 0;

        try{
            valor = Float.parseFloat(valorTemp);
        }catch(Exception e){}

        if(valorTemp.isEmpty()) valor=0;

        if(newTemp){
            Operacao opr = new Operacao(op, valor);
            operacoes.add(opr);
        }else{
            operacoes.get(operacoes.size()-1).operador = op;
            operacoes.get(operacoes.size()-1).valor = valor;
        }

    }

    public void deletarTemporario(){

        try {
            System.out.println("Entrou em deletar temporário:\nsize: " +
                    operacoes.size() +
                    "\nvalor removido: " + operacoes.get(operacoes.size() - 1).valor);

        }catch(Exception e){
        System.out.println("Okay, erro esperado");
        }

        if(operacoes.size()<=0) {
            return;
        }

        operacoes.remove(operacoes.size()-1);
    }

    public float getUltimoValor(){
        if(operacoes.isEmpty()) {
            return 0;
        }
        return operacoes.get(operacoes.size()-1).valor;
    }

    public int getUltimoOperador(){
        if(operacoes.isEmpty()){
            return 1;
        }
        return operacoes.get(operacoes.size()-1).operador;
    }

    public float calcularResultado() throws ArithmeticException{

        ArrayList<Float> tempRes = new ArrayList<Float>();
        float result = 0;

        tempRes.clear();

        for(Operacao op: operacoes) {

            System.out.println(op.operador + " ---->>> " + op.valor);

            switch(op.operador) {

                case 1:
                    tempRes.add(op.valor);
                    break;
                case 2:
                    tempRes.add(-op.valor);
                    break;

                case 3:
                    int length = tempRes.size();
                    tempRes.set(length-1, tempRes.get(length-1)*op.valor);

                    break;

                case 4:
                    if(op.valor == 0) throw new ArithmeticException();

                    int length2 = tempRes.size();
                    tempRes.set(length2-1, tempRes.get(length2-1)/op.valor);

                    break;
            }

        }

        for(float f: tempRes) {
            result += f;
        }

        return result;
    }

}
