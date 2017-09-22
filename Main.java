import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
        Path filename = Paths.get("C:/Users/tristan/Downloads/sleep_schedule.csv");
        Scanner file = null;
        try {
            file = new Scanner(filename);
        } catch (IOException e) {
            System.exit(12);
        }

        Parser parser = new Parser(file);
        parser.read_dates();
        parser.read_main_sleep_times();


    }
}
