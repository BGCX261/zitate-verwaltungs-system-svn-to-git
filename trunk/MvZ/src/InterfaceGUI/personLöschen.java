package InterfaceGUI;

import data.*;
import javax.swing.*;
import datenbank.personMapper;
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
 * @author Ben
 */
public class personLöschen {


JButton löschen;
JButton abbrechen;
JLabel listelabel;
JFrame frame;
JComboBox personlöschenbox;


    public static void main(String[] args) {
        personLöschen gui = new personLöschen();
        gui.personLöschen();
        }


public void personLöschen(){

frame = new JFrame("Person löschen");

JPanel löschenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

löschenPanel.setLayout(gbl);


GridBagConstraints constraints = new GridBagConstraints();


listelabel = new JLabel("Bitte wählen Sie eine Person:");
listelabel.setFont(new Font("Arial",18,18));

constraints.insets = new Insets( 16,16,16,16 );
constraints.anchor = GridBagConstraints.WEST;
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
löschenPanel.add(listelabel, constraints);


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


    personlöschenbox = new JComboBox(inhalte);
    Dimension groesseFluggast = new Dimension(300, 25);
    personlöschenbox.setPreferredSize(groesseFluggast);
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.weightx = 1;
    constraints.fill = GridBagConstraints.NONE;
    löschenPanel.add(personlöschenbox, constraints);

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

löschen = new JButton("löschen");
constraints.insets = new Insets( 56,16,0,0 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
löschenPanel.add(löschen, constraints);
löschen.addActionListener(new löschenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
löschenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new abbrechenListener());


frame.getContentPane().add(löschenPanel);

//die Größe des frames wird festgelegt
frame.setSize(400, 300);

frame.setResizable(false);

//der frame wird sichtbar gemacht
frame.setVisible(true);


}

class löschenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      String ausgewaehltePerson = (String)personlöschenbox.getSelectedItem();  
          
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie ''" + ausgewaehltePerson + "'' wirklich löschen?",
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

                        // Von der Verwaltung bekommen wir das Bank-Objekt.
                        //Bank bank = verwaltung.getBank();
                        
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
                                    // Hier wird die entsprechende Person anahand der zuvor ausgelesenen ID gelsöcht
                                    Person pe = verwaltung.findbyid_Person(personID);
                                    verwaltung.deletePerson(pe);                                  
                                                                    
                                }
                            }
                        }

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
          
          
            JOptionPane.showMessageDialog(frame,"Person gelöscht!");
            frame.setVisible(false);
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
