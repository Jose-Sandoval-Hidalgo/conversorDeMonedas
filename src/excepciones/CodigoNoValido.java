package excepciones;

public class CodigoNoValido extends RuntimeException{
    private final String mensaje;

    public CodigoNoValido(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
