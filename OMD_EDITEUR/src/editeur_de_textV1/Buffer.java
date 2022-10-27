package editeur_de_textV1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Buffer {

    //position du curseur
    public int position;
    //Borne Sup de la selection
    public int selection;
    //Jlabel affichant le texte
    public JLabel zoneText;
    //Texte entré au clavier
    public List<String> saisie;
    //Sauvegarde de la selection => copier/couper
    public List<String> sauvegarde;
    //Swap entre selection et écriture
    public Boolean etat;

    //Constructeur de Buffer
    public Buffer(JLabel text) {
        //Initialisation des attributs
        zoneText = text;
        position = -1;
        selection = 0;
        saisie = new ArrayList<>();
        sauvegarde = new ArrayList<>();
        etat = false;
    }

 
    public void update() {
        if(!etat){
            selection = position;
        }
        String memoire1 = "";
        for (int i = 0; i < position; i++) {
            memoire1 = memoire1 + saisie.get(i);
        }
        String caseSelect = "";
        for(int i=position;i<=selection;i++){
            caseSelect = caseSelect + saisie.get(i);
        }

       
        String memoire2 = "";
        for(int i=selection+1;i<saisie.size();i++){
            memoire2 = memoire2 + saisie.get(i);
        }

        zoneText.setText("<html><body><font color='black'>"+memoire1+"<font color='red'>"+caseSelect+"<font color='black'>"+memoire2+"</body></html>");
    }

    /**
     * ajoute l'entrée du clavier à la position du curseur
     */
    public void add(String s,int p) {
        List<String> memoire = new ArrayList<>();

        //On ajoute tous les elements de la liste de saisie
        for(int i=0;i<saisie.size();i++){
            memoire.add(saisie.get(i));
        }

        //reinitialise la saisie a 0
        saisie = new ArrayList<>();

        //ajoute a la saisie la partie en memoire du debut a la position du curseur
        for(int i=0;i<=position;i++){
            saisie.add(memoire.get(i));
        }

        //on rajoute la nouvelle entree du clavier
        saisie.add(s);

        //ajoute le reste de la memoire
        for(int i=position+1;i<memoire.size();i++){
            saisie.add(memoire.get(i));
        }

        //incremente la position du curseur
        position++;
    }

    /**
     * Supprime le caractere a la position du curseur
     */
    public void remove() {
        //supression du caractere a la position
        saisie.remove(position);
        //On decale le curseur vers la gauche
        if (position > 0) {
            position--;
        }
    }

    /**
     * Fonction qui decale le curseur vers la droite si il ne depasse pas la taille de la saisie
     */
    public void positionDroit() {
        if(position < saisie.size()-1){
            position++;
        }
    }

    /**
     * Fonction qui decale le curseur vers la droite si il n'est pas négatif
     */
    public void positionGauche() {
        if (position > 0) {
            position--;
        }
    }

    /**
     * Fonction qui incremente la position de la selection ce qui va etendre la zone de selection
     */
    public void selectionDroit(){
        if(selection < saisie.size()-1){
            selection++;
        }
    
    }

    /**
     * On réduit la zone de selection
     * Si la selection est inférieure à la position on décrémente la position
     */
    public void selectionGauche(){
        if(selection > position){
            selection--;
        }
        else if(selection==position && position>=0){
            selection--;
            position--;
        }
    }

    /**
     * Récupération de la pos du curseur
     */
    public int getPosition(){
        return this.position;
    }

    /**
     * Passage au mode selection ou ecriture
     */
    public void setEtat(Boolean b){
        this.etat = b;
    }

    /**
     * Sauvegarde les caractères selectionner
     */
    public void copier(){
        //On initialise la liste de String a sauvegarder
        sauvegarde = new ArrayList<>();

        //On ajoute les caractères de la selection dans la liste sauvegarde
        for(int i=position;i<=selection;i++){
            sauvegarde.add(saisie.get(i));
        }
    }

    /**
     * Ecris le contenu de la Liste Sauvegarde à la pos du curseur
     */
    public void coller(){
        //On initialise la chaine d'ajout
        String colle ="";

        for(int i=0;i<sauvegarde.size();i++){
            colle = colle + sauvegarde.get(i);
        }

        //On add le contenue de sauvegarde à la position du curseur
        add(colle,position);
    }

    public void couper(){
        this.copier(); //on copie la selection
        while (position<=selection){
            saisie.remove(selection); //on remove un par un les caractères de la selection
            selection--;
        }
        position--;
    }
            
        
    

}
