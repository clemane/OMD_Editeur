package editeur_de_textV1;
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
  
      //Etat Selection ou Ecriture
      public Boolean selection;
  
      //Commandes
      public Copier copier;
      public Coller coller;
      public Couper couper;
  
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
  
          //Commence en écriture
          selection = false;
  
          //Creation de la barre de menu
          JMenuBar mb = new JMenuBar();
  
          JMenu menu = new JMenu("Editeur de texte");
          //Creation des boutons du menu
          JMenuItem copy = new JMenuItem("copy = CTRL");
          JMenuItem paste = new JMenuItem("paste = ENTREE");
          JMenuItem cut = new JMenuItem("cut = ALT");
  
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
          couper = new Couper(buffer);
  
          
          mb.add(menu);
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
       * Permet de récuperer chaque touche appuyé par l'utilisateur
       */
      public void keyPressed(KeyEvent arg0) {
          //supprime un  caractère lors de l'appui de BACK_SPACE
          if (arg0.getKeyCode() == 8) {
              buffer.remove();
          }
          //Active desactive selection ou ecriture avec MAJ
          else if (arg0.getKeyCode() == 16) {
              if (selection) {
                  selection = false;
                  buffer.setEtat(false);
                  System.out.println("Selection désactivée");
              } else {
                  selection = true;
                  buffer.setEtat(true);
                  System.out.println("Selection activée");
              }
          }
          //Si Selection alors on étend la sélection sinon on bouge le curseur
          else if (arg0.getKeyCode() == 39) {
              if (selection) {
                  buffer.selectionDroit();
              } else {
                  buffer.positionDroit();
              }
          }
          //Si Selection on réduit la sélection sinon on bouge le curseur
          else if (arg0.getKeyCode() == 37) {
              if (selection) {
                  buffer.selectionGauche();
              } else {
                  buffer.positionGauche();
              }
          }
          //CTRL -- Copie
          else if(arg0.getKeyCode() == 17){
              copier.execute();
          }
          //Appuie sur Entree -- Coller
          else if(arg0.getKeyCode() == 10){
              coller.execute();
          }
          //Appuie sur ALT -- Couper
          else if(arg0.getKeyCode() == 18){
                couper.execute();
          }
          //On ajoute la saisie dans le buffer
          else {
              buffer.add(String.valueOf(arg0.getKeyChar()),buffer.getPosition());
          }
          //On update le buffer
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
