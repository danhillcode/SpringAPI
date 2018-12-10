package hello;

public class First {
    private final long id;
    private final String content;

    public First(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
