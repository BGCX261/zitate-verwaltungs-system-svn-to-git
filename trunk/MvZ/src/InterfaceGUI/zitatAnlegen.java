package InterfaceGUI;

import data.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 *
 * @author Ben
 */
public class zitatAnlegen {

JButton anlegen;
JButton abbrechen;
JFrame frame;
JTextField absatzField;
JTextField seitenzahlField;
JLabel zitatEingeben;
JLabel absatzLabel;
JLabel seitenzahlLabel;
JLabel werkzuordnenLabel;
JTextArea zitatTextArea;
String ergebnisFelder;
JScrollPane scroller;
JComboBox werkzuordnen;



public static void main(String[] args) {
        zitatAnlegen gui = new zitatAnlegen();
        gui.zitatAnlegen();
        }


public void zitatAnlegen(){

frame = new JFrame ("Zitat anlegen");


JPanel zitatAnlegenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

zitatAnlegenPanel.setLayout(gbl);


GridBagConstraints constraints = new GridBagConstraints();

zitatEingeben = new JLabel("Zitat eingeben*:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
zitatAnlegenPanel.add(zitatEingeben, constraints);

zitatTextArea = new JTextArea (8,25);
scroller = new JScrollPane(zitatTextArea);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatTextArea.setLineWrap(true);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
zitatAnlegenPanel.add(scroller, constraints);


absatzLabel = new JLabel("Absatz:");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(absatzLabel, constraints);

absatzField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(absatzField, constraints);

seitenzahlLabel = new JLabel("Seitenzahl:");
constraints.insets = new Insets(9,9,9,9);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(seitenzahlLabel, constraints);


seitenzahlField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(seitenzahlField, constraints);


werkzuordnenLabel = new JLabel("Werk zuordnen:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(werkzuordnenLabel, constraints);

werkzuordnen = new JComboBox();
Dimension werkzuordnenDimension = new Dimension(200, 20);
werkzuordnen.setPreferredSize(werkzuordnenDimension);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(werkzuordnen, constraints);


anlegen = new JButton("anlegen");
constraints.insets = new Insets(89,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(anlegen, constraints);
anlegen.addActionListener(new anlegenListener());

abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new zitatabbrechenListener());

frame.getContentPane().add(zitatAnlegenPanel);
frame.setSize(470, 500);
frame.setResizable(false);
frame.setVisible(true);

}

class anlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Zitat anlegen?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION){

      //auslesen der einzelnen Strings der Felder. Diese werden dann in der Variable Ergebnisfelder zusammengefasst
        String inhalt = zitatTextArea.getText();
        int absatz = Integer.parseInt(absatzField.getText());
        int seitenzahl = Integer.parseInt(seitenzahlField.getText());
        String ausgewähltesWerk = (String)werkzuordnen.getSelectedItem();
        
//        JOptionPane.showMessageDialog(frame,ergebnisFelder);

        //Überprüfen ob alle Pflichtfelder ausgefüllt sind
        if (inhalt.equals("")) {
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
                        Zitat z = verwaltung.createZitat(2,1,seitenzahl,absatz,2,50,2,inhalt);

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
    
          
          
        JOptionPane.showMessageDialog(frame,"Zitat angelegt!");
            frame.setVisible(false);
      }
        else if (antwort == JOptionPane.NO_OPTION)
        frame.setVisible(true);
      }
}

class zitatabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}
}
