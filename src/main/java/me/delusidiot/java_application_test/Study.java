package me.delusidiot.java_application_test;

public class Study {
    private StudyStatus status = StudyStatus.DRAFT;
    private String name;
    private int limit;

    public Study() {
        this.limit = 0;
    }

    public Study(int limit) throws IllegalAccessException {
        if (limit < 0)
            throw new IllegalAccessException("limit은 0보다 커야 한다.");
        this.limit = limit;
    }

    public Study(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                '}';
    }
}
