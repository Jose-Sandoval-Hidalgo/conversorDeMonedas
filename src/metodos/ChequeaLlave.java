package metodos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class ChequeaLlave {
    public String ChequearLlave() throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        String key;
        DatosListaDeDivisasExchangeApi getTest;
        DatosListaDeDivisas test;
        ConsultaExchange consulta = new ConsultaExchange();

        try{
            Scanner keyfile = new Scanner(new File("key.txt"));
            key = keyfile.nextLine();
        }catch (FileNotFoundException e){
            System.out.println("""
                    Este programa utiliza los servicios de ExchangeRate-API,
                    que requieren de una llave válida para funcionar.
                    Por favor, ingrese una llave:
                    """);
            key = lectura.nextLine();
            getTest= consulta.ConsigueDivisasValidas(key);
            test = new DatosListaDeDivisas(getTest);
            while(Objects.equals(test.getResultado(), "error")){
                System.out.println("Llave inválida o inactiva, por favor ingrese una llave activa: ");
                key = lectura.nextLine();
                getTest = consulta.ConsigueDivisasValidas(key);
                test = new DatosListaDeDivisas(getTest);
            }
            GuardaLlave aArchivo = new GuardaLlave();
            try {
                aArchivo.guardarKey(key);
            } catch (IOException ex) {
                System.out.println("""
                    No se pudo guardar la llave, deberá
                    reingresarla en su siguiente sesión.
                    """);
            }
        }
        return key;
    }

}
