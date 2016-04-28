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
            s = s.replaceAll("@+", "@");
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
        String parts[] = s.split("\\|");
        parts[0]=replaceName(parts[0]);
        parts[1]=replaceAge(parts[1]);
        parts[2]=replacePhoneNumber(parts[2]);
        parts[3]=replaceEmailAdress(parts[3]);

        s="";

        for (int i=0;i<parts.length;i++)
        {
            if (i!=parts.length-1)
                s=s+parts[i]+"|";
            else s=s+parts[i];
        }

        System.out.println(s);

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
