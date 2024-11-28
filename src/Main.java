import java.io.IOException;

/**
 * Clase principal que inicia el programa de cifrado.
 *
 * @author AniVaz
 * @version 1.0
 * @since 11/27/2024
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MenuCifradoCesar menu = new MenuCifradoCesar();
        menu.menuiniciar();
    }
}