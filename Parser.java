import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Parser
{
    public Scanner file;
    public ArrayList<String> file_contents;
    public int number_of_days;
    public TimePoint[] dates;
    public TimePoint[] fall_asleep_times;
    public TimePoint[] wakeup_times;

    public Parser(Scanner _file)
    {
        file = _file;
        file_contents = new ArrayList<>();
        init_file();
    }

    private void init_file()
    {
        while (file.hasNextLine())
            file_contents.add(file.nextLine());

        number_of_days = file_contents.get(0).split(",").length;
        dates = new TimePoint[number_of_days];
        fall_asleep_times = new TimePoint[number_of_days];
        wakeup_times = new TimePoint[number_of_days];
    }

    public void read_dates()
    {
        String[] dates_line = file_contents.get(0).split(",");
        // dates_line[0] is trash, contains descriptor

        for (int i = 0; i < number_of_days - 1; i++)
        {
            String[] date_cell = dates_line[i+1].split("-");

            int[] date = new int[3];
            for (int j = 0; j < 3; j++)
                date[j] = Integer.parseInt(date_cell[j]);
            dates[i] = new TimePoint(date[0], date[1], date[2]);
            System.out.println(dates[i].day);
        }
    }

    public void read_main_sleep_times()
    {
        String[] sleep_string = file_contents.get(1).split(",");
        TimePoint[] fall_asleep_times = new TimePoint[number_of_days];

        for (int i = 0; i < number_of_days - 1; i++)
        {
            String[] components = sleep_string[i+1].split(":");
            int hour = Integer.parseInt(components[0]);
            int minute = Integer.parseInt(components[1]);

            fall_asleep_times[i] = new TimePoint(dates[i].year, dates[i].month, (hour >= 0 && hour <= 12) ? dates[i].day : dates[i].day - 1, hour, minute);
            // System.out.println("Data point " + (i) + "\t" + fall_asleep_times[i].day + "; " + hour + ":" + minute);
        }

        String[] wakeup_string = file_contents.get(2).split(",");
        TimePoint[] wakeup_times = new TimePoint[number_of_days];

        for (int i = 0; i < number_of_days - 1; i++)
        {
            String[] components = wakeup_string[i+1].split(":");
            int hour = Integer.parseInt(components[0]);
            int minute = Integer.parseInt(components[1]);

            wakeup_times[i] = new TimePoint(dates[i].year, dates[i].month, (hour >= 0 && hour <= 12) ? dates[i].day : dates[i].day - 1, hour, minute);
            // System.out.println("Data point " + (i) + "\t" + wakeup_times[i].day + "; " + hour + ":" + minute);
        }
    }
}
