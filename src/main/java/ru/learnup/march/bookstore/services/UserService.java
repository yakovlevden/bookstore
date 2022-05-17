package ru.learnup.march.bookstore.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.User;
import ru.learnup.march.bookstore.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void createUser(User user) {
        User exist;
        exist = userRepository.findByUsername(user.getUsername());
        if (exist != null) {
            throw new EntityExistsException("User with Login " + user.getUsername() + " already exist");
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        log.info("CreateUser: {}", user);
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        log.info("Request getUserById: {}", id);
        return userRepository.getUserById(id);
    }

    public Boolean deleteUser(Long id) {
        log.info("DeleteUser by id: {}", id);
        userRepository.delete(userRepository.getById(id));
        return true;
    }

    public User updateUser(User user) {
        try {
            log.info("UpdateUser: {}", user.toString());
            return userRepository.save(user);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for User id {}", user.getId());
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        return userRepository.findByUsername(loginName);
    }
}