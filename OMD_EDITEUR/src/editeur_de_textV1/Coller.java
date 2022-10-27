package editeur_de_textV1;
public class Coller implements Commande {

    
    public Buffer b;

    public Coller(Buffer b){
        this.b = b;
    }

    public void execute(){
        
        b.coller();
    }
}
