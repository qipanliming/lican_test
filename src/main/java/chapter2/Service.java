package chapter2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lican on 17/11/2.
 */
@Component
public class Service {
    Shopping shopping;
    @Autowired
    public void setShopping(Shopping shopping){
        this.shopping = shopping;
    }
}
