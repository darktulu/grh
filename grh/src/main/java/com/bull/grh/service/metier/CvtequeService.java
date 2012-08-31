package com.bull.grh.service.metier;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.view.metier.vo.CandidatVO;

public interface CvtequeService {

    public List<CandidatVO> loadAllCandidat(int page, int size);

    public List<CandidatVO> loadCandidats(Map<String, String> query);

    public List<CandidatVO> loadCandidats(int page, int size, Map<String, String> query);

    public Page<Candidat> loadPageCandidats(int page, int size, Map<String, String> query);

}
