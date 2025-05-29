package lk.ijse.cmjd108.LostandFoundSys_2025.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lk.ijse.cmjd108.LostandFoundSys_2025.dao.UserDao;

@Service
@RequiredArgsConstructor
public class UserDetailService_IMPL implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username)
                .map(user -> new User(
                        user.getEmail(),
                        user.getPassword(),
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

//        return userDao.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

}
