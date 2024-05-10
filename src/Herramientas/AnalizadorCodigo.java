package Herramientas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorCodigo {
    private String [] texto;
    private static int constante;

    public AnalizadorCodigo(String texto){
        this.texto = texto.split("\n");
    }

    /**
     * Enumeración de los tipos de incremento
     */
    public static TipoWhile analizarBucle(String linea){ // Analiza el bucle
        Matcher matcher = Pattern.compile("-?\\d+").matcher(linea); // Busca un número en la línea
        constante = Integer.parseInt(matcher.group()); // Guarda el número
        if (linea.contains("=")){ // Si la línea contiene un igual
            return TipoWhile.IGUAL; // Es un bucle con igual
        } else if (linea.contains(">")){ // Si la línea contiene un mayor
            return TipoWhile.MAYOR; // Es un bucle con mayor
        } else if (linea.contains("<")){ // Si la línea contiene un menor
            return TipoWhile.MENOR; // Es un bucle con menor
        } else { // Si no es ninguno de los anteriores
            return TipoWhile.OTRO; // Es otro tipo de bucle
        }
    }

    /**
     * Analiza el código y devuelve si es un bucle infinito o no
     * @param i valor de la variable
     * @return true si es un bucle infinito, false si no
     */
    public boolean analizar(int i){
        TipoIncremento tipoIncremento = TipoIncremento.NULO;
        TipoWhile tipoWhile = TipoWhile.OTRO;
        for (String linea : texto) { // Recorre el texto
            if (linea.contains("while")) { // Si la línea contiene la palabra while
                tipoWhile = analizarBucle(linea); // Analiza el bucle
            }
            if (linea.matches("\\s*((\\w+ =\\w+ \\+\\d+)|(\\w+ \\+=\\d+)|(\\w+\\+\\+)|(\\+\\+\\w));")){ // Si la línea contiene un incremento positivo
                tipoIncremento = TipoIncremento.POSITIVO; // El incremento es positivo
            }
            if (linea.matches("\\s*((\\w+ *= *\\w+ *- *\\d+)|(\\w+ *-= *\\d+)|(\\w+--)|(--\\w*));*")){ // Si la línea contiene un incremento negativo
                tipoIncremento = TipoIncremento.NEGATIVO; // El incremento es negativo
            }
        }
        switch (tipoWhile) { // Según el tipo de bucle
            case IGUAL: // Si es un bucle con igual
                if (tipoIncremento == TipoIncremento.POSITIVO){ // Si el incremento es positivo
                    return i <= constante;
                }else if (tipoIncremento == TipoIncremento.NEGATIVO){ // Si el incremento es negativo
                    return i >= constante;
                }else { // Si no es ni positivo ni negativo
                    return i == constante; // i es igual a la constante
                }
            case MAYOR: // Si es un bucle con mayor
                if (tipoIncremento == TipoIncremento.POSITIVO){ // Si el incremento es positivo
                    return true;
                }else if (tipoIncremento == TipoIncremento.NEGATIVO){ // Si el incremento es negativo
                    return i > constante;
                }else { // Si no es ni positivo ni negativo
                    return false; // No es un bucle infinito
                }

            case MENOR: // Si es un bucle con menor
                if (tipoIncremento == TipoIncremento.POSITIVO){ // Si el incremento es positivo
                    return i > constante;
                }else if (tipoIncremento == TipoIncremento.NEGATIVO){ // Si el incremento es negativo
                    return i < constante;
                }else { // Si no es ni positivo ni negativo
                    return false; // No es un bucle infinito
                }
            default: // Si no es ninguno de los anteriores
                return false; // No es un bucle infinito
        }
    }

}
