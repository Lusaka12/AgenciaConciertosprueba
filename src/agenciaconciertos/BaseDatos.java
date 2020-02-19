/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Clase que "almacena" la base de datos
 *
 * @author Daw-102
 */
public class BaseDatos {

    // como previsiblemente se puedan añadir nuevos reporteros usamos un ArrayList
    public static ArrayList<Reportero> baseReporteros = new ArrayList<Reportero>(
            Arrays.asList(
                    new Reportero("reportero1", "apellido1", "1234a", "123456789"),
                    new Reportero("reportero2", "apellido2", "1234b", "123456799"),
                    new Reportero("reportero3", "apellido3", "1234c", "123456899")
            ));
    
    public static ArrayList<Actuacion> baseActuaciones = new ArrayList<Actuacion>(
            Arrays.asList(
                    new Actuacion(0, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock"), new Artista("Artista2", "electronica")))),
                    new Actuacion(1, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(2, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(3, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(4, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(5, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(6, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(7, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(8, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock")))),
                    new Actuacion(9, 1, 20, new ArrayList<Artista>(Arrays.asList(new Artista("Artista1", "rock"))))
            )
    );
    // el constructor de tipo date esta marcada como @Deprecated es decir en desuso
    public static ArrayList<Gira> baseGiras = new ArrayList<Gira>(
            Arrays.asList(
                    new Gira(0, new Date(2020, 10, 1), new Date(2020, 10, 7), "giraNorte", new ArrayList<Concierto>(Arrays.asList(
                            new Concierto(new Date(2020, 10, 7), new ArrayList<Actuacion>(Arrays.asList(
                                    baseActuaciones.get(0),
                                    baseActuaciones.get(1),
                                    baseActuaciones.get(2),
                                    baseActuaciones.get(3),
                                    baseActuaciones.get(4))))))),
                    new Gira(1, new Date(2020, 10, 1), new Date(2020, 10, 7), "giraSur", new ArrayList<Concierto>()),
                    new Gira(2, new Date(2020, 10, 1), new Date(2020, 10, 7), "giraEste", new ArrayList<Concierto>()))
    );

    public static ArrayList<Informe> baseInformes = new ArrayList<Informe>(
            Arrays.asList(
                    new Informe(0, "bien", baseGiras.get(0)),
                    new Informe(1, "muy bien", baseGiras.get(0)),
                    new Informe(2, "mal", baseGiras.get(0)),
                    new Informe(3, "muy mal", baseGiras.get(0)),
                    new Informe(4, "bien", baseGiras.get(0))
            ));

    public static Reportero buscaReporteroByNIF(String NIF) {
        for (int i = 0; i < baseReporteros.size(); i++) {
            Reportero rep = baseReporteros.get(i);
            if (rep.getNif().equals(NIF)) {
                return rep;
            }
        }
        //throw new ReporteroNoEncontrado;
        return null;
    }

    public static Gira buscaGiraByNombre(String nombre) {
        for (int i = 0; i < baseGiras.size(); i++) {
            Gira gira = baseGiras.get(i);
            if (gira.getNombre().equals(nombre)) {
                return gira;
            }
        }
        //throw new GiraNoEncontrada;
        return null;
    }

    public static Informe buscaInformeByGira(Gira g) {
        for (int i = 0; i < baseInformes.size(); i++) {
            Informe informe = baseInformes.get(i);
            if (informe.getGira().equals(g)) {
                return informe;
            }
        }
        return null;
    }

    public static Informe buscaInformeByNomGira(String in) {
        for (int i = 0; i < baseInformes.size(); i++) {
            Informe informe = baseInformes.get(i);
            if (informe.getGira().getNombre().equals(in)) {
                return informe;
            }
        }
        return null;
    }

}
