# Conversor de Monedas

Pequeño programa didáctico escrito para interactuar con una API web utilizando Java.

**Conversor de Monedas** funciona en consola, con un menú de texto, y convierte cualquier divisa soportada por [ExchangeRate-API](https://www.exchangerate-api.com/) en otra similar, utilizando sus [códigos ISO-4217](https://es.wikipedia.org/wiki/ISO_4217).

### Características

**Conversor de Monedas** permite realizar las siguientes tareas:
- Convertir montos de dinero entre [divisas soportadas](https://www.exchangerate-api.com/docs/supported-currencies) por ExchageRate-API. Incluye también opciones de consulta rápida para transformar dolares estadounidenese a pesos chilenos, pesos colombianos y reales brasileños.

- Revisar las conversiones realizadas durante la sesión.

- Guardar en un archivo de texto las conversiones realizadas, con fecha y hora de consulta como referencia.

### Limitaciones
**Conversor de Monedas** está escrito en Java usando [SDK 17](https://www.oracle.com/cl/java/technologies/downloads/), y requiere de este o superior para funcionar.

**ExchageRate-API** tiene sus propias limitaciones con divisas volátiles o fuera del mercado internacional, como el Won Norcoreano. Se puede ver el detalle en su página de [monedas soportadas](https://www.exchangerate-api.com/docs/supported-currencies).
