/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;

/**
 *
 * @author DAW113
 */
public class InformeException extends Exception {

    public InformeException(String message) {
        super(message);
    }

    public static void validaDescripcion(String descripcion) throws InformeException {
        if (descripcion.isEmpty()) {
            throw new InformeException("La descripcion esta vacia.");

        }
        if (descripcion.length() > 20) {
            throw new InformeException("La descripcion ocupa mas de 20 caracteres");
        }
//        for(int i=0;i<descripcion.length();i++){
//           if("0123456789".indexOf(descripcion.charAt(i))!=-1){
//           throw new InformeException("La descripcion contiene algun caracter numerico");
//           }
//       }
    }

    public static void validaGira(Gira g) throws InformeException {
        if (g == null) {
            throw new InformeException("La gira va vacia.");
        }

    }
}
