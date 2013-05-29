package com.bull.grh.repos.specs;

import com.bull.grh.domaine.Candidat;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CandidatSpecs {
    public static Specification<Candidat> prenomLike(final String prenom) {
        return new Specification<Candidat>() {
            @Override
            public Predicate toPredicate(Root<Candidat> candidatRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String likepattern = getLikePattern(prenom);
                return cb.like(candidatRoot.<String>get("prenom"), likepattern);
            }
        };
    }

    public static Specification<Candidat> nomLike(final String nom) {
        return new Specification<Candidat>() {
            @Override
            public Predicate toPredicate(Root<Candidat> candidatRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String likepattern = getLikePattern(nom);
                return cb.like(candidatRoot.<String>get("nom"), likepattern);
            }
        };
    }

    private static String getLikePattern(final String searchTerm) {
        StringBuilder pattern = new StringBuilder("%");
        pattern.append(searchTerm.toLowerCase());
        pattern.append("%");
        return pattern.toString();
    }
}
