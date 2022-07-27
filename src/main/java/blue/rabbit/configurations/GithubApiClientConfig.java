package blue.rabbit.configurations;

import blue.rabbit.rest.client.GithubApi;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class GithubApiClientConfig {
    @Bean
    public GithubApi githubApi(@Value("${github.api.url}") String githubUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(githubUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(GithubApi.class);
    }

    @Bean
    public OkHttpClient createHttpClient(@Value("${retrofit.logger.level}") HttpLoggingInterceptor.Level level) {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(createHttpLoggingInterceptor(level))
                .build();
    }

    private HttpLoggingInterceptor createHttpLoggingInterceptor(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(log::info);
        interceptor.level(level);
        return interceptor;
    }
}
