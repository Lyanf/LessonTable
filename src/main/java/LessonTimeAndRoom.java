class LessonTimeAndRoom {
    private int weekStart;
    private int weekEnd;
    private int day;
    private int timeStart;
    private int timeEnd;
    private WeekType weekType;
    private String room;
    private boolean trueLesson = true;

    enum WeekType {
        danzhou, shuangzhou, quanzhou
    }

    public LessonTimeAndRoom(String originString) {
        String[] splitStrings = originString.split(" ");
        int length = splitStrings.length;
        if (length == 1) {
            trueLesson = false;
            return;
        }
        this.room = splitStrings[length - 1];
        String week = splitStrings[0];
        if (week.contains("单")) {
            weekType = WeekType.danzhou;
        } else if (week.contains("双")) {
            weekType = WeekType.shuangzhou;
        } else {
            weekType = WeekType.quanzhou;
        }
        if (week.contains("第")) {
            weekStart = Integer.parseInt(week.substring(1, week.length() - 1));
            weekEnd = weekStart;
        } else {
            int i = week.indexOf('-');
            int j = week.indexOf('周');
            weekStart = Integer.parseInt(week.substring(0, i));
            weekEnd = Integer.parseInt(week.substring(i + 1, j));
        }
        switch (splitStrings[1].charAt(2)) {
            case '一':
                day = 1;
                break;
            case '二':
                day = 2;
                break;
            case '三':
                day = 3;
                break;
            case '四':
                day = 4;
                break;
            case '五':
                day = 5;
                break;
            case '六':
                day = 6;
                break;
            case '日':
                day = 7;
                break;
        }
        String time = splitStrings[2];
        if (time.contains("-")) {
            int i = time.indexOf('-');
            int j = time.indexOf('节');
            timeStart = Integer.parseInt(time.substring(0, i));
            timeEnd = Integer.parseInt(time.substring(i + 1, j));
        } else {
            timeStart = Integer.parseInt(time.substring(2, 3));
            timeEnd = Integer.parseInt(time.substring(3, 4));
        }

    }

    public int getWeekStart() {
        return weekStart;
    }

    public int getWeekEnd() {
        return weekEnd;
    }

    public int getDay() {
        return day;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public String getRoom() {
        return room;
    }

    public void setWeekStart(int weekStart) {
        this.weekStart = weekStart;
    }

    public void setWeekEnd(int weekEnd) {
        this.weekEnd = weekEnd;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTrueLesson(boolean trueLesson) {
        this.trueLesson = trueLesson;
    }

    public boolean isTrueLesson() {
        return trueLesson;
    }
}