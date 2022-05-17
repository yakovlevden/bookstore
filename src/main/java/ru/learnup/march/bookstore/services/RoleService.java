package ru.learnup.march.bookstore.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Role;
import ru.learnup.march.bookstore.entity.User;
import ru.learnup.march.bookstore.repository.RoleRepository;
import ru.learnup.march.bookstore.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

}