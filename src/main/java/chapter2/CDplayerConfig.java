package chapter2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by lican on 17/9/30.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class CDplayerConfig {
    @Autowired
    Environment env;

    @Bean
    public CompactDisc disc(@Value("${title}") String title) {
        this.title = title;
    }
}
