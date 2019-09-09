package socketudpsm;
import java.net.*;
import java.io.*;
public class ClientUDP {
  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {
    int puerto = 6789;
    try {
        String dato="prueba";
        String ip="localhost";
      DatagramSocket socketUDP = new DatagramSocket();
      byte[] mensaje = dato.getBytes();
      InetAddress hostServidor = InetAddress.getByName(ip);
      // Construimos un datagrama para enviar el mensaje al servidor
      DatagramPacket peticion =
        new DatagramPacket(mensaje, dato.length(), hostServidor,
                           puerto);
      // Enviamos el datagrama
      socketUDP.send(peticion);
      // Construimos el DatagramPacket que contendrá la respuesta
      byte[] bufer = new byte[1000];
      DatagramPacket respuesta =
        new DatagramPacket(bufer, bufer.length);
      socketUDP.receive(respuesta);
      String cadena = "respuesta";
      StringBuilder builder = new StringBuilder(cadena);
      String cadena_invertida = builder.reverse().toString();
      // Enviamos la respuesta del servidor a la salida estandar
      System.out.println(cadena_invertida + new String(respuesta.getData()));
      // Cerramos el socket
      socketUDP.close();
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}