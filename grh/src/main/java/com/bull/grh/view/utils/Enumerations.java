package com.bull.grh.view.utils;

import com.bull.grh.domaine.types.DecisionEntretien;
import com.bull.grh.domaine.types.Gender;
import com.bull.grh.domaine.types.NiveauCompetence;
import com.bull.grh.domaine.types.NiveauLangue;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("enumsBean")
@Scope("singleton")
public class Enumerations {

    public Gender[] getSexes() {
        return Gender.values();
    }

    public NiveauCompetence[] getLvlCompetences() {
        return NiveauCompetence.values();
    }

    public NiveauLangue[] getLvlLangues() {
        return NiveauLangue.values();
    }

    public List<DecisionEntretien> getDecisionEntretiensCE() {
        List<DecisionEntretien> x = new ArrayList<DecisionEntretien>();
        x.add(DecisionEntretien.OK);
        x.add(DecisionEntretien.REJECTED);
        x.add(DecisionEntretien.PENDING);
        return x;
    }

    public List<DecisionEntretien> getDecisionEntretiensRH() {
        List<DecisionEntretien> x = new ArrayList<DecisionEntretien>();
        x.add(DecisionEntretien.OK);
        x.add(DecisionEntretien.REJECTED);
        x.add(DecisionEntretien.RECALL);
        return x;
    }
}
