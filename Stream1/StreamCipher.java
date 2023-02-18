import java.io.*;
import java.util.*;
import java.lang.*;

class StreamCipher {

    public static void main (String[] args){

        if(args.length != 3){
            System.out.println("too few arguments");
            System.exit(1);
        }

        String key = args[0];
        String infile = args[1];
        String outfile = args[2];

        long keyStream = 0;

        try{
            keyStream = Long.parseLong(key);
        }
        catch(Exception e){
            System.out.println("Key must be a number");
            System.exit(1);
        }
        Random random = new Random(keyStream);

        FileInputStream fis = null;
        BufferedInputStream reader = null;
        FileOutputStream fos = null;
        BufferedOutputStream writer = null;

        try{
            fis = new FileInputStream(infile);    
            reader = new BufferedInputStream(fis);
        }
        catch(Exception E){
            System.out.println("Infile does not exist");
            System.exit(1);
        }

        try{
            fos = new FileOutputStream(outfile);
            writer = new BufferedOutputStream(fos);
        }
        catch(Exception E){
            System.out.println("The outfile is either a directory, does not exist but cannot be created or cannot be opened for admin reasons");
            System.exit(1);
        }
        try{
            int i;
            while((i=reader.read())!=-1){    // taget från https://www.javatpoint.com/java-bufferedinputstream-class,
                // System.out.println(i);
                int rng = random.nextInt(256);
                // System.out.println("randomnumber: " + rng);
                int xored = i^rng;                
                writer.write(xored);
                // System.out.println(xored);
            }  
 
        }
        catch(Exception E){
            System.out.println("Couldn't read from the infile for some reason. Might be due to corrupt data in file.");
            System.exit(1);
        }

        try{
            reader.close();
            writer.close();
            fis.close();
            fos.close();
        }
        catch(Exception e){
            System.out.println("Couldnt close");
            System.exit(1);
        }

    }    
}
