package editeur_de_text;
public class Coller implements Commande {

    //Attribut
    public Buffer b;

    public Coller(Buffer b){
        //On recupere le Buffer courant
        this.b = b;
    }

    /**
     * Fonction qui va appeler la methode coller du Buffer courant
     */
    public void execute(){
        //appel de la methode coller
        b.coller();
    }
}
