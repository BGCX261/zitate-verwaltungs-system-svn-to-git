/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import java.sql.*;
import java.util.Vector;
import data.*;



public class verlagMapper {

    private static verlagMapper verlagMapper = null;

    /*
     * statische Methode, die sicherstellt, dass nur eine Istanz von FluglinieMapper erzeugt wird.
     *
     * @return Das fluglinieMapper -Objekt.
     */
    public static verlagMapper verlagMapper() {
        if (verlagMapper == null) {
            verlagMapper = new verlagMapper();
        }

        return verlagMapper;
    }
    /**
    * Hier wird ein neues Fluglinie (-Objekt) in die Datenbank eingefügt.
    * Dabei wird auch überprüft was die bisher höhste fluglinie_ID(der Primärschlüssel) ist.
    *
    * @param fl ist das das zu speichernde Fluglinie-Objekt
    * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter fluglinie_ID vom Typ Fluglinie
    */

    public Verlag insert(Verlag ve) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            /*
             * Hier wird überprüft welche fluglinie_id bisher die höhste ist.
             */
            ResultSet rs = stmt.executeQuery(
                    "SELECT MAX(verlag_id) AS maxid " +
                    "FROM verlag");


            if (rs.next()) {
                try {
                    /* Hier wird die bisher höhste vorhandene fluglinie_id um +1 erhöht.
                     */
                    ve.set_verlag_id(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    /* Die tatsächliche Einfügeoperation mit den übergebenden Attributen.
                     */
                    stmt.executeUpdate("INSERT INTO verlag (verlag_id, name, ort)VALUES (" +

//                    stmt.executeUpdate("INSERT INTO verlag ('verlag_id', 'name','ort') " +

                            ve.get_verlag_id() + ",'" +
                            ve.get_name() + "','" +
                            ve.get_ort()+ "')");
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        /* Rückgabe des angelegten Objekts fl von Typ Fluglinie.
         */
        return ve;
    }


    public Verlag update(Verlag ve) {
        /* Stellt durch Aufruf der connection() Methode der DBConnection-Klasse, die Verbindung zur Datenbank her. */
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            //Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("UPDATE verlag " +
                    "SET verlag_id=" + ve.get_verlag_id() + ", " +
                    "name='" + ve.get_name() + "', " +
                    "ort='" + ve.get_ort() + "' " +
                    "WHERE verlag_id=" + ve.get_verlag_id());



        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // das als Parameter übergebende Objekt des Typs Fluglinie wird zurückgegeben
        return ve;
    }


    /**
     * Diese Methode löscht alle Daten eines Fluglinie- Objekts aus der Datenbank.
     *
     * @param fl ist das zu löschende Objekt
     */

    public void delete(Verlag ve) {
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            // Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("DELETE FROM verlag " +
                    "WHERE verlag_id=" + ve.get_verlag_id());

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

    public Vector findAll_verlag() {
        Connection con = DBConnection.connection();
        Vector result = new Vector();

        try {
            Statement stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery(
                    "SELECT verlag_id, name, ort " +
                    "FROM verlag " +
                    "ORDER BY verlag_id");

            // Für jeden Eintrag im Suchergebnis wird nun ein Fluglinie-Objekt fl erstellt.
            while (rs.next()) {
                try {
                    VerlagImpl ve = new VerlagImpl();
                    ve.set_verlag_id(rs.getInt("verlag_id"));
                    ve.set_name(rs.getString("name"));
                    ve.set_ort(rs.getString("ort"));
                    //Hier wird das Objekt dem Ergebnisvektor hinzugefügt
                    result.addElement(ve);

                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Bei einer evtl. Exception wird die while-Schleife abgebrochen.
                    break;
                }

            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        // Ergebnisvektor vom Typ Fluglinie wird zurückgeben.
        return result;

    }


    /**
     * Diese Methode sucht die Datenbank nach einem Fluglinie mit eingegebener ID.
     *
     * @param fluglinie_id ist das Primärschlüsselattribut Fluglinie in der Datenbank.
     * @return Das Objekt vom Typ Fluglinie, das der id entspricht wird zurückgegebn.
     * null wird zurückgegeben, wenn das Objekt nicht in der Datenbank vorhanden ist.
     * Da es sich um einen Primärschlüssel handelt und dieser somit eindeutig ist, wird genau ein Objekt zurückgegeben.
     */

    public Verlag findbyid(int id) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT verlag_id,name,ort FROM verlag " +
                    "WHERE verlag_id =" + id);

            /*
             * Da es sich bei id um einen Primärschlüssel handelt, kann max. nur ein Tupel zurückgegeben werden.
             * Es wird geprüft, ob ein Ergebnis vorliegt. Wenn ja werden die Attribute fluglinie_id,
             * und bezeichnung ausgelesen und als Typ Fluglinie zurückgegeben.
             */
            if (rs.next()) {
                try {

                    VerlagImpl ve = new VerlagImpl();
                    ve.set_verlag_id(rs.getInt("verlag_id"));
                    ve.set_name(rs.getString("name"));
                    ve.set_ort(rs.getString("ort"));
                    return ve;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            return null;
        }

        return null;
    }

//    public verlag findbybezeichnung(String bezeichnung) {
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


