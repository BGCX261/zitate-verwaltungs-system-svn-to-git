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
public class verlagLöschen {
    
JButton löschen;
JButton abbrechen;
JFrame frame;
JLabel listelabel;
JComboBox verlaglöschenbox;
    
    public static void main(String[] args) {
        verlagLöschen gui = new verlagLöschen();
        gui.verlagLöschen();
        }   


public void verlagLöschen(){
   


frame = new JFrame("Verlag löschen");
           
JPanel editierenPanel = new JPanel();
        
GridBagLayout gbl = new GridBagLayout();
    
editierenPanel.setLayout(gbl);
 
    
GridBagConstraints constraints = new GridBagConstraints();
        
        
listelabel = new JLabel("Bitte wählen Sie einen Verlag:");
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
    
verlaglöschenbox = new JComboBox(inhalte);
Dimension groesseVerlag = new Dimension(300, 25);
verlaglöschenbox.setPreferredSize(groesseVerlag);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(verlaglöschenbox, constraints);

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
editierenPanel.add(löschen, constraints);
löschen.addActionListener(new löschenListener());
       
        
abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(abbrechen, constraints);   
abbrechen.addActionListener(new abbrechenListener());
          
        
frame.getContentPane().add(editierenPanel);
        
//die Größe des frames wird festgelegt
frame.setSize(400, 300);
frame.setResizable(false);       
//der frame wird sichtbar gemacht
frame.setVisible(true);  
                
            
}
            
class löschenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
    
    String ausgewaehlterVerlag = (String)verlaglöschenbox.getSelectedItem();  
        
    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie ''" + ausgewaehlterVerlag + "'' wirklich löschen?",
    "", JOptionPane.YES_NO_OPTION);
    
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
                            // Hier wird die entsprechende Person anahand der zuvor ausgelesenen ID gelsöcht
                            Verlag ve = verwaltung.findbyid_Verlag(verlagID);
                            verwaltung.deleteVerlag(ve);
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

    if (antwort == JOptionPane.YES_OPTION)
    JOptionPane.showMessageDialog(frame,"Verlag wurde gelöscht!");
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
