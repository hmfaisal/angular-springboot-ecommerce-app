package com.basketbird.backend.config;

import com.basketbird.backend.security.auth.AuthenticationFailureHandler;
import com.basketbird.backend.security.auth.AuthenticationSuccessHandler;
import com.basketbird.backend.security.auth.LogoutSuccess;
import com.basketbird.backend.security.auth.RestAuthenticationEntryPoint;
import com.basketbird.backend.security.auth.TokenAuthenticationFilter;
import com.basketbird.backend.service.UserService;
import com.basketbird.backend.service.impl.CustomUserDetailsService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by fan.jin on 2016-10-19.
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	String [] publicUrls = new String [] {
			"/api/public/**",
			"/api/login",
			"/api/logout",
			"/api/register",
			"/api/confirm",
			"/auth/**",
			"/api/auth/**",
	};

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private LogoutSuccess logoutSuccess;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserIdSource userIdSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Set a custom successHandler on the SocialAuthenticationFilter
		final SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
		socialConfigurer.addObjectPostProcessor(new ObjectPostProcessor<SocialAuthenticationFilter>() {
			@Override
			public <O extends SocialAuthenticationFilter> O postProcess(O socialAuthenticationFilter) {
				socialAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
				return socialAuthenticationFilter;
			}
			
		});
		http
				.csrf()
				.ignoringAntMatchers(publicUrls)
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
				.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
			.and()
				.exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint )
			.and()
				.addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests()
				//allow anonymous calls to social login
				//.antMatchers(
						//"/auth/**",
						//"/api/auth/**",
						//"/api/registration"
					//).permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/api/login")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
				.logoutSuccessHandler(logoutSuccess)
				.deleteCookies(TOKEN_COOKIE)
			.and()
				.apply(socialConfigurer.userIdSource(userIdSource));
	}
	
	@Bean
	public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
		return new TokenAuthenticationFilter();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
		.userDetailsService( jwtUserDetailsService )
		.passwordEncoder( passwordEncoder() );

	}
	
	@Override
	protected UserService userDetailsService() {
		return userService;
	}
}
