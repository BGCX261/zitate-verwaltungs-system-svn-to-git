/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import java.sql.*;
import java.util.Vector;
import data.*;



public class personMapper {

    private static personMapper personMapper = null;

    /*
     * statische Methode, die sicherstellt, dass nur eine Istanz von FluglinieMapper erzeugt wird.
     *
     * @return Das fluglinieMapper -Objekt.
     */
    public static personMapper personMapper() {
        if (personMapper == null) {
            personMapper = new personMapper();
        }

        return personMapper;
    }
    /**
    * Hier wird ein neues Fluglinie (-Objekt) in die Datenbank eingefügt.
    * Dabei wird auch überprüft was die bisher höhste fluglinie_ID(der Primärschlüssel) ist.
    *
    * @param fl ist das das zu speichernde Fluglinie-Objekt
    * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter fluglinie_ID vom Typ Fluglinie
    */

    public Person insert(Person pe) {
        Connection con = DBConnection.connection();

        try {
            Statement stmt = con.createStatement();

            /*
             * Hier wird überprüft welche fluglinie_id bisher die höhste ist.
             */
            ResultSet rs = stmt.executeQuery(
                    "SELECT MAX(person_id) AS maxid " +
                    "FROM person");


            if (rs.next()) {
                try {
                    /* Hier wird die bisher höhste vorhandene fluglinie_id um +1 erhöht.
                     */
                    pe.set_person_id(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    /* Die tatsächliche Einfügeoperation mit den übergebenden Attributen.
                     */

                    stmt.executeUpdate("INSERT INTO person (person_id, vorname, name, anrede, titel) VALUES (" +
                            pe.get_person_id() + ",'" +
                            pe.get_vorname() + "','" +
                            pe.get_name()+ "','" +
                            pe.get_anrede()+ "','" +
                            pe.get_titel() + "')");


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        /* Rückgabe des angelegten Objekts fl von Typ Fluglinie.
         */
        return pe;
    }


    public Person update(Person pe) {
        /* Stellt durch Aufruf der connection() Methode der DBConnection-Klasse, die Verbindung zur Datenbank her. */
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            //Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("UPDATE person " +
                    "SET person_id=" + pe.get_person_id() + "," +
                    "vorname='" + pe.get_vorname() + "', " +
                    "name='" + pe.get_name() + "', " +
                    "anrede='" + pe.get_anrede() + "', " +
                    "titel='" + pe.get_titel() + "' " +
                    "WHERE person_id=" + pe.get_person_id());



        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // das als Parameter übergebende Objekt des Typs Fluglinie wird zurückgegeben
        return pe;
    }


    /**
     * Diese Methode löscht alle Daten eines Fluglinie- Objekts aus der Datenbank.
     *
     * @param fl ist das zu löschende Objekt
     */

    public void delete(Person pe) {
        Connection con = DBConnection.connection();

        try {
            // Leeres SQL-Statement stmt wird angelegt.
            Statement stmt = con.createStatement();

            // Statement wird ausgefüllt und als Query an die DB geschickt.
            stmt.executeUpdate("DELETE FROM person " +
                    "WHERE person_id=" + pe.get_person_id());

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

    public Vector findAll_person() {
        Connection con = DBConnection.connection();
        Vector result = new Vector();

        try {
            Statement stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery(

                    "SELECT person_id, vorname, name, anrede, titel " +
                    "FROM person " +
                    "ORDER BY person_id");

            // Für jeden Eintrag im Suchergebnis wird nun ein Fluglinie-Objekt fl erstellt.
            while (rs.next()) {
                try {
                    PersonImpl pe = new PersonImpl();
                    pe.set_person_id(rs.getInt("person_id"));
                    pe.set_vorname(rs.getString("vorname"));
                    pe.set_name(rs.getString("name"));
                    pe.set_anrede(rs.getString("anrede"));
                    pe.set_titel(rs.getString("titel"));
                    result.addElement(pe);
                    // Wenn kein Titel vorhanden ist bleibt der String Titel des Vectors leer
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

    public Person findbyid(int id) {
        Connection con = DBConnection.connection();
     
        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT person_id, vorname, name, anrede, titel FROM person " +
                    "WHERE person_id =" + id);

            /*
             * Da es sich bei id um einen Primärschlüssel handelt, kann max. nur ein Tupel zurückgegeben werden.
             * Es wird geprüft, ob ein Ergebnis vorliegt. Wenn ja werden die Attribute fluglinie_id,
             * und bezeichnung ausgelesen und als Typ Fluglinie zurückgegeben.
             */
            if (rs.next()) {
                try {

                    PersonImpl pe = new PersonImpl();
                    pe.set_person_id(rs.getInt("person_id"));
                    pe.set_vorname(rs.getString("vorname"));
                    pe.set_name(rs.getString("name"));
                    pe.set_anrede(rs.getString("anrede"));
                    pe.set_titel(rs.getString("titel"));
                 
                    return pe;

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

