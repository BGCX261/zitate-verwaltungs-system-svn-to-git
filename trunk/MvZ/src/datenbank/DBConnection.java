/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datenbank;


import java.sql.*;

public class DBConnection {
    private static Connection con = null;

    /**
    * url ist das Attribut, mit deren Hilfe die Datenbank angesprochen wird.
    * verändert sich etwas an der url muss dies hier geschehen und die gesamte Software neu kompiliert werden
    */
    private static String url= "jdbc:mysql://mars.iuk.hdm-stuttgart.de/u-dm045?"
            + "user=dm045&password=iXaeWu0thu";

    public static Connection connection() {
                 //hier wird geprüft ob es bereits eine Verbindung zur Datenbank gibt
                if ( con == null ) {
                        try {
                                // Laden des DB- Treibers
                                Class.forName("com.mysql.jdbc.Driver").newInstance();

                                /*
                                 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
                                 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
                                 *
                                 * Diese Verbindung wird dann in der statischen Variable con
                                 * abgespeichert und fortan verwendet. @Hinweis: Author Thies
                                 */
                                con = DriverManager.getConnection(url);
                        }
                        catch (SQLException e1) {
                                con = null;
                                e1.printStackTrace();
                        }
                        catch (InstantiationException e) {
                                con = null;
                                e.printStackTrace();
                        }
                        catch (IllegalAccessException e) {
                                con = null;
                                e.printStackTrace();
                        }
                        catch (ClassNotFoundException e) {
                                con = null;
                                e.printStackTrace();
                        }
                }

                // Die Verbindung wird zurückgegeben
                return con;
        }

}

