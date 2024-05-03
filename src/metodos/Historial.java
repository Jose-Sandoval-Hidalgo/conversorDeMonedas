package metodos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Historial {
    private final ArrayList<String> historial = new ArrayList<>();

    public void guardaConsulta(String conversion){
        String aHistorial = conversion + "\t\t\t| Fecha y hora de consulta: " + LocalDateTime.now();
        historial.add(aHistorial);
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void historialPorLinea() {
        System.out.println("\n");
        for (String i : historial) {
            System.out.println(i);
        }
    }

    public void guardaAArchivo(){
        try {
            String nombreDeArchivo = LocalDateTime.now() + "-historial.txt";
            FileWriter guardaHistorial = new FileWriter(nombreDeArchivo);
            for (String consulta: historial){
                guardaHistorial.write(consulta + System.lineSeparator());
            }
            guardaHistorial.close();
            System.out.println("Guardado con éxito en archivo: " + nombreDeArchivo);
        }catch(IOException e){
            System.out.println("Falló: No se pudo guardar en nuevo archivo.");
        }

    }
}
