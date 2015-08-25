/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.regex.Pattern;
/**
 *
 * @author Ben
 */
public class editor {

   JMenuItem editormenue;
   JMenuItem reportermenue;
   JMenuItem anlegen;
   JMenuItem editieren;
   JMenuItem löschen;
   JMenuItem systemBeenden;
   JFrame frame;

   public void editor() {


        frame = new JFrame("Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel editor = new JPanel();
        editor.setBackground(Color.ORANGE);
        editor.setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Objekte anzeigen");

        DefaultMutableTreeNode child = new DefaultMutableTreeNode("Zitate");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Personen");
        DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Sammelwerke");
        DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("Werke");
        DefaultMutableTreeNode child5 = new DefaultMutableTreeNode("Verlage");

        root.add(child);
        root.add(child2);
        root.add(child3);
        root.add(child4);
        root.add(child5);

        final JTree tree = new JTree(root);

        tree.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
        TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
        if(tp != null) {
           doMouseClicked(me, tree);
        }
         }
        });

        JLabel überschrift = new JLabel("       Editor: Zitatverwaltungssystem");

        überschrift.setFont(new Font("Arial",28,28));

        editor.add(überschrift,BorderLayout.CENTER);

        JMenuBar menuebar = new JMenuBar();
        JMenu gui = new JMenu("GUI");
        JMenu verwalten = new JMenu("Verwalten");
        JMenu exit = new JMenu("Exit");

        editormenue = new JMenuItem("Editor");
        editormenue.addActionListener(new editormenueListener());
        reportermenue = new JMenuItem("Reporter");
        reportermenue.addActionListener(new reportermenueListener());
        anlegen = new JMenuItem("anlegen");
        anlegen.addActionListener(new anlegenListener());
        editieren = new JMenuItem("editieren");
        editieren.addActionListener(new editierenListener());
        löschen = new JMenuItem("löschen");
        löschen.addActionListener(new löschenListener());
        systemBeenden = new JMenuItem("System beenden");
        systemBeenden.addActionListener(new beendenmenueListener());


        menuebar.add(gui);
        menuebar.add(verwalten);
        menuebar.add(exit);

        gui.add(editormenue);
        gui.add(reportermenue);
        verwalten.add(anlegen);
        verwalten.add(editieren);
        verwalten.add(löschen);
        exit.add(systemBeenden);

        frame.getContentPane().add(menuebar,BorderLayout.NORTH);
        frame.getContentPane().add(tree,BorderLayout.WEST );
        frame.getContentPane().add(editor,BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setVisible(true);

    }



   public void doMouseClicked(MouseEvent me, JTree tree) {
   TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
   String code = tp.toString();
   String regex = ",";
   Pattern p = Pattern.compile(regex);
   String[] items = p.split(code);
   if(items.length > 1) {
      if (tp != null) {
         String nodeItem = items[1];
         nodeItem = nodeItem.substring(1, nodeItem.length()-1);

         if (nodeItem.equals("Zitate")) {
             testklasse test = new testklasse();
             test.testklasse(); }

         if (nodeItem.equals("Personen")) {
             testklasse test = new testklasse();
             test.testklasse(); }

         if (nodeItem.equals("Sammelwerke")) {
             testklasse test = new testklasse();
             test.testklasse(); }

         if (nodeItem.equals("Werke")) {
             testklasse test = new testklasse();
             test.testklasse(); }

         if (nodeItem.equals("Verlage")) {
             testklasse test = new testklasse();
             test.testklasse(); }

      }
      }
      }

    public static void main(String[] args) {
        editor test1 = new editor();
        test1.editor();
    }

    class editormenueListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == editormenue){

editor editor1 = new editor();
editor1.editor(); }
}

    }
class reportermenueListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == reportermenue){

reporter reporter1 = new reporter();
reporter1.reporter(); }
}

    }

class anlegenListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == anlegen){

objektAnlegen anlegen1 = new objektAnlegen();
anlegen1.objektAnlegen(); }
}
    }

class editierenListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == editieren){

objektEditieren editieren1 = new objektEditieren();
editieren1.objektEditieren(); }
}
    }

class löschenListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == löschen){

objektLöschen löschen1 = new objektLöschen();
löschen1.objektLöschen(); }
}
 }

class beendenmenueListener implements ActionListener{

    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == systemBeenden){
             frame.setVisible(false) ;}

}
 }}


