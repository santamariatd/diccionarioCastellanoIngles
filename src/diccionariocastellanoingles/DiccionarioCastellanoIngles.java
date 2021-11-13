package diccionariocastellanoingles;
import java.util.Scanner;
/**
 * @author Daniel Santamaría Tavera
 */
public class DiccionarioCastellanoIngles {

    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();
        diccionario.leerArchivo();
        Scanner teclado = new Scanner(System.in);
        boolean ejecutar = true;
        do{
            System.out.println("DICCIONARIO ESPAÑOL-INGLÉS");
            System.out.println("Seleccione:");
            System.out.println("0.Para abandonar el programa");
            System.out.println("1.Para registrar una palabra en español y su respectiva traducción en inglés");
            System.out.println("2.Para consultar el diccionario en su totalidad");
            System.out.println("3.Para consultar un término en español y obtener su traducción");
            System.out.print("Opción: ");
            int opcion = teclado.nextInt();
            switch(opcion){
                case 0: 
                    ejecutar = false;
                    break;
                case 1:
                    System.out.print("Digite la palabra en español: ");
                    String cast = teclado.next();
                    System.out.print("Digite la palabra en inglés: ");
                    String ing = teclado.next();
                    diccionario.registrarTermino(cast.toUpperCase()+" [ESPAÑOL]", ing.toUpperCase()+ " [INGLES]",false);
                    break;
                case 2: 
                    diccionario.consultarTodo();
                    break;
                case 3: 
                    System.out.print("Digite la palabra en español: ");
                    String buscar = teclado.next();
                    diccionario.consultarTermino(buscar.toUpperCase()+" [ESPAÑOL]");
                    break;
                default:
                    System.out.println("Opción no permitida");
                    break;
            }
        } while (ejecutar==true);
    }
}
