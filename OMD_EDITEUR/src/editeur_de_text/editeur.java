package editeur_de_text;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class editeur extends JFrame implements ActionListener, KeyListener {
      //Attributs
      public JFrame fenetre;
      public Buffer buffer;
  
      public JLabel text;
  
      //Booleen pour savoir si on est en mode selection ou non
      public Boolean selection;
  
      //Commandes
      public Copier copier;
      public Coller coller;
  
      public editeur() {
          //Initialisation de la fenetre
          fenetre = new JFrame();
          fenetre.addKeyListener(this);
  
          //Initialisation du Label
          text = new JLabel();
          text.setVerticalAlignment(JLabel.TOP);
          text.setHorizontalAlignment(JLabel.LEFT);
  
          //Initialisation du Buffer
          buffer = new Buffer(text);
  
          //a la base on est pas en mode selection
          selection = false;
  
          //Creation de la barre de menu
          JMenuBar mb = new JMenuBar();
  
          //Creaton du menu
          JMenu menu = new JMenu("Editeur de texte");
  
          //Creation des boutons du menu
          JMenuItem copy = new JMenuItem("copy = CTRL");
          JMenuItem paste = new JMenuItem("paste = ENTREE");
          JMenuItem cut = new JMenuItem("cut = ");
  
          //Ajout des boutons au menu
          menu.add(copy);
          menu.add(paste);
          menu.add(cut);
  
          copy.addActionListener(this);
          paste.addActionListener(this);
          cut.addActionListener(this);
  
          //Creation des commandes
          copier = new Copier(buffer);
          coller = new Coller(buffer);
  
          //Ajout du menu a la barre de menu
          mb.add(menu);
  
          //Ajout de la barre de menu a la fenetre
          fenetre.setJMenuBar(mb);
  
          //Initialisation de la fenetre
          fenetre.add(text);
          fenetre.setSize(600, 600);
          fenetre.setLocationRelativeTo(null);
          fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          fenetre.setVisible(true);
      }
  
      @Override
      /**
       * Fonction qui entre en jeu si une touche du clavier est appuyee
       */
      public void keyPressed(KeyEvent arg0) {
          //applique la fonction retirer un caractere si on appuie sur la touche BACK_SPACE
          if (arg0.getKeyCode() == 8) {
              buffer.remove();
          }
          //On active ou desactive le mode de selction si on appuie sur la touche MAJ
          else if (arg0.getKeyCode() == 16) {
              if (selection) {
                  selection = false;
                  //On change aussi l'etat du buffer
                  buffer.setEtat(false);
                  System.out.println("Selection désactivée");
              } else {
                  selection = true;
                  //On change aussi l'etat du buffer
                  buffer.setEtat(true);
                  System.out.println("Selection activée");
              }
          }
          //si on appuie sur la fleche de droite et qu'on est en mode selection on etend la selection, sinon on deplace le curseur 
          else if (arg0.getKeyCode() == 39) {
              if (selection) {
                  buffer.selectionDroit();
              } else {
                  buffer.positionDroit();
              }
          }
          //fleche de gauche et qu'on est en mode s 
          else if (arg0.getKeyCode() == 37) {
              if (selection) {
                  buffer.selectionGauche();
              } else {
                  buffer.positionGauche();
              }
          }
          //CTRL
          else if(arg0.getKeyCode() == 17){
              copier.execute();
          }
          //Apuie sur Entree
          else if(arg0.getKeyCode() == 10){
              coller.execute();
          }
          //On ajoute la saisie dans le buffer
          else {
              buffer.add(String.valueOf(arg0.getKeyChar()),buffer.getPosition());
          }
          //On update la mise a jour du buffer
          buffer.update();
  
      }
  
      @Override
      public void keyReleased(KeyEvent arg0) {
          // TODO Auto-generated method stub
  
      }
  
      @Override
      public void keyTyped(KeyEvent arg0) {
          // TODO Auto-generated method stub
  
      }
  
      @Override
      public void actionPerformed(ActionEvent arg0) {
          // TODO Auto-generated method stub
      }


}
