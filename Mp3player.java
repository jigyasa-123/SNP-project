

import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class Mp3player {

    
    public Mp3player(String s) throws FileNotFoundException {
        File file = new File(s);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new  BufferedInputStream(fis);
        try{
        Player player = new Player(bis);
        player.play();
        
        }catch(JavaLayerException e){}
    }
    
}
