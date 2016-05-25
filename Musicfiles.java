
package musicfiles;
import musicfiles.FileEvent;

import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
public class Musicfiles {
   private static FileEvent fileEvent=null;
   private static ObjectOutputStream outputStream = null;
    private boolean isConnected = false;
   // private String sourceFilePath = "D:/temp/songs/song1.mp3";
     private static String sourceFilePath = "/Users/jigs/Desktop/";
     private static String add;
    
    //private String destinationPath = "C:/tmp/downloads/";
    private static String destinationPath = "/Users/jigs/Desktop";
    static int l;
   static String serverName = "";
Musicfiles(String peer){
    serverName = peer;
    System.out.println("heya"+serverName);
    //serverName = "Localhost";
    fun();
    
}
   
    public static void fun() {
      // String serverName = "172.16.86.132";
      int port = 5085;
      try
      {
    	  Music m = new Music();
     	 m.extract("/Users/jigs/Desktop");
        	Iterator i =m.al.iterator();
         System.out.println("Connecting to " + serverName +
		 " on port " + port);
         Socket client = new Socket(serverName, port);
         System.out.println("Just connected to " 
		 + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF("Hello from "
                      + client.getLocalSocketAddress());
       // InputStream inFromServer = client.getInputStream();
        // DataInputStream in =
       //                 new DataInputStream(inFromServer);
        // System.out.println("Server");
        //System.out.println("Server says " + in.readUTF());
         //while(in!=null){
        	// System.out.println("hello");
         //}
        
         ObjectOutputStream objectoutput = new ObjectOutputStream(client.getOutputStream());
         objectoutput.writeObject(m.al);
          System.out.println("waiting for server to select a song");
          try{
          DataInputStream din = new DataInputStream(client.getInputStream());
           l = din.readInt();
          }catch(SocketTimeoutException f){
              f.printStackTrace();
              Socket client1 = new Socket(serverName, port);
         System.out.println("Just connected to " 
		 + client.getRemoteSocketAddress());
           DataOutputStream dout = new DataOutputStream(client1.getOutputStream());
           dout.writeUTF("enter index again");
           DataInputStream din1= new DataInputStream(client1.getInputStream());
           l=din1.readInt();
           
          }
          System.out.println("selected song is of index:"+l);
           add = (String)m.al.get(l);
          System.out.println("selected song is  "+add);
                   client.close();

          
        // sendFile();
          Client c1 = new Client(add,serverName);
          
          c1.connect();
          c1.sendFile();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   
    }
   /*  public static void sendFile() throws FileNotFoundException, IOException {
         System.out.println("hello ayushi");
        fileEvent = new FileEvent();
        String fileName = add.substring(add.lastIndexOf("/")+1 , add.length());
        String path = add.substring(0, add.lastIndexOf("/")+1);
        fileEvent.setDestinationDirectory(destinationPath);
        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(add);
         System.out.println(fileName);
                  System.out.println(add);

        File file = new File(add);
        System.out.println("sdf"+file);
        /*if(file.canRead()){
            System.out.println("file can be read");
           //FilePermission permission = new FilePermission(file, FilePermission.READ);
           // FilePermission permission = new FilePermission("C:/Users/SONY/Desktop/Maid with the Flaxen Hair.mp3", "READ");
          //  FilePermission permission = new FilePermission("C:/Users/SONY/Desktop/Maid with the Flaxen Hair.mp3", "WRITE");
        }
        else
        {
           FilePermission permission = new FilePermission(add, "read");
            System.out.println("File can't b read");
        }*/
    /*   if (file.isFile()) {
            try {
                    System.out.println("isfile");
                
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                

                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read,
                        fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                                                 System.out.println("igfile");

                }
System.out.println(len);
System.out.println(fileBytes);
                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
                System.out.println(fileEvent);
            } catch (Exception e) {
                printf("SDFrg");
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {
           System.out.println("path specified is not pointing to a file");
            fileEvent.setStatus("Error");
        }
        //Now writing the FileEvent object to socket
        try {
                                                                System.out.println("isfdfgfile");

            outputStream.writeObject(fileEvent);
            System.out.println("Done...Going to exit");
            Thread.sleep(3000);
            System.exit(0);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/
    
}
