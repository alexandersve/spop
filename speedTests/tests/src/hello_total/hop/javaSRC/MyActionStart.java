package run;

import org.apache.hop.workflow.actions.start.ActionStart;

public class MyActionStart extends ActionStart {

    public MyActionStart(
          int DayOfMonth,
          int hour,
          int intervalMinutes,
          int intervalSeconds,
          int minutes,
          boolean repeat,
          int schedulerType,
          int weekDay) {
        super();
        setDayOfMonth(DayOfMonth);
        setHour(hour);
        setIntervalMinutes(intervalMinutes);
        setIntervalSeconds(intervalSeconds);
        setMinutes(minutes);
        setRepeat(repeat);
        setSchedulerType(schedulerType);
        setWeekDay(weekDay);
    }

}
