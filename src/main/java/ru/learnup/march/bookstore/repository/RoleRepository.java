package ru.learnup.march.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Role;
import ru.learnup.march.bookstore.entity.User;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll();

    Role getRoleById(Long id);

    Role findByRole(String role);
}
