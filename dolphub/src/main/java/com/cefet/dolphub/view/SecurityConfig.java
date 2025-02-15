package com.cefet.dolphub.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/cadastro", "/salvarUsuario", "/login",
                                                                "/confirmacao", "/styles/**", "/imgs/**")
                                                .permitAll() // Caminhos permitidos sem login
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .usernameParameter("cpf")
                                                .passwordParameter("senha")
                                                .defaultSuccessUrl("/cursosAdquiridos", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login")
                                                .permitAll())
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers("/acessarCurso/*/responderQuestao",
                                                                "/acessoCurso/marcar-video/*") // Ignorar
                                // CSRF
                                // para
                                // requisições
                                // específicas,
                                // se
                                // necessário
                                );

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
        
}
