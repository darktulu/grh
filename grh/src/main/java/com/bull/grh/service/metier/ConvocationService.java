package com.bull.grh.service.metier;

import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

import javax.validation.Valid;
import java.util.List;

public interface ConvocationService {
    public Integer getCountStartedTaskList();

    public Integer getCountTaskList();

    public List<CandidatureVO> loadStartedTaskList();

    public List<CandidatureVO> loadTaskList();

    public void completeConvocation(@Valid CandidatureVO candidature, @Valid EntretienVO entretien);

    public void cancelConvocation(CandidatureVO candidature);

    public void startConvocation(CandidatureVO candidature);

}
