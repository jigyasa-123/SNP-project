package musicfiles;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
public class Music {
File x;
ArrayList al = new ArrayList();

public void extract(String p){
	String s=null;
File f=new File(p);
File l[]=f.listFiles();
for(File x:l)
{
    if(x==null){//return "Failed";
    	
    }
    if(x.isHidden()||!x.canRead())
        continue;
    if(x.isDirectory())
        extract(x.getPath());
    else if(x.getName().endsWith(".mp3"))
      //  System.out.println(x.getPath()+"\\"+x.getName());
    al.add(x.getPath());

}
}}


