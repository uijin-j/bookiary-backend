package oz.bookiarybacked.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = {"oz.bookiarybacked.config.properties",
	"oz.bookiarybacked.auth.config.properties"})
public class PropertyConfig {
}
