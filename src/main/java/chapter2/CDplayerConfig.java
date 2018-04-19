package chapter2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by lican on 17/9/30.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class CDplayerConfig {
    /*@Autowired
    Environment env;

    @Bean
    public CompactDisc disc(@Value("${title}") String title) {
        this.title = title;
    }*/
}
