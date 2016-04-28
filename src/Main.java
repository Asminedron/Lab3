import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Andrew on 29.04.2016.
 */
public class Main {
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
