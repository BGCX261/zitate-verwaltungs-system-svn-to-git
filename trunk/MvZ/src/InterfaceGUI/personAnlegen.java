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
 * @author Ben
 */
public class personAnlegen {

JButton personAnlegen;
JButton abbrechen;
JFrame frame;
JTextField nachnameField;
JTextField vornameField;
JTextField titelField;
JLabel anredeLabel;
JLabel titelLabel;
JLabel vornameLabel;
JLabel nachnameLabel;
JComboBox anrede;

public static void main(String[] args) {
        personAnlegen gui = new personAnlegen();
        gui.personAnlegen();
        }

public void personAnlegen(){

frame = new JFrame ("Person anlegen");


JPanel personAnlegenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

personAnlegenPanel.setLayout(gbl);

GridBagConstraints constraints = new GridBagConstraints();

anredeLabel = new JLabel("Anrede:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
personAnlegenPanel.add(anredeLabel, constraints);

Vector<String> anredevector = new Vector<String>();
anredevector.add("Herr");
anredevector.add("Frau");

anrede = new JComboBox(anredevector);
Dimension anredeDimension = new Dimension(100, 20);
anrede.setPreferredSize(anredeDimension);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(anrede, constraints);


titelLabel = new JLabel("Titel:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(titelLabel, constraints);


titelField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(titelField, constraints);


vornameLabel = new JLabel("Vorname*:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(vornameLabel, constraints);


vornameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(vornameField, constraints);


nachnameLabel = new JLabel("Nachname*:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(nachnameLabel, constraints);


nachnameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(nachnameField, constraints);


personAnlegen = new JButton("anlegen");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(personAnlegen, constraints);
personAnlegen.addActionListener(new personanlegenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
personAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new personabbrechenListener());

frame.getContentPane().add(personAnlegenPanel);
frame.setSize(400, 350);
frame.setResizable(false);
frame.setVisible(true);


}

class personanlegenListener implements ActionListener{
    private String ergebnisFelder;
   
    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie diese Person anlegen?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION) {

        //auslesen der einzelnen Strings der Felder. Diese werden dann in der Variable Ergebnisfelder zusammengefasst
        String vorname = vornameField.getText();
        String nachname = nachnameField.getText();
        String anrede1 = (String)anrede.getSelectedItem();
        String titel = titelField.getText();
              
        ergebnisFelder = (vorname+nachname+anrede1+titel);
//        JOptionPane.showMessageDialog(frame,ergebnisFelder);
        
        //Überprüfen ob die zu anlegende Person einen Titel trägt. Wenn Nein wird der leere String durch
        // Füllzeichen ersetzt um keine Konflikte mit der DB zu bekommen
        if (titel.equals("")) {
            titel = "no title";
        }
                
        //Überprüfen ob alle Pflichtfelder ausgefüllt sind
        if (vorname.equals("") || (nachname.equals(""))) {
            JOptionPane.showMessageDialog(frame,"Bitte alle Pflichtfelder ausfüllen!");            
        
        //Hier muss noch eingebaut bwerden dass der Prozess nicht weiterläuft     
        
        }
        
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

                        // Hier wird entsprechend eine neue Person angelegt.
                        Person x = verwaltung.createPerson(1, vorname, nachname, anrede1, titel);

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


          JOptionPane.showMessageDialog(frame,"Person angelegt!");
          frame.setVisible(false);
      }
      
      else if (antwort == JOptionPane.NO_OPTION)
      frame.setVisible(false);
}
}
class personabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}


String get_vorname() {
return vornameField.getText(); }

String get_name() {
return nachnameField.getText(); }

String get_titel() {
return vornameField.getText(); }



}


