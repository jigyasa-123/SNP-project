import java.net.*;
import musicfiles.FileEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class GreetingServer extends Thread
{ private static ObjectInputStream inputStream = null;
	
    private FileEvent fileEvent;
   private File dstFile = null;
	
    private static FileOutputStream fileOutputStream = null;
   private ServerSocket serverSocket;
   int i,k=0;
	static ArrayList ar = new ArrayList();

   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
     // serverSocket.setSoTimeout(1000000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
        	 

            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            //DataOutputStream out =
              //   new DataOutputStream(server.getOutputStream());
          //while(i.hasNext()){
       		 
       	// System.out.println(i.next());
          //  out.writeUTF((String) i.next());
            //out.flush();
           // out.writeUTF(i.next()+server.getLocalSocketAddress() + "\nGoodbye!");
            //}
            ObjectInputStream objectinput = new ObjectInputStream(server.getInputStream());
            Object object = objectinput.readObject();
            ar = (ArrayList)object;
            for(int i=0;i<ar.size();i++){
                
            System.out.println("index :"+i+","+ar.get(i));
            }
             System.out.println("Enter the index of the song to be selected");
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             String num = br.readLine();
             DataOutputStream dout = new DataOutputStream(server.getOutputStream());
             dout.writeInt(Integer.parseInt(num));
           //  downloadFile();
            server.close();
            Server s = new Server();
            s.doConnect();
         s.downloadFile();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }
   public static void main(String [] args)
   {
      int port = 4095;
      try
      {
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
  /* public void downloadFile() {
	
        try {
	
            fileEvent = (FileEvent) inputStream.readObject();
	
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
	
                System.out.println("Error occurred ..So exiting");
	
                System.exit(0);
	
            }
	
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
	
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
	
                new File(fileEvent.getDestinationDirectory()).mkdirs();
	
            }
	
            dstFile = new File(outputFile);
	
            fileOutputStream = new FileOutputStream(dstFile);
	
            fileOutputStream.write(fileEvent.getFileData());
	
            fileOutputStream.flush();
	
            fileOutputStream.close();
	
            System.out.println("Output file : " + outputFile + " is successfully saved ");
	
            Thread.sleep(3000);
	
            System.exit(0);
	
 
	
        } catch (IOException e) {
	
            e.printStackTrace();
	
        } catch (ClassNotFoundException e) {
	
            e.printStackTrace();
	
        } catch (InterruptedException e) {
	
            e.printStackTrace();
	
        }
}}*/
}