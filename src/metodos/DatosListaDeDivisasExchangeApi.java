package metodos;

import java.util.HashMap;

public record DatosListaDeDivisasExchangeApi(String result, HashMap<String, String> supported_codes) {
}
