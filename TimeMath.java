public class TimeMath
{
    public static TimePoint diff(TimePoint a, TimePoint b)
    {
        int hours = b.hour + (24 * (b.day - a.day)) - a.hour;
        int minutes = Math.abs(b.minute - a.minute);
        if (a.minute > b.minute)
            --hours;
        TimePoint c = new TimePoint(hours, minutes);
        return c;
    }
}
