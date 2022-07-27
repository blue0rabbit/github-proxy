package blue.rabbit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AutorunGithubProxy {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AutorunGithubProxy.class)
                .headless(true)
                .run();
    }
}
