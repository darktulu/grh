package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Demande;
import com.bull.grh.domaine.types.EtatDemande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeDao extends JpaRepository<Demande, Long> {
    Demande findByIntitulePoste(String intitule);

    List<Demande> findByEtatDemande(EtatDemande etatDemande);

    List<Demande> findByEtatDemandeIn(List<EtatDemande> etatDemandes);
}
