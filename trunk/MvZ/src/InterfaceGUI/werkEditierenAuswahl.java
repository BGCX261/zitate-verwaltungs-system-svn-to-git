/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Tobias
 */
public class werkEditierenAuswahl {

JButton editieren;
JButton abbrechen;
JFrame frame;
JComboBox werk;
JLabel listelabel;

    public static void main(String[] args) {
        verlagEditierenAuswahl gui = new verlagEditierenAuswahl();
        gui.verlagAktualisieren();
        }


public void verlagAktualisieren(){

frame = new JFrame("Werk editieren");

JPanel editierenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

editierenPanel.setLayout(gbl);

GridBagConstraints constraints = new GridBagConstraints();

listelabel = new JLabel("Bitte wählen Sie ein gewünschtes Werk:");
listelabel.setFont(new Font("Arial",18,18));

constraints.insets = new Insets( 16,16,16,16 );
constraints.anchor = GridBagConstraints.WEST;
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
editierenPanel.add(listelabel, constraints);

//SammelwerkMapper ga = new SammelwerkMapper();
//sammelwerk = new JComboBox(ga.findAll_fluggast());
werk = new JComboBox();
werk.addItem("Werk 1");
Dimension groesseSammelwerk = new Dimension(300, 25);
werk.setPreferredSize(groesseSammelwerk);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(werk, constraints);

editieren = new JButton("editieren");
constraints.insets = new Insets( 56,16,0,0 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(editieren, constraints);
editieren.addActionListener(new editierenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new abbrechenListener());

frame.getContentPane().add(editierenPanel);

//die Gr��e des frames wird festgelegt
frame.setSize(450, 350);

frame.setResizable(false);

//der frame wird sichtbar gemacht
frame.setVisible(true);


}

class editierenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

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
