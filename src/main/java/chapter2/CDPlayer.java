package chapter2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;

/**
 * Created by lican on 17/9/30.
 */
@ComponentScan
public class CDPlayer {
    private CompactDisc cd;

    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Autowired
    public void insertDisc(CompactDisc cd) {
        this.cd = cd;
    }

    @Bean
    @Conditional(ExistsCondition.class)
    public SgtPeppers sgtPeppers(){
        return new SgtPeppers();
    }

    @Bean
    public CDPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }
}
