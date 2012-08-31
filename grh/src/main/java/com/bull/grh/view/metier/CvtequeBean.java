package com.bull.grh.view.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.service.metier.CvtequeService;
import com.bull.grh.view.metier.vo.CandidatVO;

@Scope("view")
@Component
public class CvtequeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private CvtequeService cvtequeService;

    @Inject
    transient private DozerBeanMapper mapper;

    private List<CandidatVO> candidats = new ArrayList<CandidatVO>();
    private String nom, prenom;
    private int page, size, pagecount;

    public void search() {
	Map<String, String> query = new HashMap<String, String>();
	query.put("nom", nom);
	query.put("prenom", prenom);
	Page<Candidat> result = cvtequeService.loadPageCandidats(0, size, query);
	candidats = candidatMap(result.getContent());
	pagecount = result.getTotalPages();
	page = 0;
    }

    public void pagesearch() {
	Map<String, String> query = new HashMap<String, String>();
	query.put("nom", nom);
	query.put("prenom", prenom);
	Page<Candidat> result = cvtequeService.loadPageCandidats(page, size, query);
	candidats = candidatMap(result.getContent());
	pagecount = result.getTotalPages();
    }

    private List<CandidatVO> candidatMap(List<Candidat> content) {
	List<CandidatVO> result = new ArrayList<CandidatVO>();
	for (Candidat c : content) {
	    result.add(mapper.map(c, CandidatVO.class));
	}
	return result;
    }

    public void next() {
	if (page < pagecount)
	    page += 1;
	pagesearch();
    }

    public void previous() {
	if (page > 0)
	    page -= 1;
	pagesearch();
    }

    public void first() {
	page = 1;
	pagesearch();
    }

    public void last() {
	page = pagecount;
	pagesearch();
    }

    public List<CandidatVO> getCandidats() {
	return candidats;
    }

    public void setCandidats(List<CandidatVO> candidats) {
	this.candidats = candidats;
    }

    public CvtequeService getCvtequeService() {
	return cvtequeService;
    }

    public void setCvtequeService(CvtequeService cvtequeService) {
	this.cvtequeService = cvtequeService;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public int getPage() {
	return page;
    }

    public void setPage(int page) {
	this.page = page;
    }

    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }

    public int getPagecount() {
	return pagecount;
    }

    public void setPagecount(int pagecount) {
	this.pagecount = pagecount;
    }

}
