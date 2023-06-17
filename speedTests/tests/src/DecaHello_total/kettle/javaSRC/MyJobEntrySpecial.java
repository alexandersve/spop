package run;

import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;

public class MyJobEntrySpecial extends JobEntrySpecial {

    public MyJobEntrySpecial(
          boolean start,
          boolean dummy,
          boolean repeat,
          int schedulerType,
          int intervalSeconds,
          int intervalMinutes,
          int hour,
          int minutes,
          int weekDay,
          int DayOfMonth) {
        super();
        setParentJobMeta(new JobMeta());
        setStart(start);
        setDummy(dummy);
        setRepeat(repeat);
        setSchedulerType(schedulerType);
        setIntervalSeconds(intervalSeconds);
        setIntervalMinutes(intervalMinutes);
        setHour(hour);
        setMinutes(minutes);
        setWeekDay(weekDay);
        setDayOfMonth(DayOfMonth);
    }

}
