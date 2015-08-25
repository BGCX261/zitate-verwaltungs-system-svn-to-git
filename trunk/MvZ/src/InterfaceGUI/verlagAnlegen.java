package InterfaceGUI;
//import data.Verlag;
//import data.Verwaltung;
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
/**
 *
 * @author Ben
 */
public class verlagAnlegen {
 
JButton anlegen;
JButton abbrechen;
JFrame frame; 
JTextField nameField;
JTextField strasseField;
JFormattedTextField plzField;
JTextField ortField;
JLabel nameLabel;
JLabel nachnameLabel;
JLabel strasseLabel;
JLabel plzLabel;
JLabel ortLabel;


public static void main(String[] args) {
        verlagAnlegen gui = new verlagAnlegen();
        gui.verlagAnlegen();
        }   

public void verlagAnlegen(){

frame = new JFrame ("Verlag anlegen");


JPanel verlagAnlegenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();
    
verlagAnlegenPanel.setLayout(gbl);
 
    
GridBagConstraints constraints = new GridBagConstraints();


nameLabel = new JLabel("Verlagsname*:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
verlagAnlegenPanel.add(nameLabel, constraints);


nameField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
verlagAnlegenPanel.add(nameField, constraints);



//strasseLabel = new JLabel("Straße:");
//constraints.gridwidth = 1;
//constraints.weightx = 0;
//constraints.fill = GridBagConstraints.NONE;
//verlagAnlegenPanel.add(strasseLabel, constraints);
//
//
//strasseField = new JTextField(20);
//constraints.gridwidth = GridBagConstraints.REMAINDER;
//constraints.weightx = 1;
//constraints.fill = GridBagConstraints.NONE;
//verlagAnlegenPanel.add(strasseField, constraints);
//
//
//plzLabel = new JLabel("Plz:");
//constraints.gridwidth = 1;
//constraints.weightx = 0;
//constraints.fill = GridBagConstraints.NONE;
//verlagAnlegenPanel.add(plzLabel, constraints);
//
//
//try {
//            plzField = new JFormattedTextField(new MaskFormatter("#####"));
//        } catch (ParseException ex) {
//            Logger.getLogger(verlagAnlegen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//plzField.setColumns(6);
//constraints.gridwidth = GridBagConstraints.REMAINDER;
//constraints.weightx = 1;
//constraints.fill = GridBagConstraints.NONE;
//verlagAnlegenPanel.add(plzField, constraints);


ortLabel = new JLabel("Ort:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAnlegenPanel.add(ortLabel, constraints);


ortField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
verlagAnlegenPanel.add(ortField, constraints);


anlegen = new JButton("anlegen");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAnlegenPanel.add(anlegen, constraints);
anlegen.addActionListener(new anlegenListener());

abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
verlagAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new verlagabbrechenListener());

frame.getContentPane().add(verlagAnlegenPanel);
frame.setSize(400, 350);
frame.setResizable(false);
frame.setVisible(true);


}

class anlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
        
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie diesen Verlag anlegen?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION) {

        String verlagname = nameField.getText();
        String ort = ortField.getText();
//        JOptionPane.showMessageDialog(frame,text + text2);

//              
//    
//        
//         //Überprüfen ob alle Felder ausgefüllt sind. Wenn nicht kann das Objekt nicht angelegt werden
//        if (text.equals("") || (text3.equals(""))) {
//            JOptionPane.showMessageDialog(frame,"Bitte alle Pflichtfelder ausfüllen!");                
//        }
//                
//        else {       
//            
            try {
                        // Zunächst benötigen wir eine Verbindung mit einer Bankverwaltung.
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

                        // Die Bank bitten wir, einen neuen Kunden anzulegen.

                        Verlag ve = verwaltung.createVerlag(1,verlagname,ort);      

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



            JOptionPane.showMessageDialog(frame,"Verlag angelegt!");
            frame.setVisible(false);
        }
//      }        
//        else if (antwort == JOptionPane.NO_OPTION)
//        frame.setVisible(false);
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


