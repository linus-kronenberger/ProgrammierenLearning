public class Time {
    // ZEIT
    private int hour;
    private int minute;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        if(!(hour <= 23 && hour>=0) || !(minute<=59 && minute>=0)) {
            throw new DBException("Wertebereich Stunde: 0-12, Wertebereich Minute: 0-59");
        }
    }
    @Override
    public String toString() {
        String returnValue = "";
        if(hour >= 10) {
            returnValue += String.valueOf(hour);
        } else {
            returnValue += "0" + String.valueOf(hour);
        }
        returnValue += ":";
        if(minute >= 10) {
            returnValue += String.valueOf(minute);
        } else {
            returnValue += "0" + String.valueOf(minute);
        }
        return returnValue;
    }
}
