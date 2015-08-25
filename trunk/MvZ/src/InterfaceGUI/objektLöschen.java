/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import java.util.Vector;

/**
 *
 * @author Ben
 */
public class objektLöschen {

    JLabel listelabel;
    JButton löschen;
    JButton abbrechen;
    JFrame frame;
    JComboBox boxanlegen;


    public static void main(String[] args) {
        objektLöschen gui = new objektLöschen();
        gui.objektLöschen();
        }


public void objektLöschen(){



        frame = new JFrame("löschen");

        JPanel löschenPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();

        löschenPanel.setLayout(gbl);


        GridBagConstraints constraints = new GridBagConstraints();

        listelabel= new JLabel("<html>Was möchten sie löschen?<br/>Bitte wählen:");
        listelabel.setFont(new Font("Arial",18,18));

        constraints.insets = new Insets( 16,16,16,16 );
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        löschenPanel.add(listelabel, constraints);

        //Vector flugData erstellen und Jcombobox mit diesen Daten füllen
        Vector<String> löschenvector = new Vector<String>();
        löschenvector.add("Zitat löschen");
        löschenvector.add("Person löschen");
        löschenvector.add("Sammelwerk löschen");
        löschenvector.add("Werk löschen");
        löschenvector.add("Verlag löschen");


        boxanlegen = new JComboBox(löschenvector);
        Dimension groesseObjekt = new Dimension(300, 25);
        boxanlegen.setPreferredSize(groesseObjekt);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.NONE;
        löschenPanel.add(boxanlegen, constraints);


        löschen = new JButton("löschen");
        constraints.insets = new Insets( 56,16,0,0 );
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        löschenPanel.add(löschen, constraints);
        löschen.addActionListener(new fluganlegenListener());


        abbrechen = new JButton("abbrechen");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        löschenPanel.add(abbrechen, constraints);
        abbrechen.addActionListener(new fluganlegenabbrechenListener());


        frame.getContentPane().add(löschenPanel);

        //die Größe des frames wird festgelegt
        frame.setSize(400, 300);

        frame.setResizable(false);
        //der frame wird sichtbar gemacht
        frame.setVisible(true);


        }

class fluganlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
        String auswahl = (String)boxanlegen.getSelectedItem();

        if (auswahl.equals("Zitat löschen")) {
          zitatLöschen z = new zitatLöschen();
          z.zitatLöschen();
          frame.setVisible(false);}

         if (auswahl.equals("Person löschen")) {
          personLöschen p = new personLöschen();
          p.personLöschen();
          frame.setVisible(false);}

         if (auswahl.equals("Sammelwerk löschen")) {
          sammelwerkLöschen s = new sammelwerkLöschen();
          s.sammelwerkLöschen();
          frame.setVisible(false);}

         if (auswahl.equals("Werk löschen")) {
          werkLöschen w = new werkLöschen();
          w.werkLöschen();
          frame.setVisible(false);}

         if (auswahl.equals("Verlag löschen")) {
          verlagLöschen v = new verlagLöschen();
          v.verlagLöschen();
          frame.setVisible(false);}

    }
}

class fluganlegenabbrechenListener implements ActionListener{

     public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}
}
