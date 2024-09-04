package br.com.lGabrielDev.praticando.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurations {
    
    
    //method para setarmos as permissoes das rotas
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                //rotas de usuario comum
                .requestMatchers("/usuario/**").permitAll() //todas as rotas filhas de "/usuario" estao liberadas
                //rotas autenticadas
                .requestMatchers("/admin/rota1", "/admin/rota2").hasAuthority("USUARIO_PADRAO") //essas duas rotas precisam de "tal" cargo
                .requestMatchers(HttpMethod.GET, "admin/usuarios/**").hasAuthority("ADMIN")
                //rotas de admin picas.
                .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/admin/**").authenticated()
                .anyRequest().hasAuthority("USUARIO_PADRAO")
                
            )
            .build();
    }


    //bean para funcionar a criptografia de senhas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //bean para setarmos quem sera o UserDetailsService da nossa apliacao.
    @Bean
    public AuthenticationManager authManager(UserDetailsService uds){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(uds);
        return new ProviderManager(dao);

    }
}
