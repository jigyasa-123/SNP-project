import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
        	 Music m = new Music();
        	 Ainvayi a = new Ainvayi();
        	 ArrayList<String> arr = new ArrayList<String>();
        	 arr = m.extract("/Users/jigs/Desktop");
        	 Iterator i =arr.iterator();
        	
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            while(i.hasNext()){
       		 
       	 
            	out.writeUTF((String) i.next());
           // out.writeUTF(i.next()+server.getLocalSocketAddress() + "\nGoodbye!");
            }
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}