package com.bull.grh.domaine.types;

public interface ProcessConst {

    /**
     * ID of the registration process it has a value of PROCESS_INSCRIPTION
     */
    static final String PROCESS_ID_INSCRIPTION = "PROCESS_INSCRIPTION";
    /**
     * ID of the demand process it has a value of PROCESS_DEMANDE_RECRUTEMENT
     */
    static final String PROCESS_ID_DEMANDE_RECRUTEMENT = "PROCESS_DEMANDE_RECRUTEMENT";
    /**
     * ID of the calling process it has a value of PROCESS_CONVOCATION
     */
    static final String PROCESS_ID_CONVOCATION = "PROCESS_CONVOCATION";
    /**
     * ID of the appointment process it has a value of PROCESS_ENTRETIEN
     */
    static final String PROCESS_ID_ENTRETIEN = "PROCESS_ENTRETIEN";

    // Register Process variables
    static final String REGISTER_PROCESS_CANDIDAT = "candidat";
    static final String REGISTER_PROCESS_TOKEN = "token";

    // Global variables
    static final String PROCESS_GLOBAL_MAIL_SERVICE = "emailService";
    static final String PROCESS_GLOBAL_DAO_CANDIDAT = "candidatDao";

    // Convocation Process variables
    static final String CONVOCATION_ENTRETIEN = "entretien";
    static final String CONVOCATION_CANDIDATURE = "candidature";
    static final String CONVOCATION_DISPONIBLE = "disponible";
    static final String CONVOCATION_TASK_CONVOCATION = "convocationTask";

    // Entretien Process variables
    static final String APPOINTMENT_USERNAME_CE = "usernameCE";
    static final String APPOINTMENT_ENTRETIEN = "entretien";
    static final String APPOINTMENT_TASK_CE = "evaluationTask";
    static final String APPOINTMENT_TASK_RH = "evaluationRH";

    // Demande Process variables
    static final String DEMANDE_TASK_OP_CHOICE = "choixConvocationTask";
    static final String DEMANDE_DEMANDE = "demande";
    static final String DEMANDE_CANDIDATURE_LIST = "candidatureList";
    static final String DEMANDE_TASK_RH_VALIDATION = "validationDemandeTask";
    static final String DEMANDE_TASK_OP_INIT = "demandeInitTask";

}