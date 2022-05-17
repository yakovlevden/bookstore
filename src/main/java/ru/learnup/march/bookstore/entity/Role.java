package ru.learnup.march.bookstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
