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
public class sammelwerkEditieren {
    
JButton aktualisieren;
JButton abbrechen;
JFrame frame;
JComboBox sammelwerk;    
    
    public static void main(String[] args) {
        sammelwerkEditieren gui = new sammelwerkEditieren();
        gui.sammelwerkEditieren();
        }   


public void sammelwerkEditieren(){
   
frame = new JFrame("Sammelwerk editieren");
           
JPanel editierenPanel = new JPanel();
        
GridBagLayout gbl = new GridBagLayout();
    
editierenPanel.setLayout(gbl);
     
GridBagConstraints constraints = new GridBagConstraints();
                
JLabel listelabel= new JLabel("Bitte wählen Sie ein gewünschtes Sammelwerk:");
listelabel.setFont(new Font("Arial",18,18));
        
constraints.insets = new Insets( 16,16,16,16 );
constraints.anchor = GridBagConstraints.WEST;
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
editierenPanel.add(listelabel, constraints);

//SammelwerkMapper ga = new SammelwerkMapper();
//sammelwerk = new JComboBox(ga.findAll_fluggast());
sammelwerk = new JComboBox();
sammelwerk.addItem("Bachelorarbeit");
Dimension groesseSammelwerk = new Dimension(300, 25);
sammelwerk.setPreferredSize(groesseSammelwerk);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(sammelwerk, constraints);

aktualisieren = new JButton("aktualisieren");
constraints.insets = new Insets( 56,16,0,0 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(aktualisieren, constraints);
aktualisieren.addActionListener(new editierenListener());
       
        
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
         
    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Sammelwerk aktualisieren?",
    "", JOptionPane.YES_NO_OPTION);

    if (antwort == JOptionPane.YES_OPTION)
    JOptionPane.showMessageDialog(frame,"Sammelwerk wurde aktualisiert!");
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
