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
import javax.swing.text.MaskFormatter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public class sammelwerkAnlegen {

//Deklarierung der Variablen
JButton abbrechen;
JButton anlegen;
JFrame frame;
JTextField titelField;     
JFormattedTextField jahrField;
JFormattedTextField seitenzahlField;
JComboBox werktyp;    
DefaultComboBoxModel comboBoxModel1;      
JList listHerausgeber;
JList listWerk;
JScrollPane scroller;
JLabel titelLabel;
JLabel jahrLabel;
JLabel seitenzahlLabel;
JLabel herausgeberLabel;
JLabel werkezuordnenLabel;
JLabel typLabel;



public static void main(String[] args) {
        sammelwerkAnlegen gui = new sammelwerkAnlegen();
        gui.sammelwerkAnlegen();
        }   

//Methode welche den Frame, mitsamt den Panels und Komponenten erstellt
public void sammelwerkAnlegen(){

//ein neuer Frame wird erstellt   
frame = new JFrame ("Sammelwerk anlegen");

//ein neues Panel wird erstellt
JPanel sammelwerkAnlegenPanel = new JPanel();

//ein neues Gridbaglayout wird erstellt
GridBagLayout gbl = new GridBagLayout();
    
//dem buchungAnlegenPanel wird das neue Gridbaglayout zugewiesen
sammelwerkAnlegenPanel.setLayout(gbl);
 
//es werden neue GridBagConstraints erstellt    
GridBagConstraints constraints = new GridBagConstraints();

// Titel Label und Titel Textfeld erstellen. 
titelLabel = new JLabel("Titel*:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
sammelwerkAnlegenPanel.add(titelLabel, constraints);

titelField = new JTextField(50);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(titelField, constraints);

// Jahr Label und Jahr Textfeld erstellen. 
jahrLabel = new JLabel("Jahr:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(jahrLabel, constraints);
        
try {
            jahrField = new JFormattedTextField(new MaskFormatter("##.##.####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
jahrField.setColumns(8);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(jahrField, constraints);

// Seitenzahl Label und Seitenzahl Textfeld erstellen. 
seitenzahlLabel= new JLabel("Seitenzahl:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(seitenzahlLabel, constraints);
        
try {
            seitenzahlField = new JFormattedTextField(new MaskFormatter("#####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
seitenzahlField.setColumns(5);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(seitenzahlField, constraints);

typLabel= new JLabel("Werktyp:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(typLabel, constraints);

//Combobox anlegen
werktyp = new JComboBox();
Dimension groesseWerktyp = new Dimension(310, 25);
werktyp.setPreferredSize(groesseWerktyp);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
//Combobox mit vordefinierten Daten f�llen
werktyp.addItem("Buch");
werktyp.addItem("Zeitschrift");
werktyp.addItem("Thesis");
werktyp.addItem("");
sammelwerkAnlegenPanel.add(werktyp, constraints);

//Herausgeber Label erstellen
herausgeberLabel= new JLabel("Herausgeber:");
constraints.insets = new Insets(46,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(herausgeberLabel, constraints);

//JList mit Scrollbar für die Herausgeber erstellen
String[] DATA = {
    "Hund", "Katze", "Meerschweinchen", "Tiger", "Maus",     
     };
listHerausgeber = new JList(DATA);
listHerausgeber.setSelectionMode(
ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
scroller = new JScrollPane(listHerausgeber);
listHerausgeber.setSelectedIndex(2);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
constraints.gridwidth = 1;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(scroller, constraints);

//"Werk zuordnen" Label erstellen
werkezuordnenLabel= new JLabel("Werke zuordnen:");
constraints.weightx = 0;
constraints.weighty = 0;
sammelwerkAnlegenPanel.add(werkezuordnenLabel, constraints);

//JList mit Scrollbar für "Werk zuordnen" erstellen
String[] DATA2 = {
    "Hund", "Katze", "Meerschweinchen", "Tiger", "Maus",
     };
listWerk = new JList(DATA2);
listWerk.setSelectionMode(
ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
scroller = new JScrollPane(listWerk);
listWerk.setSelectedIndex(2);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(scroller, constraints);


//Button anlegen erstellen + ActionListener hinzufügen
anlegen = new JButton("anlegen");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(anlegen, constraints);
anlegen.addActionListener(new anlegenListener());

//Button abbrechen erstellen + ActionListener hinzufügen
abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
sammelwerkAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new sammelwerkabbrechenListener());

//Framegröße festlegen
frame.getContentPane().add(sammelwerkAnlegenPanel);
frame.setSize(750, 500);
frame.setResizable(false);
frame.setVisible(true);
}

//Button Events zuordnen (anlegen)
class anlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

    //Auslesen der ausgewählten Elemente der JList "Herausgeber"
    int selectionsHerausgeber[] = listHerausgeber.getSelectedIndices();
    Object selectedValuesHerausgeber[] = listHerausgeber.getSelectedValues();
          for (int i = 0, n = selectionsHerausgeber.length; i < n; i++) {
            if (i == 0) {
              System.out.println("  Selections: ");
            }
            System.out.println(selectionsHerausgeber[i] + "/" + selectedValuesHerausgeber[i] + " ");
          }

    //Auslesen der ausgewählten Elemente der JList "Werke zuordnen"
    int selectionsWerkeZuordnen[] = listWerk.getSelectedIndices();
    Object selectedValuesWerkeZuordnen[] = listWerk.getSelectedValues();
          for (int i = 0, n = selectionsWerkeZuordnen.length; i < n; i++) {
            if (i == 0) {
              System.out.println("  Selections: ");
            }
            System.out.println(selectionsWerkeZuordnen[i] + "/" + selectedValuesWerkeZuordnen[i] + " ");
          }


    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Sammelwerk anlegen?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION){
          
        //auslesen der einzelnen Strings der Felder. Diese werden dann in der Variable Ergebnisfelder zusammengefasst
        String titel = titelField.getText();
        int jahr = Integer.parseInt(jahrField.getText());
        int seitenzahl = Integer.parseInt(seitenzahlField.getText());
                       
//        JOptionPane.showMessageDialog(frame,ergebnisFelder);
        
        //Überprüfen ob die zu anlegende Person einen Titel trägt. Wenn Nein wird der leere String durch
        // Füllzeichen ersetzt um keine Konflikte mit der DB zu bekommen
        if (titel.equals("")) {
            titel = "no title";
        }
                
        //Überprüfen ob alle Pflichtfelder ausgefüllt sind
        if (titel.equals("")) {
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
                        //Sammelwerk x = verwaltung.createWerk(titel, jahr, seitenzahl, werktyp);

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
  
          

        JOptionPane.showMessageDialog(frame,"Sammelwerk angelegt!");
            frame.setVisible(false);
      }
        else if (antwort == JOptionPane.NO_OPTION)
        frame.setVisible(false);
      }
}

//Button Events zuordnen (abbrechen)
class sammelwerkabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
      
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);       
    }
}}
