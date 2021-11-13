package diccionariocastellanoingles;
import java.io.*;

/**
 * @author Daniel Santamaría Tavera
 */
public class Diccionario {
    class Termino{
        private String castellano;
        private String ingles;
        private Termino siguiente;

        public Termino() {
        }

        public String getCastellano() {
            return castellano;
        }

        public void setCastellano(String castellano) {
            this.castellano = castellano;
        }

        public String getIngles() {
            return ingles;
        }

        public void setIngles(String ingles) {
            this.ingles = ingles;
        }

        public Termino getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Termino siguiente) {
            this.siguiente = siguiente;
        }
    }

    private Termino raiz;
    
    public Diccionario() {
        this.raiz = null;
    }
    
    public void registrarTermino(String cast, String ing, boolean estaEnElArchivo){
        Termino nuevo = new Termino();
        nuevo.setCastellano(cast);
        nuevo.setIngles(ing);
        if (this.raiz==null){
            nuevo.setSiguiente(null);
        }else{
            nuevo.setSiguiente(raiz);
        }
        this.raiz = nuevo;
        if (estaEnElArchivo==false){
            this.almacenarEnArchivo();
        }
    }
    
    public void consultarTermino(String palabra){
        boolean encontrado = false;
        if (this.raiz!=null){
            Termino buscador = this.raiz;
            while (buscador!=null && encontrado==false ){
                if (palabra.equals(buscador.getCastellano())){
                    System.out.println(palabra+" = "+buscador.getIngles());
                    encontrado = true;
                }
                buscador = buscador.getSiguiente();
            }
            if (encontrado==false) System.out.println("El término digitado no está en el diccionario");
        } else {
            System.out.println("El diccionario está vacío");
        }
    }
    
    public void consultarTodo(){
        if (this.raiz!=null){
            Termino recorriendo = this.raiz;
            while(recorriendo!=null){
                System.out.println(recorriendo.getCastellano()+" = "+recorriendo.getIngles());
                recorriendo = recorriendo.getSiguiente();
            }
        } else {
            System.out.println("El diccionario está vacío");
        }
    }
    
    public void almacenarEnArchivo(){
        File archivo;
        FileWriter escritor;
        BufferedWriter bufer;
        String nombreArchivo = "Diccionario.txt";
        if (this.raiz!=null){
            try{
                archivo = new File(nombreArchivo);
                escritor = new FileWriter(archivo);
                bufer = new BufferedWriter(escritor);
                Termino recorriendo = this.raiz;
                while(recorriendo!=null){
                    if (recorriendo==this.raiz){
                        escritor.write(recorriendo.getCastellano());
                        escritor.append("\n"+recorriendo.getIngles());
                    } else {
                        escritor.append("\n"+recorriendo.getCastellano());
                        escritor.append("\n"+recorriendo.getIngles());
                    }
                    recorriendo = recorriendo.getSiguiente();
                } 
                escritor.close();
                bufer.close();
                System.out.println("Se ha actualizado el archivo persistente txt");
            } catch (IOException e){
                System.out.println("Error al almacenar en archivo: "+e.getMessage());
            }
        } 
    }
    
    public void leerArchivo(){
         String nombreArchivo = "Diccionario.txt";
          try{
            FileReader r = new FileReader(nombreArchivo);
            BufferedReader buffer = new BufferedReader(r);
            String castellano = "";
            String ingles = "";
            String temp = buffer.readLine();
            while (temp!=null){
                castellano = temp;
                temp = buffer.readLine();
                ingles = temp;
                this.registrarTermino(castellano, ingles, true);
                temp = buffer.readLine();
            } 
        }catch(IOException e){
            System.out.println("Error al leer archivo persistente");
            System.out.println(e.getMessage());
        }
    }
}
