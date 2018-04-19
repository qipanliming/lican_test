package chapter2;

/**
 * Created by lican on 17/9/30.
 */
//@Named("heartsClub")
public class SgtPeppers implements CompactDisc {
    private String title = "title";
    private String artist = "the beatles";

    public SgtPeppers(String property) {
    }

    public void play() {
        System.out.println("playing " + title + "by " + artist);
    }

    public static void main(String[] args) {
        System.out.println("xxx");
    }
}
