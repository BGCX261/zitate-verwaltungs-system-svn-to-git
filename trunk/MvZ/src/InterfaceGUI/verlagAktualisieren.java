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
public class verlagAktualisieren {
 
JButton aktualisieren;
JButton abbrechen;
JFrame frame; 
JTextField nameField;    
JTextField strasseField;
JTextField idField;
JFormattedTextField plzField;
JTextField ortField;
JLabel nachnameLabel;
JLabel strasseLabel;
JLabel plzLabel;
JLabel ortLabel;
JLabel idLabel;
String ergebnisFelder;


public static void main(String[] args) {
        verlagAktualisieren gui = new verlagAktualisieren();
        gui.verlagAktualisieren();
        }   

public void verlagAktualisieren(){

frame = new JFrame ("Verlag aktualisieren");


JPanel verlagAktualisierenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();
    
verlagAktualisierenPanel.setLayout(gbl);
    
GridBagConstraints constraints = new GridBagConstraints();

idLabel = new JLabel("ID:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
verlagAktualisierenPanel.add(idLabel, constraints);
//idLabel.setVisible(false);

idField = new JTextField(3);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(idField, constraints);
idField.setEnabled(false);
//idField.setVisible(false);


nachnameLabel = new JLabel("Name:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(nachnameLabel, constraints);


nameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(nameField, constraints);


//
//strasseLabel = new JLabel("Straße:");
//constraints.gridwidth = 1;
//constraints.weightx = 0;
//constraints.fill = GridBagConstraints.NONE;
//verlagAktualisierenPanel.add(strasseLabel, constraints);
//
//
//strasseField = new JTextField(20);
//constraints.gridwidth = GridBagConstraints.REMAINDER;
//constraints.weightx = 1;
//constraints.fill = GridBagConstraints.NONE;
//verlagAktualisierenPanel.add(strasseField, constraints);
//
//
//plzLabel = new JLabel("Plz:");
//constraints.gridwidth = 1;
//constraints.weightx = 0;
//constraints.fill = GridBagConstraints.NONE;
//verlagAktualisierenPanel.add(plzLabel, constraints);
//
//
//try {
//            plzField = new JFormattedTextField(new MaskFormatter("#####"));
//        } catch (ParseException ex) {
//            Logger.getLogger(verlagAktualisieren.class.getName()).log(Level.SEVERE, null, ex);
//        }
//plzField.setColumns(6);
//constraints.gridwidth = GridBagConstraints.REMAINDER;
//constraints.weightx = 1;
//constraints.fill = GridBagConstraints.NONE;
//verlagAktualisierenPanel.add(plzField, constraints);

ortLabel = new JLabel("Ort:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(ortLabel, constraints);


ortField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(ortField, constraints);


aktualisieren = new JButton("aktualisieren");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(aktualisieren, constraints);
aktualisieren.addActionListener(new aktualisierenListener());

abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAktualisierenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new verlagabbrechenListener());

frame.getContentPane().add(verlagAktualisierenPanel);
frame.setSize(400, 300);
frame.setResizable(false);
frame.setVisible(true);

}

class aktualisierenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
        
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie diesen Verlag aktualisieren?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION){
        
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
                       String name = nameField.getText();
                       String ort = ortField.getText();
                       System.out.println(ID + name + ort); 
                       
                       // Hier wird entsprechend eine neue Person angelegt.
                       Verlag x = verwaltung.updateVerlag(ID, name, ort);

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

            JOptionPane.showMessageDialog(frame,"Verlag aktualisiert!");
            frame.setVisible(false);
        }

        else if (antwort == JOptionPane.NO_OPTION)
        frame.setVisible(false);
}
}
class verlagabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
      
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);       
    }
}
}


