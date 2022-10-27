package editeur_de_textV1;

public class Couper implements Commande{
       
        public Buffer b;

        public Couper(Buffer b){
          
            this.b = b;
        }
    
      
        public void execute(){
           
            b.couper();
        }
}
