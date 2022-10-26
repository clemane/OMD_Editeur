package editeur_de_text;
public class Copier implements Commande {

    //Attribut
    public Buffer b;

    public Copier(Buffer b){
        //On recupere le Buffer courant
        this.b = b;
    }

    /**
     * Fonction qui va executer la methode copier du buffer courant
     */
    public void execute(){
        //appel de la methode copier
        b.copier();
    }
}