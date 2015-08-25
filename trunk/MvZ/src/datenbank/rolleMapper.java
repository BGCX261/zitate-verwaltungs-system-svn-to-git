/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import java.sql.*;
import java.util.Vector;
import data.*;
import java.rmi.RemoteException;



public class rolleMapper {

    private static rolleMapper rolleMapper = null;

    /*
     * statische Methode, die sicherstellt, dass nur eine Istanz von FluglinieMapper erzeugt wird.
     *
     * @return Das fluglinieMapper -Objekt.
     */
    public static rolleMapper rolleMapper() {
        if (rolleMapper == null) {
            rolleMapper = new rolleMapper();
        }

        return rolleMapper;
    }
    /**
    * Hier wird ein neues Fluglinie (-Objekt) in die Datenbank eingefügt.
    * Dabei wird auch überprüft was die bisher höhste fluglinie_ID(der Primärschlüssel) ist.
    *
    * @param fl ist das das zu speichernde Fluglinie-Objekt
    * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter fluglinie_ID vom Typ Fluglinie
    */

    public Rolle insert(Rolle ro) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            /*
             * Hier wird überprüft welche fluglinie_id bisher die höhste ist.
             */
            ResultSet rs = stmt.executeQuery(
                    "SELECT MAX(rolle_id) AS maxid " +
                    "FROM autor_herausgeber");

                    stmt.executeUpdate("INSERT INTO autor_herausgeber (person_id, werk_id, typ) " +
                            "VALUES (" +
                            ro.get_person_id() + ",'" +
                            ro.get_werk_id() + "','" +
                            ro.get_typ()+ "')");



                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        return ro;
    }


    public Rolle update(Rolle ro) {
        /* Stellt durch Aufruf der connection() Methode der DBConnection-Klasse, die Verbindung zur Datenbank her. */
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            //Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("UPDATE autor_herausgeber " +
                    "SET person_id=" + ro.get_person_id() + "," +
                    "werk_id='" + ro.get_werk_id() + "', " +
                    "typ='" + ro.get_typ() + "' " +
                    "WHERE person_id=" + ro.get_person_id());



        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // das als Parameter übergebende Objekt des Typs Fluglinie wird zurückgegeben
        return ro;
    }


    /**
     * Diese Methode löscht alle Daten eines Fluglinie- Objekts aus der Datenbank.
     *
     * @param fl ist das zu löschende Objekt
     */

    public void delete(Rolle ro) {
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            // Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("DELETE FROM autor_herausgeber " +
                    "WHERE person_id=" + ro.get_person_id());

        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean existsZuordnung(Person pe) throws RemoteException {
        /** Stellt durch Aufruf der connection() Methode der DBConnection-Klasse, die Verbindung zur Datenbank her. */

        Connection con = DBConnection.connection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

                        stmt = con.prepareStatement(
                        "SELECT * FROM autor_herausgeber WHERE person_id =" + pe.get_person_id() );
                         rs= stmt.executeQuery();
                         if (rs.next()==true) {
                             return true;
                          }

                         else {
                             return false;
                          }

                } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                }

    }
    /*
     * Bei dieser Methode werden alle Fluglinie ausgelesen.
     *
     * @return Es wird ein Vektor mit Fluglinie-Objekten, d.h. allen in der Datenbank angelegte Fluglinien, zurückgegeben.
     * Bei evtl. Exceptions wird ein partiell gefüllter oder ggf.
     * auch leerer Vektor vom Typ Fluglinie zurückgeliefert.
     */

//    public Vector findAll_rolle() {
//        Connection con = DBConnection.connection();
//        Vector result = new Vector();
//
//        try {
//            Statement stmt = con.createStatement();
//
//
//            ResultSet rs = stmt.executeQuery(
//                    "SELECT person_id, werk_id, typ" +
//                    "FROM Rolle " +
//                    "ORDER BY person_id");
//
//            // Für jeden Eintrag im Suchergebnis wird nun ein Fluglinie-Objekt fl erstellt.
//            while (rs.next()) {
//                try {
//                    RolleImpl ro = new RolleImpl();
//                    ro.set_person_id(rs.getInt("person_id"));
//                    ro.set_werk_id(rs.getInt("werk_id"));
//                    ro.set_typ(rs.getChar("typ"));
//                    //Hier wird das Objekt dem Ergebnisvektor hinzugefügt
//
//                    result.add(rs.getInt("person_id") + "   " + rs.getInt("werk_id")+"  "+ rs.getChar("typ"));
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
//    public Rolle findbyid(int id) {
//        Connection con = DBConnection.connection();
//
//        try {
//            Statement stmt = con.createStatement();
//
//            ResultSet rs = stmt.executeQuery(
//                    "SELECT person_id,werk_id,typ FROM Rolle " +
//                    "WHERE person_id =" + id);
//
//            /*
//             * Da es sich bei id um einen Primärschlüssel handelt, kann max. nur ein Tupel zurückgegeben werden.
//             * Es wird geprüft, ob ein Ergebnis vorliegt. Wenn ja werden die Attribute fluglinie_id,
//             * und bezeichnung ausgelesen und als Typ Fluglinie zurückgegeben.
//             */
//            if (rs.next()) {
//                try {
//
//                    RolleImpl ro = new RolleImpl();
//                    ro.set_person_id(rs.getInt("person_id"));
//                    ro.set_werk_id(rs.getInt("werk_id"));
//                    ro.set_typ(rs.getChar("typ"));
//
//
//                    return ro;
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

//    public rolle findbybezeichnung(String bezeichnung) {
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


