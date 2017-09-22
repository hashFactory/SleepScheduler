import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// TODO: Handling months changing
// TODO: Implement second nap
// TODO: Better UI for graph
// TODO: Handling changing years
// TODO: Pull from google drive everyday with updated graph

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
        parser.read_main_nap_times();

        parser.calc_daily_sleep();

        Visualizer v = new Visualizer(parser);
        v.paint();
        v.display();
        v.save();
    }
}
