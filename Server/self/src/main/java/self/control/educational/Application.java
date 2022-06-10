package self.control.educational;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@SpringBootApplication
@MapperScan({"self.control.educational.dao"})
@EnableCaching
public class Application  implements WebMvcConfigurer{


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")
						.allowedOrigins("*")
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
						.maxAge(3600);

			}
		};
	}
	
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
