package com.bull.grh.view.utils;

import com.bull.grh.service.admin.AdminValueService;
import com.bull.grh.view.admin.vo.ValueVO;
import com.bull.grh.view.utils.vo.ValueListConst;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
@Scope("request")
public class ListOfValues implements ValueListConst {
    private List<ValueVO> listVille;
    private List<ValueVO> listPays;
    private List<ValueVO> listNiveauExp;
    private List<ValueVO> listNiveauEtu;
    private List<ValueVO> listDisponibilite;
    private List<ValueVO> listTypesContrat;
    private List<ValueVO> listSituationActuelles;
    private List<ValueVO> listNationalite;
    private List<ValueVO> listSituationFamiliale;
    private List<ValueVO> listQuestionSecrete;

    @Inject
    private AdminValueService adminValueService;

    public List<ValueVO> getListVille() {
        if (listVille == null || listVille.isEmpty()) {
            listVille = adminValueService.loadValuesByDefName(VILLE);
        }
        return listVille;
    }

    public List<ValueVO> getListPays() {
        if (listPays == null || listPays.isEmpty()) {
            listPays = adminValueService.loadValuesByDefName(PAYS);
        }
        return listPays;
    }

    public List<ValueVO> getListNiveauExp() {
        if (listNiveauExp == null || listNiveauExp.isEmpty()) {
            listNiveauExp = adminValueService.loadValuesByDefName(NIVEAU_EXP);
        }
        return listNiveauExp;
    }

    public List<ValueVO> getListNiveauEtu() {
        if (listNiveauEtu == null || listNiveauEtu.isEmpty()) {
            listNiveauEtu = adminValueService.loadValuesByDefName(NIVEAU_ETU);
        }
        return listNiveauEtu;
    }

    public List<ValueVO> getListDisponibilite() {
        if (listDisponibilite == null || listDisponibilite.isEmpty()) {
            listDisponibilite = adminValueService.loadValuesByDefName(DISPONIBILITE);
        }
        return listDisponibilite;
    }

    public List<ValueVO> getListTypesContrat() {
        if (listTypesContrat == null || listTypesContrat.isEmpty()) {
            listTypesContrat = adminValueService.loadValuesByDefName(TYPE_CONTRAT);
        }
        return listTypesContrat;
    }

    public List<ValueVO> getListSituationActuelles() {
        if (listSituationActuelles == null || listSituationActuelles.isEmpty()) {
            listSituationActuelles = adminValueService.loadValuesByDefName(SITUATION_ACTUELLE);
        }
        return listSituationActuelles;
    }

    public List<ValueVO> getListNationalite() {
        if (listNationalite == null || listNationalite.isEmpty()) {
            listNationalite = adminValueService.loadValuesByDefName(NATIONALITE);
        }
        return listNationalite;
    }

    public List<ValueVO> getListSituationFamiliale() {
        if (listSituationFamiliale == null || listSituationFamiliale.isEmpty()) {
            listSituationFamiliale = adminValueService.loadValuesByDefName(SITUATION_FAMILIALE);
        }
        return listSituationFamiliale;
    }

    public List<ValueVO> getListQuestionSecrete() {
        if (listQuestionSecrete == null || listQuestionSecrete.isEmpty()) {
            listQuestionSecrete = adminValueService.loadValuesByDefName(QUESTION_SECRETE);
        }
        return listQuestionSecrete;
    }

    public void setListNationalite(List<ValueVO> listNationalite) {
        this.listNationalite = listNationalite;
    }

    public void setListSituationFamiliale(List<ValueVO> listSituationFamiliale) {
        this.listSituationFamiliale = listSituationFamiliale;
    }

    public void setListQuestionSecrete(List<ValueVO> listQuestionSecrete) {
        this.listQuestionSecrete = listQuestionSecrete;
    }

    public void setListVille(List<ValueVO> listVille) {
        this.listVille = listVille;
    }

    public void setListPays(List<ValueVO> listPays) {
        this.listPays = listPays;
    }

    public void setListNiveauExp(List<ValueVO> listNiveauExp) {
        this.listNiveauExp = listNiveauExp;
    }

    public void setListNiveauEtu(List<ValueVO> listNiveauEtu) {
        this.listNiveauEtu = listNiveauEtu;
    }

    public void setListDisponibilite(List<ValueVO> listDisponibilite) {
        this.listDisponibilite = listDisponibilite;
    }

    public void setListTypesContrat(List<ValueVO> listTypesContrat) {
        this.listTypesContrat = listTypesContrat;
    }

    public void setListSituationActuelles(List<ValueVO> listSituationActuelles) {
        this.listSituationActuelles = listSituationActuelles;
    }
}
