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


JLabel anredeLabel= new JLabel("Anrede:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
personAktualisierenPanel.add(anredeLabel, constraints);

Vector<String> anredevector = new Vector<String>();
anredevector.add("Herr");
anredevector.add("Frau");

JComboBox anrede = new JComboBox(anredevector);
Dimension anredeDimension = new Dimension(100, 20);
anrede.setPreferredSize(anredeDimension);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(anrede, constraints);


JLabel titelLabel= new JLabel("Titel:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(titelLabel, constraints);


titelField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(titelField, constraints);


JLabel vornameLabel= new JLabel("Vorname:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(vornameLabel, constraints);


vornameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(vornameField, constraints);


JLabel nachnameLabel= new JLabel("Nachname:");
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
personAnlegen.addActionListener(new personanlegenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
personAktualisierenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new personabbrechenListener());

frame.getContentPane().add(personAktualisierenPanel);
frame.setSize(400, 350);
frame.setResizable(false);
frame.setVisible(true);


}

class personanlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie diese Person aktualisieren?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION) {



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


