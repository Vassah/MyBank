package com.Vassah.MyBank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.Vassah.MyBank.Services.UserManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
	private UserManager userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin/**", "/admin").hasAuthority("Admin_role")
                .antMatchers("/", "/assets/**", "/vendor/**").permitAll()
                .antMatchers("/registration", "/ConfirmEmail", "/ConfirmEmail/**", "/login").not().fullyAuthenticated()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/user/profile").failureUrl("/login?error=true")
                .and().logout().logoutUrl("/logout").permitAll()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().rememberMe().tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(1 * 24 * 60 * 60); //24 hours
    }

    @Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

}
