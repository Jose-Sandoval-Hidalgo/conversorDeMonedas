package metodos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaExchange {

    public DatosConversionDeMonedasExchangeApi BuscaValorExchange(String key, String aConvertir, String convertida)
            throws IOException, InterruptedException {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+ key +"/pair/"+aConvertir+"/"+convertida);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = cliente
                .send(solicitud, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), DatosConversionDeMonedasExchangeApi.class);
    }

    public DatosListaDeDivisasExchangeApi ConsigueDivisasValidas(String key) throws IOException, InterruptedException {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+ key +"/codes/");
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = cliente
                .send(solicitud, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), DatosListaDeDivisasExchangeApi.class);
    }
}
