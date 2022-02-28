package com.example.testSpringSecurityWithJwt.jwt;

import com.example.testSpringSecurityWithJwt.model.Role;
import com.example.testSpringSecurityWithJwt.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;


/**
 * UserDetails 类的工厂创建方法
 *
 */
public final class JwtUserFactory {
 
    private JwtUserFactory() {
    }


    /**
     * 根据角色创建 GrantedAuthority
     * @param user
     * @return
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthoritiesByRole(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }
 
    private static List<GrantedAuthority> mapToGrantedAuthoritiesByRole(List<Role> authorities) {
        return authorities.stream()
                .map(a -> new SimpleGrantedAuthority(a.getRole()))
                .collect(Collectors.toList());
    }




    /**
     * 根据权限创建 GrantedAuthority
     * @param user
     * @return
     */
    /*public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthoritiesByAuthority(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthoritiesByAuthority(List<Role> authorities) {
        List<GrantedAuthority> collect = authorities
                .stream()
                .flatMap(a -> a.getAuthoritys().stream().map(b -> new GrantedAuthorityProvider(b.getAuthority()))).collect(Collectors.toList());
        return collect ;
    }*/
}