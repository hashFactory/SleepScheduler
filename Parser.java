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

    public TimePoint[] total_sleep;

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
        total_sleep = new TimePoint[number_of_days];
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

        String[] wakeup_string = file_contents.get(2).split(",");

        for (int i = 0; i < number_of_days - 1; i++)
        {
            String[] sleep_components = sleep_string[i+1].split(":");
            String[] wakeup_components = wakeup_string[i+1].split(":");

            int sleep_hour = Integer.parseInt(sleep_components[0]);
            int sleep_minute = Integer.parseInt(sleep_components[1]);

            int wakeup_hour = Integer.parseInt(wakeup_components[0]);
            int wakeup_minute = Integer.parseInt(wakeup_components[1]);

            fall_asleep_times[i] = new TimePoint(dates[i].year, dates[i].month, (sleep_hour >= 0 && sleep_hour <= 12) ? dates[i].day : dates[i].day - 1, sleep_hour, sleep_minute);
            wakeup_times[i] = new TimePoint(dates[i].year, dates[i].month, (wakeup_hour >= 0 && wakeup_hour <= 18) ? dates[i].day : dates[i].day - 1, wakeup_hour, wakeup_minute);
            System.out.println("Data point " + (i) + "\t" + fall_asleep_times[i].day + "; " + fall_asleep_times[i].hour + ":" + fall_asleep_times[i].minute);
            System.out.println("Data point " + (i) + "\t" + wakeup_times[i].day + "; " + wakeup_times[i].hour + ":" + wakeup_times[i].minute);
        }
    }

    public void calc_daily_sleep()
    {
        for (int i = 0; i < number_of_days - 1; i++)
        {
            TimePoint c = TimeMath.diff(fall_asleep_times[i], wakeup_times[i]);
            total_sleep[i] = c;
        }
    }
}
