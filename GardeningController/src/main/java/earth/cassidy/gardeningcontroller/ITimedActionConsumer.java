package earth.cassidy.gardeningcontroller;

import earth.cassidy.gardeningcontroller.schedule.TimedAction;

public interface ITimedActionConsumer {
    void onTurnOn(TimedAction timedAction);
    void onTurnOff(TimedAction timedAction);
}
