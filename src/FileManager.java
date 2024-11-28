import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Clase que gestiona los archivos para leer, escribir y guardar configuraciones.
 *
 * @author AniVaz
 * @version 1.0
 * @since 11/27/2024
 */
public class FileManager {

    /**
     * Guarda la configuracion del cifrado en un archivo de propiedades.
     *
     * @param rutaArchivoConfig es la ruta del archivo de configuracion.
     * @param desplazamiento clave utilizada para el cifrado.
     * @param nombreArchivoOriginal el nombre del archivo original.
     * @param nombreArchivoEncriptado el nombre del archivo encriptado.
     *@throws IOException si ocurre un error al escribir en el archivo de configuracion.
     */
    public void guardarConfiguracion(String rutaArchivoConfig, int desplazamiento, String nombreArchivoOriginal, String nombreArchivoEncriptado) throws IOException {
        Properties propiedades = new Properties();

        propiedades.setProperty("desplazamiento", String.valueOf(desplazamiento));
        propiedades.setProperty("nombreArchivoOriginal", nombreArchivoOriginal);
        propiedades.setProperty("nombreArchivoEncriptado", nombreArchivoEncriptado);

        try (FileOutputStream out = new FileOutputStream(rutaArchivoConfig)) {
            propiedades.store(out, "Configuración del Cifrado César");
            System.out.println("Configuración guardada en: " + rutaArchivoConfig);
        } catch (IOException e) {
            System.err.println("Error al guardar la configuración: " + e.getMessage());
        }
    }


    /**
     * Lee el contenido de un archivo de texto.
     *
     * @param filePath la ruta del archivo a leer.
     * @return los datos del archivo en una cadena de texto.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public String leerArchivo(String filePath) throws IOException {
        Path path = Path.of(filePath);
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate((int) Files.size(path));
            fileChannel.read(buffer);
            buffer.flip();
            return new String(buffer.array());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Escribe datos de un archivo utilizando FileChannel.
     *
     * @param txtcontenido el texto a escribir.
     * @param filePath indica la ruta dónde se debe escribir el contenido del archivo.
     *@throws IOException si ocurre un error al escribir en el archivo.
     */
    public void escribirArchivoChannel(String txtcontenido, String filePath) throws IOException {
        Path path = Path.of(filePath);
        ByteBuffer buffer = ByteBuffer.wrap(txtcontenido.getBytes());
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            fileChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}