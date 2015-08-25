/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Tobias
 */
public class sammelwerkLöschen {
    
JButton löschen;
JButton abbrechen;
JFrame frame;
    
    
    public static void main(String[] args) {
        sammelwerkLöschen gui = new sammelwerkLöschen();
        gui.sammelwerkLöschen();
        }   

public void sammelwerkLöschen(){
   
frame = new JFrame("Sammelwerk löschen");
           
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
      
//Vector erstellen und Jcombobox mit diesen Daten f�llen
Vector<String> editierenvector = new Vector<String>();

        
JComboBox boxanlegen = new JComboBox(editierenvector);
Dimension groesseFluggast = new Dimension(300, 25);
boxanlegen.setPreferredSize(groesseFluggast);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(boxanlegen, constraints);
        
      
löschen = new JButton("löschen");
constraints.insets = new Insets( 56,16,0,0 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
editierenPanel.add(löschen, constraints);
löschen.addActionListener(new editierenListener());
       
        
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

    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Sammelwerk wirklich löschen?",
    "", JOptionPane.YES_NO_OPTION);

    if (antwort == JOptionPane.YES_OPTION)
    JOptionPane.showMessageDialog(frame,"Sammelwerk wurde gelöscht!");
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
    
}}