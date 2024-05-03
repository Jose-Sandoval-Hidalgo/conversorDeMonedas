package metodos;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChequeaDivisa {
    public boolean ValidaDivisas(@NotNull String divisa, DatosListaDeDivisas divisas) {
        HashMap<String, String> listaDivisas =  divisas.getCodigosSoportados();
        return !listaDivisas.containsKey(divisa);
    }
}
