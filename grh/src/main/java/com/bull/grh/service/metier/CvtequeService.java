package com.bull.grh.service.metier;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.view.metier.vo.CandidatVO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CvtequeService {
    List<CandidatVO> loadAllCandidat(int page, int size);

    List<CandidatVO> loadCandidats(Map<String, String> query);

    List<CandidatVO> loadCandidats(int page, int size, Map<String, String> query);

    Page<Candidat> loadPageCandidats(int page, int size, Map<String, String> query);
}
