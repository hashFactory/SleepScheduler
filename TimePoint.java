public class TimePoint
{
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;

    public TimePoint()
    {

    }

    public TimePoint(int _hour, int _minute)
    {
        hour = _hour;
        minute = _minute;
    }

    public TimePoint(int _year, int _month, int _day)
    {
        year = _year;
        month = _month;
        day = _day;
    }

    public TimePoint(int _year, int _month, int _day, int _hour, int _minute)
    {
        year = _year;
        month = _month;
        day = _day;
        hour = _hour;
        minute = _minute;
    }

}
