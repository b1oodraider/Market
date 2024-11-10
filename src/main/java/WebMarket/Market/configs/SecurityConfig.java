package WebMarket.Market.configs;

import WebMarket.Market.services.UsersDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UsersDetailsService userDetailsService;

    public SecurityConfig(UsersDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req->req.requestMatchers("/hello","/login/**","/secure/registration", "/error",
                "/stock/{spring:[0-9]+}", "/stock", "stock/cart").permitAll())
                .authorizeHttpRequests(req-> req.requestMatchers("/stock/addNew").hasRole("ADMIN"))
                .formLogin(log->log.loginPage("/secure/login").permitAll()
                        .loginProcessingUrl("/secure/processing_login").permitAll()
                        .defaultSuccessUrl("/hello")
                        .failureUrl("/secure/login?error=true").permitAll())

                .authorizeHttpRequests(req-> req.anyRequest().authenticated())
                .logout(logout->logout.logoutUrl("/secure/logout")
                        .logoutSuccessUrl("/secure/login"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/*"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
