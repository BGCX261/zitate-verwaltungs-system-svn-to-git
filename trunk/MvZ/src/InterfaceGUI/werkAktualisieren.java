/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public class werkAktualisieren {

//Deklarierung der Variablen
JButton abbrechen;
JButton aktualiseren;
JFrame frame;
JTextField titelField;     
JFormattedTextField jahrField;
JFormattedTextField seitenzahlField;
JComboBox werktyp; 
JComboBox verlag;   
DefaultComboBoxModel comboBoxModel1;      
JList listHerausgeber;
JList listWerk;
JScrollPane scroller;
JLabel titelLabel;
JLabel jahrLabel;
JLabel seitenzahlLabel;
JLabel herausgeberLabel;
JLabel autorLabel;
JLabel typLabel;
JLabel verlagLabel;


public static void main(String[] args) {
        werkAktualisieren gui = new werkAktualisieren();
        gui.werkAktualisieren();
        }   

//Methode welche den Frame, mitsamt den Panels und Komponenten erstellt
public void werkAktualisieren(){

//ein neuer Frame wird erstellt   
frame = new JFrame ("Werk aktualisieren");

//ein neues Panel wird erstellt
JPanel werkAnlegenPanel = new JPanel();

//ein neues Gridbaglayout wird erstellt
GridBagLayout gbl = new GridBagLayout();
    
//dem buchungAnlegenPanel wird das neue Gridbaglayout zugewiesen
werkAnlegenPanel.setLayout(gbl);
 
//es werden neue GridBagConstraints erstellt    
GridBagConstraints constraints = new GridBagConstraints();

// Titel Label und Titel Textfeld erstellen. 
titelLabel= new JLabel("Titel*:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
werkAnlegenPanel.add(titelLabel, constraints);

titelField = new JTextField(50);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(titelField, constraints);

// Jahr Label und Jahr Textfeld erstellen. 
jahrLabel = new JLabel("Jahr:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(jahrLabel, constraints);
        
try {
            jahrField = new JFormattedTextField(new MaskFormatter("##.##.####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
jahrField.setColumns(8);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(jahrField, constraints);

// Seitenzahl Label und Seitenzahl Textfeld erstellen. 
seitenzahlLabel = new JLabel("Seitenzahl:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(seitenzahlLabel, constraints);
        
try {
            seitenzahlField = new JFormattedTextField(new MaskFormatter("#####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
seitenzahlField.setColumns(5);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(seitenzahlField, constraints);

// Werktyp Label und Werktyp Combobox erstellen.
typLabel= new JLabel("Werktyp:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(typLabel, constraints);

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
werkAnlegenPanel.add(werktyp, constraints);

//Herausgeber Label erstellen
herausgeberLabel = new JLabel("Herausgeber:");
constraints.insets = new Insets(46,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(herausgeberLabel, constraints);

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
werkAnlegenPanel.add(scroller, constraints);

//"Autor zuordnen" Label erstellen
autorLabel= new JLabel("Autor zuordnen:");
constraints.weightx = 0;
constraints.weighty = 0;
werkAnlegenPanel.add(autorLabel, constraints);

//JList mit Scrollbar für "Werk zuordnen" erstellen
String[] DATA2 = {
    "Tolstoi", "Dan Brown", "Jo Nesbo",
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
//listWerk.addListSelectionListener((ListSelectionListener) new listWerkauswahlListener());
werkAnlegenPanel.add(scroller, constraints);

//Label Verlag und Comboboc für Verlag erstellen
verlagLabel= new JLabel("Verlag:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(verlagLabel, constraints);

//Combobox anlegen
verlag = new JComboBox();
Dimension groesseVerlag = new Dimension(310, 25);
verlag.setPreferredSize(groesseVerlag);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
//Combobox mit vordefinierten Daten f�llen
verlag.addItem("Springer");
verlag.addItem("Zsr Verlag");
verlag.addItem("");
werkAnlegenPanel.add(verlag, constraints);

//Button anlegen erstellen + ActionListener hinzufügen
aktualiseren = new JButton("aktualisieren");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(aktualiseren, constraints);
aktualiseren.addActionListener(new aktualiserenListener());

//Button abbrechen erstellen + ActionListener hinzufügen
abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new werkabbrechenListener());

//Framegröße festlegen
frame.getContentPane().add(werkAnlegenPanel);
frame.setSize(750, 600);
frame.setResizable(false);
frame.setVisible(true);
}

//Button Events zuordnen (anlegen)
class aktualiserenListener implements ActionListener{

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

     //Auslesen der ausgewählten Elemente der JList "Werk"
    int selectionsWerk[] = listWerk.getSelectedIndices();
    Object selectedValuesWerk[] = listWerk.getSelectedValues();
          for (int i = 0, n = selectionsWerk.length; i < n; i++) {
            if (i == 0) {
              System.out.println("  Selections: ");
            }
            System.out.println(selectionsWerk[i] + "/" + selectedValuesWerk[i] + " ");
          }

    //Abfrage ob auczh wirklich aktualisiert werden soll....
    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Werk aktualsieren?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION){
    //Wenn JA dann....
        JOptionPane.showMessageDialog(frame,"Werk aktualisiert!");
            frame.setVisible(false);
      }
    //Wenn NEIN dann...
        else if (antwort == JOptionPane.NO_OPTION)
        frame.setVisible(false);
      }
}

//Button Events zuordnen (abbrechen)
class werkabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
      
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);       
    }
}

//class listWerkauswahlListener implements ActionListener{

}