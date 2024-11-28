/** Clase que realiza la ejecucion del cifrado Cesar para cifrar y decifrar texto.
 *
 * @author AniVaz
 * @version 1.0
 * @since 11/27/2024
 * */
public class Cifrado {
    private char[] alphabet;


    /**
     * Constructor de la clase Cifrado.
     *
     * @param alphabet  el alfabeto que se utiliza para el cifrado.
     */
    public Cifrado(char[] alphabet) {
        this.alphabet = alphabet;
    }


    /**
     * Obtiene el alfabeto utilizando el cifrado.
     *
     * @return el alfabeto como un arreglo de caracteres.
     */
    public char[] getAlphabet() {
        return alphabet;
    }

    /**
     * Cifra el texto obtenido del usuario.
     *
     * @param text el texto a cifar.
     * @param desplazar el numero de posiciones para desplazar en el alfabeto.
     * @return el texto cifrado.
     */
    public String encrypt(String text, int desplazar) {
        return desplazarTexto(text, desplazar);
    }


    /**
     * Decifra un texto obtenido del usuario utilizando un desplazamiento.
     *
     * @param text el texto a decifrar.
     * @param desplazar el numero de posiciones para desplazar en el alfabeto.
     * @return el texto decifrado.
     */
    public String decrypt(String text, int desplazar) {
        return desplazarTexto(text, -desplazar);
    }


    /**
     * Ejecuta el desplazamiento de caracteres de texto.
     *
     * @param texto el texto a procesar.
     * @param desplazamiento el numero de posiciones para desplazar.
     * @return el texto generado con el desplazamiento.
     */
    private String desplazarTexto(String texto, int desplazamiento) {
        StringBuilder result = new StringBuilder();
        int alphabetLength = alphabet.length;

        for (char c : texto.toCharArray()) {
            int index = findIndex(c);
            if (index != -1) {
                int desplazaIndex = (index + desplazamiento + alphabetLength) % alphabetLength;
                result.append(alphabet[desplazaIndex]);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    /**
     *Encontar la posicion de un caracter en el alfabeto
     *
     * @param c es el caracter a buscar.
     * @return la posicion del caracter en el alfabeto o -1 si no se encuentra.
     */
    private int findIndex(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }
}