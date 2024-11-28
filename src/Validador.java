import java.nio.file.*;

/**
 * Clase que valida las entradas del usurio y la existencia de archivos.
 *
 * @author AniVaz
 * @version 1.0
 * @since 11/27/2024
 *
 */
public class Validador {

    /**
     * Comprueba si una clave es valida dentro del alfabeto proporcionado.
     *
     * @param key es la clave a validar.
     * @param alphabet es el alfabeto permitido.
     * @return true si la clave es valida, false si es lo contrario.
     */
    public boolean isValidKey(int key, char[] alphabet) {
        return key >= 0 && key < alphabet.length;
    }


    /**
     * Comprueba si el archivo existe en la ruta proporcionada.
     *
     * @param filePath la ruta del archivo.
     * @return true si la clave es valida, false si es lo contrario.
     */
    public boolean isFileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }
}
