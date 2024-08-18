package fastApi.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="fastApi.demo")
public class FastApiApplication {

        private final static Logger logger = LogManager.getLogger(FastApiApplication.class);
    
	public static void main(String[] args) {
            SpringApplication.run(FastApiApplication.class, args);
            logger.info("Ejecutando programa.");
	}

}
