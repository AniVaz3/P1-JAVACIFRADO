import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que se encarga de mostar un menu interactivo de cifrado y decifrado de texto
 * con el cifrado Cesar
 * @author AniVaz
 * @version 1.0
 * @since 11/27/2024
 */
public class MenuCifradoCesar {

    /** Inicia el menu que realiza las operaciones de cifrado y decifrado
     * @throws InputMismatchException la entrada no es un numero entero valido.
     * */
    public void menuiniciar() throws IOException {

        Scanner scanner = new Scanner(System.in);
        Cifrado cifrado = new Cifrado("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzüéáíóúñÑ ¿¡.,;!?*-_/0123456789²º".toCharArray());
        FileManager fileManager = new FileManager();
        Validador validador = new Validador();

        boolean ejecutar = true;

        while (ejecutar) {

            System.out.println("Hola, Bienvenido al Menu Cifrado Cesar.");
            System.out.println("======== Seleccione una opción: =======");
            System.out.println("1. Cifrar texto en consola");
            System.out.println("2. Descifrar texto escrito en consola");
            System.out.println("3. Cifrar texto de un archivo");
            System.out.println("4. Descifrar texto de un archivo con clave");
            System.out.println("5. Salir");

            int opcion = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Opción inválida. Ingrese un número entero.");
                    scanner.nextLine();
                }
            }

            switch (opcion) {

                case 1:

                    System.out.println("Ingrese el texto que desea cifrar:");
                    String texto = scanner.nextLine();

                    System.out.println("Ingrese la clave de cifrado (número entero):");
                    int  desplazartext= -1;
                    validInput = false;

                    while (!validInput) {
                        try {
                            desplazartext = scanner.nextInt();
                            scanner.nextLine();
                            if (desplazartext < 0) {
                                System.out.println("La clave debe ser un número entero positivo.");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Clave inválida. Ingrese un número entero.");
                            scanner.nextLine();
                        }
                    }

                    String textoCifrado = cifrado.encrypt(texto, desplazartext);
                    System.out.println("Texto cifrado:");
                    System.out.println(textoCifrado);
                    break;

                case 2:

                    System.out.println("Ingrese el texto cifrado que desea descifrar:");
                    String textoParaDescifrar = scanner.nextLine();

                    System.out.println("Ingrese la clave de descifrado (número entero):");
                    int numDescifrado = -1;
                    validInput = false;

                    while (!validInput) {
                        try {
                            numDescifrado = scanner.nextInt();
                            scanner.nextLine();
                            if (numDescifrado < 0) {
                                System.out.println("La clave debe ser un número entero positivo.");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Clave inválida. Ingrese un número entero.");
                            scanner.nextLine();
                        }
                    }

                    String textoDescifrado = cifrado.decrypt(textoParaDescifrar, numDescifrado);
                    System.out.println("Texto descifrado:");
                    System.out.println(textoDescifrado);
                    break;

                case 3:

                    System.out.println("Ingrese la ruta del archivo a cifrar:");
                    String inputFilePath = scanner.nextLine();
                    if (!validador.isFileExists(inputFilePath)) {
                        System.out.println("El archivo no existe.");
                        break;
                    }

                    System.out.println("Ingrese la ruta para guardar el archivo cifrado y un nombre:");
                    String encryptedFilePath = scanner.nextLine();

                    System.out.println("Ingrese la clave de cifrado (número entero):");
                    int desplazamiento = -1;
                    validInput = false;

                    while (!validInput) {
                        try {
                            desplazamiento = scanner.nextInt();
                            scanner.nextLine();
                            if (!validador.isValidKey(desplazamiento, cifrado.getAlphabet())) {
                                System.out.println("Clave inválida.");
                                break;
                            }
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Clave inválida. Ingrese un número entero.");
                            scanner.nextLine();
                        }
                    }

                    String textToEncrypt = fileManager.leerArchivo(inputFilePath);
                    String encryptedText = cifrado.encrypt(textToEncrypt, desplazamiento);

                    fileManager.escribirArchivoChannel(encryptedText, encryptedFilePath);
                    System.out.println("Texto cifrado guardado en: " + encryptedFilePath);

                    fileManager.guardarConfiguracion("Config.propierties", desplazamiento, inputFilePath, encryptedFilePath);
                    break;

                case 4:

                    System.out.println("Ingrese la ruta del archivo cifrado:");
                    String encryptedFilePathForDecryption = scanner.nextLine();
                    if (!validador.isFileExists(encryptedFilePathForDecryption)) {
                        System.out.println("El archivo no existe.");
                        break;
                    }

                    System.out.println("Ingrese la ruta para guardar el archivo descifrado:");
                    String decryptedFilePath = scanner.nextLine();

                    System.out.println("Ingrese la clave de descifrado (número entero):");
                    int cambioaDescifrado = -1;
                    validInput = false;


                    while (!validInput) {
                        try {
                            cambioaDescifrado= scanner.nextInt();
                            if (!validador.isValidKey(cambioaDescifrado, cifrado.getAlphabet())) {
                                System.out.println("Clave inválida.");
                                break;
                            }
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Clave inválida. Ingrese un número entero.");
                            scanner.nextLine();
                        }
                    }

                    String textoaDescifrar = fileManager.leerArchivo(encryptedFilePathForDecryption);
                    String descifradoTx = cifrado.decrypt(textoaDescifrar, cambioaDescifrado);

                    System.out.println("Decifrado por fuerza bruta:\n" + descifradoTx);

                    fileManager.escribirArchivoChannel(descifradoTx, decryptedFilePath);
                    System.out.println("Texto descifrado guardado en: " + decryptedFilePath);
                    break;

                case 5:

                    ejecutar = false;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}
