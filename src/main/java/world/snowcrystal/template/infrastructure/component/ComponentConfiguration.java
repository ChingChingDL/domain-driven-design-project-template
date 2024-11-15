package world.snowcrystal.template.infrastructure.component;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import world.snowcrystal.template.domain.register.component.PasswordEncoder;
import world.snowcrystal.template.domain.register.component.UserPasswordGenerator;
import world.snowcrystal.template.domain.register.component.UsernameGenerator;

@Configuration
public class ComponentConfiguration {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new DefaultPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserPasswordGenerator.class)
    public UserPasswordGenerator userPasswordGenerator() {
        return new DefaultUserPasswordGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(UsernameGenerator.class)
    public UsernameGenerator usernameGenerator() {
        return new DefaultUsernameGenerator();
    }
}
