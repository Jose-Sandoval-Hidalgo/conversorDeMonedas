package metodos;

import excepciones.CodigoNoValido;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class DatosListaDeDivisas {

    private final String resultado;
    private final HashMap<String, String> codigosSoportados;

    public String getResultado() {
        return resultado;
    }

    public DatosListaDeDivisas(@NotNull DatosListaDeDivisasExchangeApi deAPI){
        resultado = deAPI.result();
        codigosSoportados = deAPI.supported_codes();
    }

    public HashMap<String, String> getCodigosSoportados() {
        if (resultado.contains("error")){
            throw new CodigoNoValido("No se pudo conseguir la lista actualizada de divisas.");
        }
        return codigosSoportados;
    }

}
