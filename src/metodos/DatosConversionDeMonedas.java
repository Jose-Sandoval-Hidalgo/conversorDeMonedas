package metodos;

import excepciones.CodigoNoValido;
import org.jetbrains.annotations.NotNull;

public class DatosConversionDeMonedas {
    private final String resultado;
    private final double razonDeConversion;

    public DatosConversionDeMonedas(@NotNull DatosConversionDeMonedasExchangeApi deAPI){
        resultado = deAPI.result();
        razonDeConversion = deAPI.conversion_rate();
    }

    public double getConversion_rate() {
        if (resultado.contains("error")){
            throw new CodigoNoValido("Códigos de divisa ISO 4217 inválidos");
        }
        return razonDeConversion;
    }
}
