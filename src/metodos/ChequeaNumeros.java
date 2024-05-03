package metodos;

public class ChequeaNumeros {
    public int ValidaInt(String cadena){
        try{
            return Integer.parseInt(cadena);
        }catch (Exception e){
            return -1;
        }
    }

    public float ValidaFloat(String cadena){
        try{
            return Float.parseFloat(cadena);
        }catch (Exception e){
            return -1;
        }
    }
}
