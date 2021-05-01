package Controller;

import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Jelszó titkosításért felelős osztály
 */
public class Encrypt {
    /**
     * jelszó "bonyolító" String
     */
    private final String salt = "s7oG0n0w9A";

    public Encrypt() {}

    /**
     * Hippity-Hoppity your code is now OUR property!
     * Viccet félretéve: ez a függvény végzi a jelszavak kódolását
     * @param pass User password before encoding
     * @return encoded password
     */
    public String encryptIt(String pass) {
        if(regex(pass)) {
            System.out.println("Jó jelszó");
        } else {
            System.out.println("Rossz jelszó");
        }
        try {
            pass+=this.salt;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(pass.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("FUCK MY LIFE");
        }
        return null;
    }

    private boolean regex(String pass) {
        //A jelszónak tartalmaznia kell legalább egy kis+nagybetűt és egy számot
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.find();
    }
}

