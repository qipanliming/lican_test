package chapter2;

/**
 * Created by lican on 17/9/30.
 */
//@Named("heartsClub")
public class SgtPeppers implements CompactDisc {
    private String title = "title";
    private String artist = "the beatles";

    public void play() {
        System.out.println("playing " + title + "by " + artist);
    }
}
