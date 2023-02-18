import java.util.*;
import java.io.*;


public class PasswordCrack{

    public static ArrayList<String> userHashes = new ArrayList();
    public static ArrayList<String> names = new ArrayList();
    public static ArrayList<String> salts = new ArrayList();
    
    public static ArrayList<String> fetchUsers(String passwordFile){
      ArrayList<String> lines = new ArrayList();
        try{
            
            FileReader fis = new FileReader(passwordFile);
            BufferedReader br = new BufferedReader(fis);
            String line;
          

            while((line = br.readLine()) != null){
             
               lines.add(line);
             
            }

            br.close();
            fis.close();

            return lines;
          
        }
        catch(Exception e){
            System.out.println("passwordfile does not exist");
            System.exit(1);
        }
        return lines;
    }

    public static ArrayList<String> getEncrypted(ArrayList<String> lines){ //plockar ut hashade passwords, ej salts
        
        ArrayList passHash = new ArrayList();

        for(int i = 0; i<lines.size(); i++){
            String line = lines.get(i);
            String[] splitted = line.split(":");
            String hash = splitted[1];
            passHash.add(hash);
        }
       
        return passHash;        

    }

    public static ArrayList<String> getSalts(ArrayList<String> lines){
        ArrayList<String> salts =  new ArrayList();

        for(int i = 0; i<lines.size(); i++){
            String line = lines.get(i);
            String[] splitted = line.split(":");
            String hash = splitted[1];
            String salt = hash.substring(0,2);
            salts.add(salt);

        }
       
        return salts;   

    }
    
    public static String prepend(String guess, int i){

        int j = i-14;
        String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String modGuess = string.charAt(j) + guess;
        
        return modGuess;
    }
    public static String append(String guess, int i){
        
        int j = i-76;
        String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String modGuess = guess + string.charAt(j);
        return modGuess;
    }

    public static ArrayList<String> createEasyDictionary(){ //taken from slides and wikipedia
        ArrayList<String> guesses = new ArrayList();

        guesses.add("123456");
        guesses.add("password");
        guesses.add("12345678");
        guesses.add("qwerty");
        guesses.add("12345");
        guesses.add("football");
        guesses.add("1234");
        guesses.add("1234567");
        guesses.add("baseball");
        guesses.add("welcome");
        guesses.add("abc123");
        guesses.add("111111");
        guesses.add("1qaz2wsx");
        guesses.add("dragon");
        guesses.add("master");
        guesses.add("letmein");
        guesses.add("login");
        guesses.add("princess");
        guesses.add("qwertyuiop");
        guesses.add("solo");
        guesses.add("passw0rd");
        guesses.add("starwars");
        guesses.add("picture1");
        guesses.add("senha");   
        guesses.add("000000");    
        guesses.add("Million2"); 
        guesses.add("password1");
        guesses.add("admin");




        return guesses;
    }

    public static String mangleID(int id, String guess){
            if (id == 0){  return guess; }   
            else if(id==1){ return deleteStringFirst(guess); } 
            else if(id==2){ return deleteStringLast(guess); } 
            else if(id==3){ return deleteStringFirst(guess); } 
            else if(id==4){ return reverse(guess); } 
            else if(id==5){ return duplicate(guess); } 
            else if(id==6){ return reflect(guess); } 
            else if(id==7){ return uppercase(guess); } 
            else if(id==8){ return lowercase(guess); } 
            else if(id==9){ return capitalize(guess); } 
            else if(id==10){ return ncapitalize(guess); } 
            else if(id==11){ return togglecase(guess); } 
            else if(id==12){ return togglecase2(guess); } 
            else if(id==13){ return reverse2(guess); } 
            else if(id >=14 && id <= (75)){ return prepend(guess, id); }  
            else if(id >=76 && id <= 137){ return append(guess, id); } 
            else if(id==139){return reflect2(guess); }
     
        

        return "faultyMangleID"; 
    }

    public static String deleteStringFirst(String guess){
        String modGuess = guess.substring(1);
        return modGuess;
    }

    public static String deleteStringLast(String guess){
        String modGuess = guess.substring(0, guess.length()-1);
        return modGuess;
    }

    public static String reverse(String guess){
        StringBuilder sb = new StringBuilder();
        sb.append(guess);
        sb.reverse();
        String modguess = sb.toString();
        return modguess;
    }
    public static String duplicate(String guess){
        String modguess = guess + guess;
        return modguess;
    }
    public static String reflect(String guess){
        StringBuilder sb = new StringBuilder();
        sb.append(guess);
        sb.reverse();
        sb.append(guess);

        String modguess = sb.toString();
        return modguess;
    } 
    
