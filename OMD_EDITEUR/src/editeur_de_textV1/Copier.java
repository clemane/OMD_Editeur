package editeur_de_textV1;
public class Copier implements Commande {

 
    public Buffer b;

    public Copier(Buffer b){
      
        this.b = b;
    }

  
    public void execute(){
        
        b.copier();
    }
}