package InterfaceGUI;

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
import data.*;
/**
 *
 * @author Ben
 */
public class personAktualisieren {

    
JButton personAnlegen;
JButton abbrechen;
JFrame frame;
JTextField nachnameField;
JTextField vornameField;
JTextField titelField;
JTextField idField;
JLabel anredeLabel;
JLabel titelLabel;
JLabel vornameLabel;
JLabel nachnameLabel;
JLabel idLabel;
JComboBox anrede;

public static void main(String[] args) {
        personAktualisieren gui = new personAktualisieren();
        gui.personAktualisieren();
        }

public void personAktualisieren(){

frame = new JFrame ("Person aktualisieren");


JPanel personAktualisierenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

personAktualisierenPanel.setLayout(gbl);


GridBagConstraints constraints = new GridBagConstraints();

anredeLabel = new JLabel("Anrede:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
personAktualisierenPanel.add(anredeLabel, constraints);

anrede = new JComboBox();
String anrede1 = (String)anrede.getSelectedItem();
//if (anrede1.equals("Herr")){
//    anrede.addItem("Frau");
//}
//else if (anrede1.equals("Frau")){
//    anrede.addItem("Herr");
//}
Dimension anredeDimension = new Dimension(100, 20);
anrede.setPreferredSize(anredeDimension);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(anrede, constraints);

idLabel = new JLabel("ID:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(idLabel, constraints);
//idLabel.setVisible(false);

idField = new JTextField(3);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(idField, constraints);
idField.setEnabled(false);
//idField.setVisible(false);

titelLabel = new JLabel("Titel:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(titelLabel, constraints);


titelField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(titelField, constraints);


vornameLabel = new JLabel("Vorname:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(vornameLabel, constraints);


vornameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(vornameField, constraints);


nachnameLabel = new JLabel("Nachname:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(nachnameLabel, constraints);


nachnameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(nachnameField, constraints);



personAnlegen = new JButton("aktualisieren");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(personAnlegen, constraints);
personAnlegen.addActionListener(new personaktualisierenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new personabbrechenListener());

frame.getContentPane().add(personAktualisierenPanel);
frame.setSize(400, 400);
frame.setResizable(false);
frame.setVisible(true);


}

class personaktualisierenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie diese Person aktualisieren?",
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
//                       String ID = idField.getText();
                       int ID = Integer.parseInt(idField.getText());
                       String nachname = nachnameField.getText();
                       String vorname = vornameField.getText();
                       String titel = titelField.getText();
                       String anrede1 = (String) anrede.getSelectedItem();
                       // Hier wird entsprechend eine neue Person angelegt.
                       Person x = verwaltung.updatePerson(ID, vorname, nachname, anrede1, titel);

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

            JOptionPane.showMessageDialog(frame,"Person aktualisiert!");
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
}
