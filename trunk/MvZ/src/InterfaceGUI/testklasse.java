/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;





public class testklasse {

public static void main(String[] args) {
        testklasse gui = new testklasse();
        gui.testklasse();
        }

public void testklasse(){


    JFrame frame = new JFrame("table");





    String[][] zeilen = {
    { "Japan", "245" }, { "USA", "240" }, { "Italien", "220" },
    { "Spanien", "217" }, {"Türkei", "215"} ,{ "England", "214" },
    { "Frankreich", "190" }, {"Griechenland", "185" },
    { "Deutschland", "180" }, {"Portugal", "170" }
  };
    String[] spalten = {
        "Land", "Durchschnittliche Sehdauer pro Tag in Minuten"
    };

       JTable table = new JTable(zeilen, spalten );



        frame.add( new JScrollPane(table));



        //die Größe des frames wird festgelegt
        frame.setSize(600, 400);

        //der frame wird sichtbar gemacht
        frame.setVisible(true);







}
}

