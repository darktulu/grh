package com.bull.grh.view.metier;

import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Scope("view")
@Component
public class ConvocationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private ConvocationService convocationService;

    private List<CandidatureVO> convocations, startedconvocations;
    private CandidatureVO convocation = new CandidatureVO();
    private EntretienVO entretien = new EntretienVO();

    public void proceedConvocation() {
        convocationService.startConvocation(convocation);
        convocations.remove(convocation);
    }

    public void cancelConvocation() {
        convocationService.cancelConvocation(convocation);
        convocations.remove(convocation);
    }

    public void completeConvocation() {
        convocationService.completeConvocation(convocation, entretien);
        convocations.remove(convocation);
    }

    public ConvocationService getConvocationService() {
        return convocationService;
    }

    public void setConvocationService(ConvocationService convocationService) {
        this.convocationService = convocationService;
    }

    public List<CandidatureVO> getConvocations() {
        if (convocations == null || convocations.isEmpty()) {
            convocations = convocationService.loadTaskList();
        }
        return convocations;
    }

    public void setConvocations(List<CandidatureVO> convocations) {
        this.convocations = convocations;
    }

    public CandidatureVO getConvocation() {
        return convocation;
    }

    public void setConvocation(CandidatureVO convocation) {
        this.convocation = convocation;
    }

    public List<CandidatureVO> getStartedconvocations() {
        if (startedconvocations == null || startedconvocations.isEmpty()) {
            startedconvocations = convocationService.loadStartedTaskList();
        }
        return startedconvocations;
    }

    public void setStartedconvocations(List<CandidatureVO> startedconvocations) {
        this.startedconvocations = startedconvocations;
    }

    public EntretienVO getEntretien() {
        return entretien;
    }

    public void setEntretien(EntretienVO entretien) {
        this.entretien = entretien;
    }

    public void initEntretien() {
        entretien = new EntretienVO();
    }

    public void initConvocation() {
        convocation = new CandidatureVO();
    }

}
