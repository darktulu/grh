package com.bull.grh.repos.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Authority;

@Repository("authorityDao")
public interface AuthorityDao extends JpaRepository<Authority, Long>{

	public Authority findByRole(String role);

}
