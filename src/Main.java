import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 29.04.2016.
 */
public class Main {
    public static String replaceName(String s)
    {
        s=s.replaceAll("([А-Я])"," $1");
        s=s.replaceAll("^\\s","");
        return s;
    }

    public static String replaceAge(String s)
    {
        s=s.replaceAll("\\s","");
        s=" "+s+" ";
        Pattern p = Pattern.compile("[\\D&&[^ ]]");
        s = s.replaceAll("-(\\d*)", " $1");
        Matcher m = p.matcher(s);
        if (m.find())
            s=" Not correct string";
        else {
            s = s.replaceAll("(\\s\\s)", " ");
        }
        return s;
    }

    public static String replacePhoneNumber(String s){
        Pattern p =Pattern.compile("[\\D&&[^ +]]");
        Matcher m = p.matcher(s);
        if (m.find())
            s=" Not correct string ";
        else {
            s = s.replaceAll(" +", "");
            s = " " + s + " ";
            s = s.replaceAll(" ([+]\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "$1 ($2) $3-$4-$5");
        }
        return s;
    }

    public static String replaceEmailAdress(String s){
        Pattern p1 = Pattern.compile("\\.ru$");
        Matcher m1 = p1.matcher(s);
        Pattern p2 = Pattern.compile("\\.com$");
        Matcher m2 = p2.matcher(s);
        if (!(m1.find())&&!(m2.find()))
            s=" Not correct string ";
        else s=s.replaceAll("@+","@");
        return s;
    }
    public static void main(String[] args){
        File output = new File("out.txt");
        File input = new File("in.txt");
        char[] buffer = new char[(int) output.length()];
        try (FileReader reader = new FileReader(output)) {
            reader.read(buffer);
            //System.out.println(new String(buffer));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String s = new String(buffer);

        buffer = s.toCharArray();
        try (FileWriter writer = new FileWriter(input))
        {
            writer.write(buffer);
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