    public static String uppercase(String guess){
     
        return guess.toUpperCase();
        
    }
    public static String lowercase(String guess){
        return guess.toLowerCase();
        
    }
    public static String capitalize(String guess){
        String modGuess = guess.substring(0,1).toUpperCase() + guess.substring(1).toLowerCase();
        return modGuess;
        
    }
    public static String ncapitalize(String guess){
        String modGuess = guess.substring(0,1).toLowerCase() + guess.substring(1).toUpperCase();
        return modGuess;
    }
    public static String togglecase(String guess){
        StringBuilder sb = new StringBuilder();
     
        for(int i = 0; i<guess.length(); i++){
            if(i%2 == 0){
                sb.append(guess.toUpperCase().charAt(i));
            }
            else{
                sb.append(guess.charAt(i));
            }
        }

        return sb.toString();
    }
    public static String togglecase2(String guess){
        StringBuilder sb = new StringBuilder();
     
        for(int i = 0; i<guess.length(); i++){
            if(i%2 != 0){
                sb.append(guess.toUpperCase().charAt(i));
            }
            else{
                sb.append(guess.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String reverse2(String guess){
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        sb.append(guess);
        sb2.append(guess);
        sb2.reverse();

        String reversed = sb2.toString();
        String modguess = sb.toString();

        return modguess + reversed;
    
    }
    public static String reflect2(String guess){
        StringBuilder sb = new StringBuilder();
        sb.append(guess);
        sb.reverse();
        String modguess = sb.toString() + guess;

        return modguess;
    }
    public static void usernameCrack(ArrayList<String> lines){

        String[] names = new String[lines.size()];


        for(int i = 0; i < lines.size(); i++){ //get all names in an array
            String line = lines.get(i);
            String [] splitted = line.split(":");
            String name = splitted[4];
            names[i]=name;
        }   

    yttre:  for(int j = 0; j<names.length; j++){ //för alla namn
            String[] namesplit = names[j].split(" "); //splitta upp efternamn och förnamn
            
            for(int k = 0; k<namesplit.length; k++){ //för varje subnamn
                for(int id = 0; id < 139; id++){ 
                    String mod1 = mangleID(id, namesplit[k]); 
                    for(int id2 = 0; id2 < 139; id2++){
                        String mod = mangleID(id2, mod1);
                        for(int x = 0; x < userHashes.size();x++){ //för varje user             
                            String jcrypted = jcrypt.crypt(salts.get(j), mod); 
                            if(jcrypted.equals(userHashes.get(j))){
                                System.out.println(mod);
                                continue yttre;
                            }
                        }
                    }
                }
            }
        }


    
    }

    public static void runEasyDic(ArrayList<String> dictionary, ArrayList<String> lines){
        for(int i = 0; i < lines.size(); i++){ //get all names in an array
            String line = lines.get(i);
            String [] splitted = line.split(":");
            String name = splitted[4];
            names.add(name);
        }   

   
    yttre:  for(int j = 0; j < dictionary.size(); j++){ //för varje ord
            String word = dictionary.get(j); //ta ut odet
            for(int id = 0; id < 139; id++){ //gör varje mangle
                String mod = mangleID(id, word); 
                for(int x = 0; x < userHashes.size(); x++){ //för varje mangle, kolla om det funkar på varje person
                    String jcrypted = jcrypt.crypt(salts.get(x), mod);
                    // System.out.println(userHashes.get(x)+ " " + jcrypted + " " + names.get(x));
                   
                    if(jcrypted.equals(userHashes.get(x))){
                        System.out.println(mod);
                        // System.out.println(names[x]);
                        userHashes.remove(x);
                        salts.remove(x);
                        names.remove(x);
                        x--; //rätta till indexet efter att du tagit bort ett index så att vi inte skippar
                       
                        continue yttre;
                    }
                }

                
            }

        }

    }

    public static void bruteForce(String dictionary, ArrayList<String> lines){

        try{
            FileReader fis = new FileReader(dictionary);
            BufferedReader br = new BufferedReader(fis);
            String word;
            int counter = 0;

yttre:      while((word = br.readLine()) != null){ //för varje ord
                for(int id = 0; id<139; id++){
                    String mod = mangleID(id, word);
                    for(int x = 0; x < userHashes.size(); x++){
                        String jcrypted = jcrypt.crypt(salts.get(x), mod);
                        if(jcrypted.equals(userHashes.get(x))){
                            System.out.println(mod);
                            userHashes.remove(x);
                            salts.remove(x);
                            names.remove(x);
                            x--;
                            continue yttre;
                        }
                    }
                }
                

            }
        br.close();
        fis.close();



        }

        catch(Exception e){
            System.out.println("dictionaryfile can't be found");
            System.exit(1);
        }

    }

    public static void main(String args[] ){

        if(args.length != 2){
            System.out.println("too few or too many arguments, format is: java 'dictionary' 'passfile'");
            System.exit(1);
        }

        String dictionary = args[0];
        String passwordFile = args[1];

        

 
        ArrayList<String> lines = fetchUsers(passwordFile);
        ArrayList<String> easyDictionary = createEasyDictionary();
        userHashes = getEncrypted(lines);
        salts = getSalts(lines);
       
        
        usernameCrack(lines);
        runEasyDic(easyDictionary, lines);       
        bruteForce(dictionary, lines);
        
    

    }
    
}