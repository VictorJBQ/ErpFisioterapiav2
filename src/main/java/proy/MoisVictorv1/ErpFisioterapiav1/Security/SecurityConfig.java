package proy.MoisVictorv1.ErpFisioterapiav1.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
	        .httpBasic()
	            .and()
	        .authorizeHttpRequests()
	            .requestMatchers("/css/**","/assets/**","/assets2/**","/libreria/**","/download/**","/javascript/**", "/js/**", "/images/**","/index").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/intranet/Plantilla") // Agrega esta línea
	            .permitAll()
	            .and()
	        .logout()
	            .logoutSuccessUrl("/login")
	            .permitAll().and().logout().permitAll();

	    return http.build();
	    }
	 
	
	
	 
	 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

