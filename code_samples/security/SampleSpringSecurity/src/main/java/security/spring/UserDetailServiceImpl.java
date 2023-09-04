package security.spring;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import security.domain.AppUser;

//Custom class to load users' details for authentication/authorization purposes
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        UserBuilder builder = User.withUsername(user.getUsername());


        // Encode the password
        // Note there is no need to do this if the password is already encoded in the DB
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        builder.password(encodedPassword);
        builder.roles(user.getRole());

        return builder.build();
    }

    private AppUser findByUsername(String username) {
        // TODO fetch user from DB
        if (username.equals("user")) {
            return new AppUser("user", "password", "USER");
        } else if (username.equals("admin")) {
            return new AppUser("admin", "password", "ADMIN");
        }
        return null;
    }

}
