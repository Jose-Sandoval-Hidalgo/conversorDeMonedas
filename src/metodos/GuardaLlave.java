package metodos;

import java.io.FileWriter;
import java.io.IOException;

public class GuardaLlave {
    public void guardarKey(String key) throws IOException {
        FileWriter escritura = new FileWriter("key.txt");
        escritura.write(key);
        escritura.close();
    }
}
