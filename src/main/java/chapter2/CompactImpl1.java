package chapter2;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by lican on 17/10/31.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//
public class CompactImpl1 implements CompactDisc {
    @Override
    public void play() {

    }
}
