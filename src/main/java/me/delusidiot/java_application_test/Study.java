package me.delusidiot.java_application_test;

public class Study {
    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;

    public Study() {
        this.limit = 0;
    }

    public Study(int limit) throws IllegalAccessException {
        if (limit < 0)
            throw new IllegalAccessException("limit은 0보다 커야 한다.");
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }
}
