public class BitCount{
    
    public static void main (String args[]){

        String bit1 = args[0];
        String bit2 = args[1];
        int counter = 0;

            for (int i = 0; i < bit1.length(); i++) {


                if(bit1.charAt(i) == bit2.charAt(i))

                counter++;            
            }   

        System.out.println(counter);
    }
        
}