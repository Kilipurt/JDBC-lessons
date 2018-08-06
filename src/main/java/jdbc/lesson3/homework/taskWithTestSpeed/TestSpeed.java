package jdbc.lesson3.homework.taskWithTestSpeed;

public class TestSpeed {
    private long id;
    private String someString;
    private int someNumber;

    public TestSpeed(long id, String someString, int someNumber) {
        this.id = id;
        this.someString = someString;
        this.someNumber = someNumber;
    }

    public long getId() {
        return id;
    }

    public String getSomeString() {
        return someString;
    }

    public int getSomeNumber() {
        return someNumber;
    }
}
