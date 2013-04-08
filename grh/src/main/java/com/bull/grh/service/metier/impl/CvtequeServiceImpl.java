package com.bull.grh.service.metier.impl;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.repos.specs.CandidatSpecs;
import com.bull.grh.service.metier.CvtequeService;
import com.bull.grh.view.metier.vo.CandidatVO;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CvtequeServiceImpl extends CandidatSpecs implements CvtequeService {
    @Inject
    private CandidatDao candidatDao;
    @Inject
    private DozerBeanMapper mapper;

    @Override
    public List<CandidatVO> loadAllCandidat(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Candidat> list = candidatDao.findAll(pageRequest);
        List<CandidatVO> result = new ArrayList<CandidatVO>();
        for (Candidat c : list.getContent()) {
            result.add(mapper.map(c, CandidatVO.class));
        }
        return result;
    }

    @Override
    public List<CandidatVO> loadCandidats(Map<String, String> query) {
        Specification<Candidat> sp;

        // always search for name cause if "" it will return all the candidats
        if (query.get(NOM) == null || "".equals(query.get(NOM))) {
            sp = Specifications.where(nomLike(""));
        } else
            sp = Specifications.where(nomLike(query.get(NOM)));
        if (query.get(PRENOM) != null || !"".equals(query.get(PRENOM))) {
            sp = Specifications.where(sp).and(prenomLike(query.get(PRENOM)));
        }

        //search with specifications
        List<Candidat> list = candidatDao.findAll(sp);

        //map to CandidatVO and return the result
        List<CandidatVO> result = new ArrayList<CandidatVO>();
        for (Candidat c : list) {
            result.add(mapper.map(c, CandidatVO.class));
        }
        return result;
    }

    @Override
    public List<CandidatVO> loadCandidats(int page, int size, Map<String, String> query) {
        Specification<Candidat> sp;

        // always search for name cause if "" it will return all the candidats
        if (query.get(NOM) == null || "".equals(query.get(NOM))) {
            sp = Specifications.where(nomLike(""));
        } else
            sp = Specifications.where(nomLike(query.get(NOM)));
        if (query.get(PRENOM) != null || !"".equals(query.get(PRENOM))) {
            sp = Specifications.where(sp).and(prenomLike(query.get(PRENOM)));
        }

        //search with specifications and page request
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Candidat> list = candidatDao.findAll(sp, pageRequest);

        //map to CandidatVO and return the result
        List<CandidatVO> result = new ArrayList<CandidatVO>();
        for (Candidat c : list.getContent()) {
            result.add(mapper.map(c, CandidatVO.class));
        }
        return result;
    }

    @Override
    public Page<Candidat> loadPageCandidats(int page, int size, Map<String, String> query) {
        Specification<Candidat> sp;

        // always search for name cause if "" it will return all the candidats
        if (query.get(NOM) == null || "".equals(query.get(NOM))) {
            sp = Specifications.where(nomLike(""));
        } else
            sp = Specifications.where(nomLike(query.get(NOM)));
        if (query.get(PRENOM) != null || !"".equals(query.get(PRENOM))) {
            sp = Specifications.where(sp).and(prenomLike(query.get(PRENOM)));
        }

        //search with specifications and page request
        PageRequest pageRequest = new PageRequest(page, size);
        return candidatDao.findAll(sp, pageRequest);
    }
}
