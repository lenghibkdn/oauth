//package com.oauth.oauth;
//
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//@EnableOAuth2Sso
//@AutoConfigureBefore({OAuth2AutoConfiguration.class})
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        // セキュリティ設定を無視するリクエストの設定
//        // 静的リソース(images、css、javascript)を無視する設定を記述
//        web.ignoring().antMatchers(
//                            "/images/**",
//                            "/css/**",
//                            "/javascript/**",
//                            "/webjars/**");
//    }
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // CSRF対策を無効化
//            .authorizeRequests()
//                // indexはアクセスを許可
//                .antMatchers("/login").permitAll()
//                .antMatchers("/test").permitAll()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//            .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
//                .logoutSuccessUrl("/")
//                // ログアウト時に削除するクッキー名
//                .deleteCookies("JSESSIONID")
//                // ログアウト時のセッション破棄を有効化
//                .invalidateHttpSession(true)
//                .permitAll();
//
//    }
//}
