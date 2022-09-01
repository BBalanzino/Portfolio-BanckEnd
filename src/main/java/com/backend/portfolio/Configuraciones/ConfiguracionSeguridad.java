package com.backend.portfolio.Configuraciones;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.portfolio.auth.JwtAuthenticationEntryPoint;
import com.backend.portfolio.auth.JwtFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
// import com.backend.portfolio.auth.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter filter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/persona/**").permitAll()
				.antMatchers(HttpMethod.GET, "/estudio/**").permitAll()
				.antMatchers(HttpMethod.GET, "/experiencias/**").permitAll()
				.antMatchers(HttpMethod.GET, "/proyecto/**").permitAll()
				.antMatchers(HttpMethod.GET, "/skill/**").permitAll()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {

	// // // Enable CORS and disable CSRF
	// // http = http.cors().and().csrf().disable();

	// // // Set session management to stateless
	// // http = http
	// // .sessionManagement()
	// // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	// // .and();

	// // // Set unauthorized requests exception handler
	// // http = http
	// // .exceptionHandling()
	// // .authenticationEntryPoint(
	// // (request, response, ex) -> {
	// // response.sendError(
	// // HttpServletResponse.SC_UNAUTHORIZED,
	// // ex.getMessage());
	// // })
	// // .and();

	// // http.authorizeRequests()
	// // .antMatchers(HttpMethod.GET, "/persona/**").permitAll()
	// // .antMatchers(HttpMethod.GET, "/estudio/**").permitAll()
	// // .antMatchers(HttpMethod.GET, "/experiencia/**").permitAll()
	// // .antMatchers(HttpMethod.GET, "/proyecto/**").permitAll()
	// // .antMatchers("/usuario/login").permitAll()
	// // .anyRequest().authenticated();

	// // http.addFilterBefore(jwtRequestFilter,
	// // UsernamePasswordAuthenticationFilter.class);

	// http.headers().frameOptions().sameOrigin().and().authorizeRequests()
	// .antMatchers("/css/*", "/js/*", "/img/*", "/**")
	// .permitAll()
	// .and().formLogin()
	// .loginPage("/login") // Que formulario esta mi login
	// .loginProcessingUrl("/logincheck")
	// .usernameParameter("correo") // Como viajan los datos del logueo
	// .passwordParameter("password")// Como viajan los datos del logueo
	// .defaultSuccessUrl("/") // A que URL viaja
	// .permitAll()
	// .and().logout() // Aca configuro la salida
	// .logoutUrl("/logout")
	// .logoutSuccessUrl("/login?logout")
	// .permitAll().and().csrf().disable();
	// }

}
