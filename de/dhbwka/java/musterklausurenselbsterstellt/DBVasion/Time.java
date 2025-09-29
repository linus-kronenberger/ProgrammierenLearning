public class Time {
    private int hour;
    private int minute;
    public Time(int hour, int minute ) throws DBException {
    //repräsentiert Zeit
        if(((0 <= hour) && (hour <= 23)) && ((0 <= minute) && (minute <= 59))) {
            this.minute = minute;
            this.hour = hour;
        } else {
            throw new DBException("hours are between 0 and 23. Minutes are between 0 and 59");
        }
    }
    public String toString() {
        String hournAnzeige = "";
        if(hour > 9) {
            hournAnzeige = String.valueOf(hour);
        } else {
            hournAnzeige = "0" + String.valueOf(hour);
        }
        String minutenAnzeige = "";
        if(minute > 9) {
            minutenAnzeige = String.valueOf(minute);
        } else {
            minutenAnzeige = "0" + String.valueOf(minute);
        }
        return hournAnzeige + ":" + minutenAnzeige;
    }
    public int getHour() {
        return this.hour;
    }
    public int getMinute() {
        return this.minute;
    }
}
