package security.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //uncomment to disable csrf
                .authorizeRequests()
                .antMatchers("/home*").authenticated()
                .antMatchers("/admin.jsp").hasRole("ADMIN")
                .antMatchers("/user.jsp").hasRole("USER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/home", true)
                .and()
                .logout();
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(new UserDetailServiceImpl())
                .passwordEncoder(new Pbkdf2PasswordEncoder()).and().build();

    }

}
