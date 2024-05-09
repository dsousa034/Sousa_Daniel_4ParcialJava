package Herramientas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorCodigo {
    private String [] texto;
    private static int constante;

    public AnalizadorCodigo(String texto){
        this.texto = texto.split("\n");
    }

    public static TipoWhile analizarBucle(String linea){
        Matcher matcher = Pattern.compile("-?\\d+").matcher(linea);
        constante = Integer.parseInt(matcher.group());
        if (linea.contains("=")){
            return TipoWhile.IGUAL;
        } else if (linea.contains(">")){
            return TipoWhile.MAYOR;
        } else if (linea.contains("<")){
            return TipoWhile.MENOR;
        } else {
            return TipoWhile.OTRO;
        }
    }

    public boolean analizar(int i){
        TipoIncremento tipoIncremento = TipoIncremento.NULO;
        TipoWhile tipoWhile = TipoWhile.OTRO;
        for (String linea : texto) {
            if (linea.contains("while")) {
                tipoWhile = analizarBucle(linea);
            }
            if (linea.matches("\\s*((\\w+ =\\w+ \\+\\d+)|(\\w+ \\+=\\d+)|(\\w+\\+\\+)|(\\+\\+\\w));")){
                tipoIncremento = TipoIncremento.POSITIVO;
            }
            if (linea.matches("\\s*((\\w+ *= *\\w+ *- *\\d+)|(\\w+ *-= *\\d+)|(\\w+--)|(--\\w*));*")){
                tipoIncremento = TipoIncremento.NEGATIVO;
            }
        }
        switch (tipoWhile) {
            case IGUAL:
                if (tipoIncremento == TipoIncremento.POSITIVO){
                    return i <= constante;
                }else if (tipoIncremento == TipoIncremento.NEGATIVO){
                    return i >= constante;
                }else {
                    return i == constante;
                }
            case MAYOR:
                if (tipoIncremento == TipoIncremento.POSITIVO){
                    return i < constante;
                }else if (tipoIncremento == TipoIncremento.NEGATIVO){
                    return i > constante;
                }else {
                    return false;
                }

            case MENOR:
                if (tipoIncremento == TipoIncremento.POSITIVO){
                    return i > constante;
                }else if (tipoIncremento == TipoIncremento.NEGATIVO){
                    return i < constante;
                }else {
                    return false;
                }
            default:
                return false;
        }
    }

}
