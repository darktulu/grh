package com.bull.grh.repos.admin;

import com.bull.grh.domaine.Authority;
import com.bull.grh.domaine.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneDao extends JpaRepository<Personne, Long> {
    Personne findByUsername(String username);

    @Query("SELECT p FROM Personne p WHERE :authority not in elements (p.authorities)")
    List<Personne> getHaveNotAuthority(@Param("authority") Authority authority);

    @Query("SELECT p FROM Personne p WHERE :authority  in elements (p.authorities)")
    List<Personne> getHaveAuthority(@Param("authority") Authority authority);
}
