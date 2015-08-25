/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;

import data.*;
//import datenbank.personMapper;
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
public class personEditierenAuswahl {

JButton editieren;
JButton abbrechen;
JFrame frame;
JLabel listelabel;
JComboBox person;
String Vorname;
String Titel;
String Name;
String Anrede;
int ID;

public String Vorname() {
        return Vorname;
    }

public String Name() {
        return Name;
    }

public String Titel() {
        return Titel;
    }

public String Anrede() {
        return Anrede;
    }

public int ID() {
        return ID;
    }


public static void main(String[] args) {
        personEditierenAuswahl gui = new personEditierenAuswahl();
        gui.personAktualisieren();
        }


public void personAktualisieren(){

frame = new JFrame("Person editieren");

JPanel editierenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

editierenPanel.setLayout(gbl);

GridBagConstraints constraints = new GridBagConstraints();

listelabel = new JLabel("Bitte wählen Sie eine gewünschte Person:");
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


    Vector<Person> ergebnis1 = verwaltung.getAll_Person();
    Vector inhalte = new Vector();
  
    //Hier wird dem Vector 'inhalte' das Objekt Person mit den Elementen Titel, Vorname und Name 
    //übergeben und der Combobox 'person' hinzugefügt
    for ( Person test : ergebnis1 ) {
        if (test != null ) {
            //Es wird überprüft ob die Person einen Titel besitzt. Trifft dies nicht zu wird dieser
            //in der Combobox weggelassen.
            if (test.get_titel().equals("no title")) {
                inhalte.add(test.get_vorname() + "  " + test.get_name());               
            }
            else
                inhalte.add(test.get_titel()+ ". " + test.get_vorname() + "  " + test.get_name());
        }
    }

    person = new JComboBox(inhalte);
    //person = new JComboBox();
    Dimension groesseSammelwerk = new Dimension(300, 25);
    person.setPreferredSize(groesseSammelwerk);
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.weightx = 1;
    constraints.fill = GridBagConstraints.NONE;
    editierenPanel.add(person, constraints);

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

//die Groeße des frames wird festgelegt
frame.setSize(450, 350);

frame.setResizable(false);

//der frame wird sichtbar gemacht
frame.setVisible(true);


}

class editierenListener implements ActionListener{
   
    public void actionPerformed(ActionEvent event){
  
      String ausgewaehltePerson = (String)person.getSelectedItem();
             
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie ''" + ausgewaehltePerson + "'' editieren?",
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
                        
                        Vector<Person> ergebnis1 = verwaltung.getAll_Person();
                        Vector inhalte = new Vector();

                        //Hier wird Jedes Objekt des Vectors auf ggf. Titel, Name, Vorname überprüft und mit dem
                        //zuvor ausgelesenen String der Combobox verglichen. Wird die entsprechende Person
                        //der Combobox gefunden wird von dieser die ID ausgelesen.
                        for ( Person test : ergebnis1 ) {
                            if (test != null ) {
                                String NameOhneTitel  = test.get_vorname() + "  " + test.get_name(); 
                                String NameMitTitel = test.get_titel()+ ". " + test.get_vorname() + "  " + test.get_name();
                                if (NameOhneTitel.equals(ausgewaehltePerson) || NameMitTitel.equals(ausgewaehltePerson) ) {                               
                                    int personID = test.get_person_id();                               
                                    
                                    // Hier werden die Daten der entsprechende Person aus der DB gewählt. Selektiert werden diese
                                    // anhand der davor festgestellten ID.
                                    Person pe = verwaltung.findbyid_Person(personID);
                                    Anrede = pe.get_anrede();
                                    Name = pe.get_name();
                                    ID = pe.get_person_id();
                                    Titel= pe.get_titel();
                                    Vorname = pe.get_vorname();
                                    System.out.println(ID + Titel + Anrede + Vorname + Name );                                    
                                    break;
                                
                                    //Es wird die enstprechende Maske der Person aufgerufen um die Daten zu ändern oder aktualisieren
                                                  
                                }
                            }
                        }
                        
                       personAktualisieren PeEd = new personAktualisieren();
                       PeEd.personAktualisieren();
                       PeEd.nachnameField.setText(Name);
                       PeEd.vornameField.setText(Vorname);
                       PeEd.titelField.setText(Titel);
                       PeEd.anrede.addItem(Anrede);

                       PeEd.idField.setText(ID+"");

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
