/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import java.sql.*;
import java.util.Vector;
import data.*;



public class werkMapper {

    private static werkMapper werkMapper = null;

    /*
     * statische Methode, die sicherstellt, dass nur eine Istanz von FluglinieMapper erzeugt wird.
     *
     * @return Das fluglinieMapper -Objekt.
     */
    public static werkMapper werkMapper() {
        if (werkMapper == null) {
            werkMapper = new werkMapper();
        }

        return werkMapper;
    }
    /**
    * Hier wird ein neues Werk (-Objekt) in die Datenbank eingefügt.
    * Dabei wird auch überprüft was die bisher höhste werk_ID(der Primärschlüssel) ist.
    *
    * @param we ist das das zu speichernde Werk-Objekt
    * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter werk_ID vom Typ Werk
    */

    public Werk insert(Werk we) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            /*
             * Hier wird überprüft welche werk_id bisher die höhste ist.
             */
            ResultSet rs = stmt.executeQuery(
                    "SELECT MAX(werk_id) AS maxid " +
                    "FROM Werk");


            if (rs.next()) {
                try {
                    /* Hier wird die bisher höhste vorhandene werk_id um +1 erhöht.
                     */
                    we.set_werk_id(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    /* Die tatsächliche Einfügeoperation mit den übergebenden Attributen.
                     */

                    stmt.executeUpdate("INSERT INTO Werk (werk_id, s_typ, titel, jahr, werk_typ, seiten, verlag_id) " +

                            "VALUES (" +
                            we.get_werk_id() + "," +
                            we.get_s_typ() + ",'" +
                            we.get_titel() + "'," +
                            we.get_jahr() + ",'" +
                            we.get_werk_typ() + "'," +
                            we.get_seiten() + "," +
                            we.get_verlag_id() + ")"

                            );



                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        /* Rückgabe des angelegten Objekts we von Typ Werk.
         */
        return we;
    }


    public Werk update(Werk we) {
        /* Stellt durch Aufruf der connection() Methode der DBConnection-Klasse, die Verbindung zur Datenbank her. */
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            //Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("UPDATE Werk " +
                    "SET werk_id=" + we.get_werk_id() + "," +
                    "titel='" + we.get_titel() + "' " +
                    "jahr='" + we.get_jahr() + "' " +
                    "seiten='" + we.get_seiten() + "' " +
                    "werk_typ='" + we.get_werk_typ() + "' " +
                    "WHERE werk_id=" + we.get_werk_id());



        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // das als Parameter übergebende Objekt des Typs Werk wird zurückgegeben
        return we;
    }


    /**
     * Diese Methode löscht alle Daten eines Fluglinie- Objekts aus der Datenbank.
     *
     * @param fl ist das zu löschende Objekt
     */

    public void delete(Werk we) {
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            // Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("DELETE FROM Werk " +
                    "WHERE werk_id=" + we.get_werk_id());

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

    public Vector findAll_werk() {
        Connection con = DBConnection.connection();
        Vector result = new Vector();

        try {
            Statement stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery(
                    "SELECT werk_id, titel, jahr, seiten, werk_typ " +
                    "FROM Werk " +
                    "ORDER BY werk_id");

            // Für jeden Eintrag im Suchergebnis wird nun ein Werke-Objekt we erstellt.
            while (rs.next()) {
                try {
                    WerkImpl we = new WerkImpl();
                    we.set_werk_id(rs.getInt("werk_id"));
                    we.set_titel(rs.getString("titel"));
                    we.set_jahr(rs.getInt("jahr"));
                    we.set_seiten(rs.getInt("seiten"));
//                    we.set_werk_typ(rs.getChar("werk_typ")));
                    
                    //Hier wird das Objekt dem Ergebnisvektor hinzugefügt
//                    result.add(rs.getInt("werk_id") + "   " + rs.getString("titel")+"  "+ rs.getInt("jahr")+ "   " + rs.getInt("seiten")+ "   " + rs.getChar("werk_typ"));
                    //Ausgabe in der Konsole für Test
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
        // Ergebnisvektor vom Typ Werk wird zurückgeben.
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

    public Werk findbyid(int id) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT werk_id, titel, jahr, seiten, werk_typ FROM Werk " +
                    "WHERE werk_id =" + id);

            /*
             * Da es sich bei id um einen Primärschlüssel handelt, kann max. nur ein Tupel zurückgegeben werden.
             * Es wird geprüft, ob ein Ergebnis vorliegt. Wenn ja werden die Attribute werk_id,
             * und titel ausgelesen und als Typ Werk zurückgegeben.
             */
            if (rs.next()) {
                try {

                    WerkImpl we = new WerkImpl();
                    we.set_werk_id(rs.getInt("werk_id"));
                    we.set_titel(rs.getString("titel"));
                    we.set_jahr(rs.getInt("jahr"));
                    we.set_seiten(rs.getInt("seiten"));
//                    we.set_werk_typ(rs.getChar("werk_typ"));

                    return we;

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

//    public Person findbybezeichnung(String bezeichnung) {
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

