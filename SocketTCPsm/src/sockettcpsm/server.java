package sockettcpsm;
import java.io.*;
import java.net.*;
public class server {    
    public static void main(String[] args) throws InterruptedException{
        int port =5001; // puerto en el que escuchara el socket        
        try {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;      
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while(true){   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion 
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
               
                StringBuilder builder = new StringBuilder(cadena);
                String cadena_invertida = builder.reverse().toString();
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                System.out.println(cadena_invertida+client.getInetAddress());               
                toClient.flush();
                toClient.println("hola"+cadena_invertida);               
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}