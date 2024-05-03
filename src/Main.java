import metodos.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaExchange consulta = new ConsultaExchange();
        ChequeaLlave llave = new ChequeaLlave();
        String key;
        int opcionMenuEntero = -1;
        String opcionMenuTexto;
        DatosConversionDeMonedasExchangeApi conversorMonedaDeApi;
        DatosConversionDeMonedas conversorMoneda;
        String dinero;
        double dineroInicial;
        double dineroFinal;
        String resultadoDeConversion;
        ChequeaNumeros testNumero = new ChequeaNumeros();
        Historial historial = new Historial();

        String menu = """
                
                °•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·
                Bienvenido, por favor elija una opción:
                
                1) Convertir monedas ingresando códigos de divisas (ISO-4217)
                2) Convertir dólar estadounidense (USD) a peso chileno (CLP)
                3) Convertir peso chileno (CLP) a dólar (USD)
                4) Convertir dólar estadounidense (USD a real brasileño (BRL)
                5) Convertir real brasileño (BRL) a dólar estadounidense (USD)
                6) Convertir dólar estadounidense (USD) a peso colombiano (COP)
                7) Convertir peso colombiano (COP) a dólar estadounidense (USD)
                8) Ver el historial de conversiones realizadas
                9) Salir
                °•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·°•·
                """;

        while (opcionMenuEntero != 9){
            try{
                key = llave.ChequearLlave();
                System.out.println(menu);
                opcionMenuTexto = lectura.nextLine();
                opcionMenuEntero = testNumero.ValidaInt(opcionMenuTexto);
                while (opcionMenuEntero == 0){
                    System.out.println("Por favor, ingresa un número entero del menú, sin más caracteres.");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(menu);
                    opcionMenuTexto = lectura.nextLine();
                    opcionMenuEntero = testNumero.ValidaInt(opcionMenuTexto);
                }

                switch (opcionMenuEntero){
                    case 1:
                        String monedaInicial;
                        String monedaFinal;
                        DatosListaDeDivisasExchangeApi listaDivisasDeApi = consulta.ConsigueDivisasValidas(key);
                        DatosListaDeDivisas listaDivisas = new DatosListaDeDivisas(listaDivisasDeApi);
                        ChequeaDivisa divisas = new ChequeaDivisa();
                        System.out.println("Ingresa el código ISO de la moneda que quieres convertir:");
                        monedaInicial = lectura.nextLine().toUpperCase();
                        while (divisas.ValidaDivisas(monedaInicial, listaDivisas)){
                            System.out.println("""
                                            Divisa inválida. Por favor, revise y reingrese el código de la moneda
                                            o escriba "salir" para volver al menú""");
                            monedaInicial = lectura.nextLine().toUpperCase();
                            if (monedaInicial.equals("SALIR")){break;}
                        } if (monedaInicial.equals("SALIR")){break;}
                        System.out.println("Ingresa el código ISO de la moneda a la que quieres llegar.");
                        monedaFinal = lectura.nextLine().toUpperCase();
                        while (divisas.ValidaDivisas(monedaFinal, listaDivisas)){
                            System.out.println("""
                                            Divisa inválida. Por favor, revise y reingrese el código de la moneda
                                            o escriba "salir" para volver al menú""");
                            monedaFinal = lectura.nextLine().toUpperCase();
                            if (monedaFinal.equals("SALIR")){break;}
                        } if (monedaFinal.equals("SALIR")){break;}
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, monedaInicial, monedaFinal);
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa cuanto dinero quieres convertir");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate() * dineroInicial;
                        resultadoDeConversion = monedaInicial + " " + String.format("%.2f", dineroInicial)
                                + " es equivalente a " + monedaFinal + " " + String.format("%.2f", dineroFinal);
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 2:
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, "USD", "CLP");
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa un valor en dólares estadounidenses para convertir a pesos chilenos:");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate()*dineroInicial;
                        resultadoDeConversion = "USD " + String.format("%.2f",dineroInicial)
                                + " es equivalente a CLP " + String.format("%.2f",dineroFinal);
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 3:
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, "CLP", "USD");
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa un valor en pesos chilenos para convertir a dólares estadounidenses:");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate()*dineroInicial;
                        resultadoDeConversion = "CLP " + String.format("%.2f", dineroInicial)
                                + " es equivalente a USD " + String.format("%.2f", dineroFinal);
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 4:
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, "USD", "BRL");
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa un valor en dólares estadounidenses para convertir a reales brasileños:");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate()*dineroInicial;
                        resultadoDeConversion = "USD " + String.format("%.2f", dineroInicial)
                                + " es equivalente a BRL " + String.format("%.2f", dineroFinal);
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 5:
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, "BRL", "USD");
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa un valor en reales brasileños para convertir a dólares estadounidenses:");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate()*dineroInicial;
                        resultadoDeConversion = "BRL " + String.format("%.2f", dineroInicial)
                                + " es equivalente a USD " + dineroFinal;
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 6:
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, "USD", "COP");
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa un valor en dólares estadounidenses para convertir a pesos colombianos:");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate()*dineroInicial;
                        resultadoDeConversion = "USD " + String.format("%.2f", dineroInicial)
                                + " es equivalente a COP " + String.format("%.2f",dineroFinal);
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 7:
                        conversorMonedaDeApi = consulta.BuscaValorExchange(key, "COP", "USD");
                        conversorMoneda = new DatosConversionDeMonedas(conversorMonedaDeApi);
                        System.out.println("Ingresa un valor en pesos colombianos para convertir a dólares estadounidenses:");
                        dinero = lectura.nextLine();
                        dineroInicial = testNumero.ValidaFloat(dinero);
                        while (dineroInicial <= 0){
                            System.out.println("Valor no válido, por favor ingresa un número positivo, entero o decimal.");
                            dinero = lectura.nextLine();
                            dineroInicial = testNumero.ValidaFloat(dinero);
                        }
                        dineroFinal = conversorMoneda.getConversion_rate()*dineroInicial;
                        resultadoDeConversion = "COP " + String.format("%.2f", dineroInicial)
                                + " es equivalente a USD " + String.format("%.2f", dineroFinal);
                        System.out.println(resultadoDeConversion);
                        historial.guardaConsulta(resultadoDeConversion);
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 8:
                        if (historial.getHistorial() != null) {
                            historial.historialPorLinea();
                            System.out.println("¿Desea guardar el historial en un archivo de texto?\n" +
                                    "(escriba \"si\" para guardar)");
                            String siONo = lectura.nextLine().toUpperCase();
                            if (siONo.equals("SI")){
                                historial.guardaAArchivo();
                                TimeUnit.SECONDS.sleep(2);
                            }else {
                                System.out.println("Continuando sin guardar.");
                                TimeUnit.SECONDS.sleep(1);
                            }
                        }else{
                            System.out.println("Historial vacío, ¡Aún no ha hecho consultas!");
                        }
                        break;
                    case 9:
                        System.out.println("Programa Finalizado. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no valida, por favor vuelva a seleccionar.");
                        break;
                }
            }catch (Exception e){
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String stacktrace = sw.toString();
                System.out.println("Error: \n" + stacktrace);
            }
        }

    }
}
