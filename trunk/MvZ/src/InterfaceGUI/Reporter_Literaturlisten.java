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
import javax.swing.text.MaskFormatter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public class Reporter_Literaturlisten {

//Deklarierung der Variablen
JButton abbrechen;
JButton anlegen;
JFrame frame;
JTextField erscheinungsjahrField;
JComboBox werktyp;
DefaultComboBoxModel comboBoxModel1;
JList listAutor;
JScrollPane scroller;
JLabel erscheinungsjahrLabel;
JLabel autorenLabel;
JLabel typLabel;


public static void main(String[] args) {
        Reporter_Literaturlisten gui = new Reporter_Literaturlisten();
        gui.Reporter_Literaturlisten();
        }

//Methode welche den Frame, mitsamt den Panels und Komponenten erstellt
public void Reporter_Literaturlisten(){

//ein neuer Frame wird erstellt
frame = new JFrame ("Reporter");

//ein neues Panel wird erstellt
JPanel ReporterPanel = new JPanel();

//ein neues Gridbaglayout wird erstellt
GridBagLayout gbl = new GridBagLayout();

//dem buchungAnlegenPanel wird das neue Gridbaglayout zugewiesen
ReporterPanel.setLayout(gbl);

//es werden neue GridBagConstraints erstellt
GridBagConstraints constraints = new GridBagConstraints();

// Titel Label und Titel Textfeld erstellen.
erscheinungsjahrLabel= new JLabel("Erscheinungsjahr:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
ReporterPanel.add(erscheinungsjahrLabel, constraints);



try {
            erscheinungsjahrField = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
erscheinungsjahrField.setColumns(8);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
ReporterPanel.add(erscheinungsjahrField, constraints);


// Werktyp Label und Werktyp Combobox erstellen.
typLabel= new JLabel("Werktyp:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
ReporterPanel.add(typLabel, constraints);

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
ReporterPanel.add(werktyp, constraints);

//Herausgeber Label erstellen
autorenLabel = new JLabel("Autor:");
constraints.insets = new Insets(46,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
ReporterPanel.add(autorenLabel, constraints);

//JList mit Scrollbar für die Herausgeber erstellen
String[] DATA = {
    "Hund", "Katze", "Meerschweinchen", "Tiger", "Maus",
     };
listAutor = new JList(DATA);
listAutor.setSelectionMode(
ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
scroller = new JScrollPane(listAutor);
listAutor.setSelectedIndex(2);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
constraints.gridwidth = 0;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
ReporterPanel.add(scroller, constraints);

////"Autor zuordnen" Label erstellen
//autorLabel= new JLabel("Autor zuordnen:");
//constraints.weightx = 0;
//constraints.weighty = 0;
//werkAnlegenPanel.add(autorLabel, constraints);
//
////JList mit Scrollbar für "Werk zuordnen" erstellen
//String[] DATA2 = {
//    "Tolstoi", "Dan Brown", "Jo Nesbo",
//     };
//listWerk = new JList(DATA2);
//listWerk.setSelectionMode(
//ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//scroller = new JScrollPane(listWerk);
//listWerk.setSelectedIndex(2);
//scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//constraints.gridwidth = GridBagConstraints.REMAINDER;
//constraints.weightx = 1;
//constraints.fill = GridBagConstraints.NONE;
//werkAnlegenPanel.add(scroller, constraints);
//
////Label Verlag und Comboboc für Verlag erstellen
//verlagLabel= new JLabel("Verlag:");
//constraints.gridwidth = 1;
//constraints.weightx = 0;
//constraints.fill = GridBagConstraints.NONE;
//werkAnlegenPanel.add(verlagLabel, constraints);
//
////Combobox anlegen
//verlag = new JComboBox();
//Dimension groesseVerlag = new Dimension(310, 25);
//verlag.setPreferredSize(groesseVerlag);
//constraints.gridwidth = GridBagConstraints.REMAINDER;
//constraints.weightx = 1;
//constraints.fill = GridBagConstraints.NONE;
////Combobox mit vordefinierten Daten f�llen
//verlag.addItem("Springer");
//verlag.addItem("Zsr Verlag");
//verlag.addItem("");
//werkAnlegenPanel.add(verlag, constraints);

//Button anlegen erstellen + ActionListener hinzufügen
anlegen = new JButton("ausgeben");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
ReporterPanel.add(anlegen, constraints);
anlegen.addActionListener(new anlegenListener());

//Button abbrechen erstellen + ActionListener hinzufügen
abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
ReporterPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new abbrechenListener());

//Framegröße festlegen
frame.getContentPane().add(ReporterPanel);
frame.setSize(500, 500);
frame.setResizable(false);
frame.setVisible(true);
}

//Button Events zuordnen (anlegen)
class anlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

    //Auslesen der ausgewählten Elemente der JList "Herausgeber"
    int selectionsHerausgeber[] = listAutor.getSelectedIndices();
    Object selectedValuesHerausgeber[] = listAutor.getSelectedValues();
          for (int i = 0, n = selectionsHerausgeber.length; i < n; i++) {
            if (i == 0) {
              System.out.println("  Selections: ");
            }
            System.out.println(selectionsHerausgeber[i] + "/" + selectedValuesHerausgeber[i] + " ");
          }

     //Auslesen der ausgewählten Elemente der JList "Werk"
    int selectionsWerk[] = listAutor.getSelectedIndices();
    Object selectedValuesWerk[] = listAutor.getSelectedValues();
          for (int i = 0, n = selectionsWerk.length; i < n; i++) {
            if (i == 0) {
              System.out.println("  Selections: ");
            }
            System.out.println(selectionsWerk[i] + "/" + selectedValuesWerk[i] + " ");
          }

    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie die Literaturliste ausgeben?",
      "", JOptionPane.YES_NO_OPTION);
//      if (antwort == JOptionPane.YES_OPTION){
//
//        JOptionPane.showMessageDialog(frame,"Werk angelegt!");
//            frame.setVisible(false);
//      }
//        else if (antwort == JOptionPane.NO_OPTION)
//        frame.setVisible(false);
     }
}

//Button Events zuordnen (abbrechen)
class abbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}
}

