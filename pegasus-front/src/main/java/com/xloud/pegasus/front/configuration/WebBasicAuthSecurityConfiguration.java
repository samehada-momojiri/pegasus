package com.xloud.pegasus.front.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@EnableWebSecurity // (1) Spring Securityを使うための設定
public class WebBasicAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	// (1) Basic認証のID
	@Value("${security.admin.username}")
	private String username;

	// (2) Basic認証のパスワード
	@Value("${security.admin.password")
	private String password;

	// (2) 主に全体に対するセキュリティ設定を行う
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/", "/webjars/**", "/dev/v1.0/mpa/sample/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// (3) 主にURLごとに異なるセキュリティ設定を行う
		// (3) Basic認証の対象となるパス
		http.antMatcher("/nothing/**");

		// (4) Basic認証を指定
		http.httpBasic();

		// (5) 対象のすべてのパスに対して認証を有効にする
		http.authorizeRequests().anyRequest().authenticated();

		// (6) すべてのリクエストをステートレスとして設定
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// (4) 主に認証方法の実装の設定を行う
		// (7) Basic認証の実装を指定
		auth.authenticationProvider(new BasicAuthenticationProvider());
	}

	// (8) 認証処理の実装クラス
	public class BasicAuthenticationProvider implements AuthenticationProvider {

		// (9) 認証チェック
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {

			String name = authentication.getName();
			String password = authentication.getCredentials().toString();

			// 入力された name / password をチェックする
			if (name.equals(username) && password.equals(password)) {
				return new UsernamePasswordAuthenticationToken(name, password, authentication.getAuthorities());
			}

			throw new AuthenticationCredentialsNotFoundException("basic auth error");
		}

		// (10) 処理すべきAuthenticationクラスのチェック
		@Override
		public boolean supports(Class<?> authentication) {
			return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		}
	}

}
