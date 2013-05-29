package com.bull.grh.service.metier;

import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

import javax.validation.Valid;
import java.util.List;

public interface ConvocationService {
    Integer getCountStartedTaskList();

    Integer getCountTaskList();

    List<CandidatureVO> loadStartedTaskList();

    List<CandidatureVO> loadTaskList();

    void completeConvocation(@Valid CandidatureVO candidature, @Valid EntretienVO entretien);

    void cancelConvocation(CandidatureVO candidature);

    void startConvocation(CandidatureVO candidature);
}
