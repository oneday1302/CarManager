package ua.foxminded.javaspring.carmanager.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.foxminded.javaspring.carmanager.repository.BodyTypeRepository;
import ua.foxminded.javaspring.carmanager.repository.CarRepository;
import ua.foxminded.javaspring.carmanager.repository.MakerRepository;
import ua.foxminded.javaspring.carmanager.repository.ModelRepository;

@Configuration
@ComponentScan("ua.foxminded.javaspring.carmanager.service")
@ComponentScan("ua.foxminded.javaspring.carmanager.mapper")
public class ServiceTestConfig {

    @Bean
    public BodyTypeRepository bodyTypeRepository() {
        return Mockito.mock(BodyTypeRepository.class);
    }

    @Bean
    public CarRepository carRepository() {
        return Mockito.mock(CarRepository.class);
    }

    @Bean
    public MakerRepository makerRepository() {
        return Mockito.mock(MakerRepository.class);
    }

    @Bean
    public ModelRepository modelRepository() {
        return Mockito.mock(ModelRepository.class);
    }
}
