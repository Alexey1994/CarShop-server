package Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Coder {

    private static MessageDigest md = getMessageDigest();


    private static MessageDigest getMessageDigest(){
        try{
            return MessageDigest.getInstance("SHA-1");
        } catch(NoSuchAlgorithmException e){
            return null;
        }
    }


    public static String encode(String string) {
        return new String(md.digest(string.getBytes()));
    }


    public static String getUniqueID(){
        long randomNumber = (long)(Math.random() * 1000000000.0);
        return encode(String.valueOf(randomNumber));
    }
}
