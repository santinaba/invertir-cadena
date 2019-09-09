package socketudpsm;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
public class ServerUDP {
  public static void main (String args[]) { 
    int port=6789;  
    try {
      DatagramSocket socketUDP = new DatagramSocket(port);
      byte[] bufer = new byte[1000];
      while (true) {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);
        System.out.print("Datagrama recibido del host: " +
                           peticion.getAddress());
        System.out.println(" desde el puerto remoto: " +
                           peticion.getPort());
        // Construimos el DatagramPacket para enviar la respuesta
        DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());
        // Enviamos la respuesta, que es un eco
        socketUDP.send(respuesta);
      }
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}