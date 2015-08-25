/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;

import data.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import java.rmi.Naming;
import javax.swing.text.MaskFormatter;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Vector;

/**
 *
 * @author Tobias
 */
public class verlagEditierenAuswahl {

JButton editieren;
JButton abbrechen;
JFrame frame;
JComboBox verlag;
JLabel listelabel;
String Name;
String Ort;
int ID;

public String Name() {
        return Name;
    }

public String Ort() {
        return Ort;
    }

public int ID() {
        return ID;
    }


public static void main(String[] args) {
        verlagEditierenAuswahl gui = new verlagEditierenAuswahl();
        gui.verlagAktualisieren();
        }


public void verlagAktualisieren(){

frame = new JFrame("Verlag editieren");

JPanel editierenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

editierenPanel.setLayout(gbl);

GridBagConstraints constraints = new GridBagConstraints();

listelabel = new JLabel("Bitte wählen Sie einen gewünschte Verlag:");
listelabel.setFont(new Font("Arial",18,18));

constraints.insets = new Insets( 16,16,16,16 );
constraints.anchor = GridBagConstraints.WEST;
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
editierenPanel.add(listelabel, constraints);

try {
    // Zunächst benötigen wir eine Verbindung mit der Verwaltung.
    Verwaltung verwaltung =
        (Verwaltung)Naming.lookup("rmi:/localhost:1099/DRM");


    Vector<Verlag> ergebnis1 = verwaltung.getAll_Verlag();
    Vector inhalte = new Vector();
  
    //Hier wird dem Vector 'inhalte' das Objekt verlag mit deM Element Name
    //übergeben und der Combobox 'verlag' hinzugefügt
    for ( Verlag test : ergebnis1 ) {
        if (test != null ) {
            inhalte.add(test.get_name());               
        }        
    }
    
verlag = new JComboBox(inhalte);
Dimension groesseSammelwerk = new Dimension(300, 25);
verlag.setPreferredSize(groesseSammelwerk);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(verlag, constraints);

}
    catch (MalformedURLException murle) {
            System.out.println("MalformedURLException");
            System.out.println(murle);
    }
    catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);
    }
    catch (NotBoundException e) {
            System.out.println("NotBoundException");
            System.out.println(e);
    }
    catch (NullPointerException np) {
            System.out.println("NullPointerException");
            System.out.println(np);
    }

editieren = new JButton("editieren");
constraints.insets = new Insets( 56,16,0,0 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(editieren, constraints);
editieren.addActionListener(new editierenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new abbrechenListener());

frame.getContentPane().add(editierenPanel);

//die Gr��e des frames wird festgelegt
frame.setSize(450, 350);

frame.setResizable(false);

//der frame wird sichtbar gemacht
frame.setVisible(true);


}

class editierenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
    
      String ausgewaehlterVerlag = (String)verlag.getSelectedItem();  
        
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie ''" + ausgewaehlterVerlag + "'' editieren?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION) {
          
      try {
                // Zunächst benötigen wir eine Verbindung mit der Verwaltung.
                Verwaltung verwaltung =
                        (Verwaltung)Naming.lookup("rmi:/localhost:1099/DRM");

                /*
                 * Wenn wir hier ankommen, dann besteht eine Verbinung zum Server.
                 * Wäre die Verbindung nicht zustande gekommen, so wäre eine Exception
                 * ausgeworfen worden und das Programm wäre in den entsprechenden
                 * Catch-Block (s.u.) gesprungen.
                 */
                System.out.println("Verbindung hergestellt...");

                Vector<Verlag> ergebnis1 = verwaltung.getAll_Verlag();
                Vector inhalte = new Vector();

                //Hier wird Jedes Objekt des Vectors auf ggf. Titel, Name, Vorname überprüft und mit dem
                //zuvor ausgelesenen String der Combobox verglichen. Wird die entsprechende Person
                //der Combobox gefunden wird von dieser die ID ausgelesen.
                for ( Verlag test : ergebnis1 ) {
                    if (test != null ) {
                        String nameVerlag  = test.get_name();
                        if (nameVerlag.equals(ausgewaehlterVerlag)) {                               
                            int verlagID = test.get_verlag_id();                               

                            // Hier werden die Daten der entsprechende Person aus der DB gewählt. Selektiert werden diese
                            // anhand der davor festgestellten ID.
                            Verlag ve = verwaltung.findbyid_Verlag(verlagID);
                            Name = ve.get_name();
                            ID = ve.get_verlag_id();
                            Ort = ve.get_ort();
                            System.out.println(ID + Name + Ort);                                    
                            break;

//                            //Es wird die enstprechende Maske der Person aufgerufen um die Daten zu ändern oder aktualisieren
//                            verlagAktualisieren VeEd = new verlagAktualisieren();
//                            VeEd.verlagAktualisieren();  
//                            VeEd.nameField.setText(Name);
//                            VeEd.ortField.setText(Ort);                
                        }
                    }
                }
                        
               verlagAktualisieren VaEd = new verlagAktualisieren();
               VaEd.verlagAktualisieren();
               VaEd.nameField.setText(Name);
               VaEd.ortField.setText(Ort);
               VaEd.idField.setText(ID+"");

                }
                catch (MalformedURLException murle) {
                        System.out.println("MalformedURLException");
                        System.out.println(murle);
                }
                catch (RemoteException re) {
                        System.out.println("RemoteException");
                        System.out.println(re);
                }
                catch (NotBoundException e) {
                        System.out.println("NotBoundException");
                        System.out.println(e);
                }
                catch (NullPointerException np) {
                        System.out.println("NullPointerException");
                        System.out.println(np);
                }    
        
//          JOptionPane.showMessageDialog(frame,"Person angelegt!");
//          frame.setVisible(false);fgfgfggf
      }
      
      else if (antwort == JOptionPane.NO_OPTION)
      frame.setVisible(false);

    }
}



class abbrechenListener implements ActionListener{

     public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}
}
