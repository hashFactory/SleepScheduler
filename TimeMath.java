public class TimeMath
{
    public static TimePoint diff(TimePoint a, TimePoint b)
    {
        int hours = b.hour + (24 * (b.day - a.day)) - a.hour;
        int minutes;
        if (a.minute > b.minute)
        {
            minutes = b.minute + 60 - a.minute;
            hours -= 1;
        }
        else
        {
            minutes = b.minute - a.minute;
            //hours -= 1;
        }

        TimePoint c = new TimePoint(hours, minutes);
        return c;
    }
}
