package com.bull.grh.repos.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Authority;
import com.bull.grh.domaine.Personne;

@Repository("personneDao")
public interface PersonneDao extends JpaRepository<Personne, Long> {

	public Personne findByUsername(String username);

	@Query("SELECT p FROM Personne p WHERE :authority not in elements (p.authorities)")
	public List<Personne> getHaveNotAuthority(@Param("authority") Authority authority);

	@Query("SELECT p FROM Personne p WHERE :authority  in elements (p.authorities)")
	public List<Personne> getHaveAuthority(@Param("authority") Authority authority);

}
