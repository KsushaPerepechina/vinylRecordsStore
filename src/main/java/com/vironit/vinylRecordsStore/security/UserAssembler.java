package com.vironit.vinylRecordsStore.security;

/**
 *
 */
import java.util.ArrayList;
import java.util.Collection;

import com.vironit.vinylRecordsStore.entity.Role;
import com.vironit.vinylRecordsStore.entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сборщик аккаунта пользователя.
 */
@Component("assembler")
public class UserAssembler {

    @Transactional
    User buildUserFromUserEntity(UserAccount userAccount) {

        String login = userAccount.getEmail();
        String password = userAccount.getPassword();
        boolean enabled = userAccount.isActive();
        boolean accountNonExpired = userAccount.isActive();
        boolean credentialsNonExpired = userAccount.isActive();
        boolean accountNonLocked = userAccount.isActive();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userAccount.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        User user = new User(login, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return user;
    }
}
