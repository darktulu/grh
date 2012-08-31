package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Demande;
import com.bull.grh.domaine.types.EtatDemande;

@Repository("demandeDao")
public interface DemandeDao extends JpaRepository<Demande, Long> {

	public Demande findByIntitulePoste(String intitule);

	public List<Demande> findByEtatDemande(EtatDemande etatDemande);

}
