/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import java.sql.*;
import java.util.Vector;
import data.*;



public class es_beziehungMapper {

    private static es_beziehungMapper es_beziehungMapper = null;

    /*
     * statische Methode, die sicherstellt, dass nur eine Istanz von FluglinieMapper erzeugt wird.
     *
     * @return Das fluglinieMapper -Objekt.
     */
    public static es_beziehungMapper es_beziehungMapper() {
        if (es_beziehungMapper == null) {
            es_beziehungMapper = new es_beziehungMapper();
        }

        return es_beziehungMapper;
    }
    /**
    * Hier wird ein neues Fluglinie (-Objekt) in die Datenbank eingefügt.
    * Dabei wird auch überprüft was die bisher höhste fluglinie_ID(der Primärschlüssel) ist.
    *
    * @param fl ist das das zu speichernde Fluglinie-Objekt
    * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter fluglinie_ID vom Typ Fluglinie
    */

    public Es_beziehung insert(Es_beziehung es) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            /*
             * Hier wird überprüft welche fluglinie_id bisher die höhste ist.
             */
            ResultSet rs = stmt.executeQuery(
                    "SELECT MAX(es_beziehung_id) AS maxid " +
                    "FROM es_beziehung");


            if (rs.next()) {
                try {
                    /* Hier wird die bisher höhste vorhandene fluglinie_id um +1 erhöht.
                     */
                    es.set_sammelwerk_id(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    /* Die tatsächliche Einfügeoperation mit den übergebenden Attributen.
                     */

                    stmt.executeUpdate("INSERT INTO es_beziehung (es_beziehung_id, quellseite, quellabsatz, zielseite, zielabsatz, inhalt, quellwerk, zielwerk) " +

//                    stmt.executeUpdate("INSERT INTO es_beziehung ('es_beziehung_id', 'name','ort') " +
                            "VALUES (" +
                            es.get_sammelwerk_id() + ",'" +
                            es.get_einzelwerk_id() + ",'"
                          
                            );



                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        /* Rückgabe des angelegten Objekts fl von Typ Fluglinie.
         */
        return es;
    }


    public Es_beziehung update(Es_beziehung es) {
        /* Stellt durch Aufruf der connection() Methode der DBConnection-Klasse, die Verbindung zur Datenbank her. */
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            //Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("UPDATE es_beziehung " +
                    "SET es_beziehung_id=" + es.get_sammelwerk_id() + "," +
                    "quellseite='" + es.get_einzelwerk_id() + "' " +
         

                    "WHERE es_beziehung_id=" + es.get_sammelwerk_id());



        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // das als Parameter übergebende Objekt des Typs Fluglinie wird zurückgegeben
        return es;
    }


    /**
     * Diese Methode löscht alle Daten eines Fluglinie- Objekts aus der Datenbank.
     *
     * @param fl ist das zu löschende Objekt
     */

    public void delete(Es_beziehung es) {
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            // Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("DELETE FROM es_beziehung " +
                    "WHERE es_beziehung_id=" + es.get_sammelwerk_id());

        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /*
     * Bei dieser Methode werden alle Fluglinie ausgelesen.
     *
     * @return Es wird ein Vektor mit Fluglinie-Objekten, d.h. allen in der Datenbank angelegte Fluglinien, zurückgegeben.
     * Bei evtl. Exceptions wird ein partiell gefüllter oder ggf.
     * auch leerer Vektor vom Typ Fluglinie zurückgeliefert.
     */

//    public Vector findAll_es_beziehung() {
//        Connection con = DBConnection.connection();
//        Vector result = new Vector();
//
//        try {
//            Statement stmt = con.createStatement();
//
//
//            ResultSet rs = stmt.executeQuery(
//                    "SELECT es_beziehung_id, quellseite, quellabsatz, zielseite, zielabsatz, inhalt, quellwerk, zielwerk " +
//                    "FROM es_beziehung " +
//                    "ORDER BY es_beziehung_id");
//
//            // Für jeden Eintrag im Suchergebnis wird nun ein Fluglinie-Objekt fl erstellt.
//            while (rs.next()) {
//                try {
//                    es_beziehungImpl zi = new es_beziehungImpl();
//                    zi.set_es_beziehung_id(rs.getInt("es_beziehung_id"));
//                    zi.set_quellseite(rs.getInt("quellseite"));
//                    zi.set_quellabsatz(rs.getInt("quellabsatz"));
//                    zi.set_zielseite(rs.getInt("zielseite"));
//                    zi.set_zielabsatz(rs.getInt("zielabsatz"));
//                    zi.set_inhalt(rs.getString("inhalt"));
//                    zi.set_quellwerk(rs.getInt("quellwerk"));
//                    zi.set_zielwerk(rs.getInt("zielwerk"));
//                    //Hier wird das Objekt dem Ergebnisvektor hinzugefügt
//
//                    result.add(rs.getInt("es_beziehung_id") + "   " + rs.getInt("quellseite")+"  "+ rs.getInt("quellabsatz") + "   " + rs.getInt("zielseite")+"  "+ rs.getInt("zielabsatz") +"  "+ rs.getString("inhalt") + "   " + rs.getInt("quellwerk")+"  "+ rs.getInt("zielwerk"));
//
//
////                    result.add(rs.getInt("fluglinie_id") + "  " + rs.getString("bezeichnung"));
//                    System.out.println(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    //Bei einer evtl. Exception wird die while-Schleife abgebrochen.
//                    break;
//                }
//
//            }
//        } catch (SQLException e2) {
//            e2.printStackTrace();
//        }
//        // Ergebnisvektor vom Typ Fluglinie wird zurückgeben.
//        return result;
//
//    }
//
//
//    /**
//     * Diese Methode sucht die Datenbank nach einem Fluglinie mit eingegebener ID.
//     *
//     * @param fluglinie_id ist das Primärschlüsselattribut Fluglinie in der Datenbank.
//     * @return Das Objekt vom Typ Fluglinie, das der id entspricht wird zurückgegebn.
//     * null wird zurückgegeben, wenn das Objekt nicht in der Datenbank vorhanden ist.
//     * Da es sich um einen Primärschlüssel handelt und dieser somit eindeutig ist, wird genau ein Objekt zurückgegeben.
//     */
//
//    public es_beziehung findbyid(int id) {
//        Connection con = DBConnection.connection();
//
//        try {
//            Statement stmt = con.createStatement();
//
//            ResultSet rs = stmt.executeQuery(
//                    "SELECT es_beziehung_id,quellseite,quellabsatz, zielseite, zielabsatz, inhalt, quellwerk, zielwerk FROM es_beziehung " +
//                    "WHERE es_beziehung_id =" + id);
//
//            /*
//             * Da es sich bei id um einen Primärschlüssel handelt, kann max. nur ein Tupel zurückgegeben werden.
//             * Es wird geprüft, ob ein Ergebnis vorliegt. Wenn ja werden die Attribute fluglinie_id,
//             * und bezeichnung ausgelesen und als Typ Fluglinie zurückgegeben.
//             */
//            if (rs.next()) {
//                try {
//
//                    es_beziehungImpl zi = new es_beziehungImpl();
//                    zi.set_es_beziehung_id(rs.getInt("es_beziehung_id"));
//                    zi.set_quellseite(rs.getInt("quellseite"));
//                    zi.set_quellabsatz(rs.getInt("quellabsatz"));
//                    zi.set_quellseite(rs.getInt("zielseite"));
//                    zi.set_quellabsatz(rs.getInt("zielabsatz"));
//                    zi.set_inhalt(rs.getString("inhalt"));
//                    zi.set_quellwerk(rs.getInt("quellwerk"));
//                    zi.set_zielwerk(rs.getInt("zielwerk"));
//
//
//                    return zi;
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        } catch (SQLException e2) {
//            e2.printStackTrace();
//            return null;
//        }
//
//        return null;
//    }

//    public es_beziehung findbybezeichnung(String bezeichnung) {
//        Connection con = DBConnection.connection();
//
//        try {
//            Statement stmt = con.createStatement();
//
//            ResultSet rs = stmt.executeQuery(
//                    "SELECT fluglinie_ID, bezeichnung FROM Fluglinie " +
//                    "WHERE bezeichnung =" + bezeichnung);
//
//             /*
//             * Da es sich bei id um einen Primärschlüssel handelt, kann max. nur ein Tupel zurückgegeben werden.
//             * Es wird geprüft, ob ein Ergebnis vorliegt. Wenn ja werden die Attribute fluglinie_id,
//             * und bezeichnung ausgelesen und als Typ Fluglinie zurückgegeben.
//             */
//            if (rs.next()) {
//                try {
//
//                    FluglinieImpl fl = new FluglinieImpl();
//                    fl.set_fluglinie_id(rs.getInt("fluglinie_ID"));
//                    fl.set_bezeichnung(rs.getString("bezeichnung"));
//                    return fl;
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        } catch (SQLException e2) {
//            e2.printStackTrace();
//            return null;
//        }
//
//        return null;
//    }
////    public static void main(String[] args) {
////        FluglinieMapper gui = new FluglinieMapper();
////        gui.findAll_fluglinie();
////        }
}


