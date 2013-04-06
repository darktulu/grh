package com.bull.grh.repos.admin;

import com.bull.grh.domaine.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
    Authority findByRole(String role);
}
