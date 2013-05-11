package com.bull.grh.service.utils.impl;

import com.bull.grh.domaine.Authority;
import com.bull.grh.domaine.EmailTemplate;
import com.bull.grh.domaine.Personne;
import com.bull.grh.domaine.types.EmailType;
import com.bull.grh.domaine.types.Statut;
import com.bull.grh.repos.admin.AuthorityDao;
import com.bull.grh.repos.admin.EmailTemplateDao;
import com.bull.grh.repos.admin.PersonneDao;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationInitializator {
    @Inject
    private EmailTemplateDao emailTemplateDao;
    @Inject
    private PersonneDao personneDao;
    @Inject
    private AuthorityDao authorityDao;

    @PostConstruct
    public void init() {
        EmailTemplate e = emailTemplateDao.findByCode(EmailType.REGISTRATION);
        if (e == null) {
            //init emailTemplate table
            EmailTemplate emailTemplate = new EmailTemplate();

            emailTemplate.setId(1L);
            emailTemplate.setCode(EmailType.REGISTRATION);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br /> <br />Cet email vous est envoyé suite à votre inscription à l\'espace de recrutement de simu Maroc.<br />Cliquez sur le lien ci-dessous pour activer votre compte <br />$link</body></html>");
            emailTemplate.setSubject("Confirmation Inscription simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(2L);
            emailTemplate.setCode(EmailType.ACTIVATION);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br /> <br />Votre compte chez simu Maroc est activé.<br />Votre login est $login <br />Cordialement.</body></html>");
            emailTemplate.setSubject("Compte activé simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(3L);
            emailTemplate.setCode(EmailType.RELANCE);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br /> <br />Cet email vous est envoyé suite à votre inscription à l\'espace de recrutement de simu Maroc.<br />Cliquez sur le lien ci-dessous pour activer votre compte <br />$link</body></html>");
            emailTemplate.setSubject("Confirmation Inscription simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(4L);
            emailTemplate.setCode(EmailType.CALL_APPOINTMENT);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br /> <br />Suite à notre conversation de ce jour le $today, merci de trouver ci-après, les informations relatives à votre entretien chez simu Maroc : <br /> <br />Date	: $date <br />Heure	: $heure <br />Contact sur place	: $contact <br />Adresse	: $adresse <br />Poste ciblé	: $poste <br /> <br />$dossier <br /></body></html>");
            emailTemplate.setSubject("Convocation Entretien simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(5L);
            emailTemplate.setCode(EmailType.CANDIDATURE);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br /> <br />Nous avons le plaisir de vous annoncer que suite à notre entretien, votre candidature a été retenue pour le poste de : $poste <br />Nous vous prions de trouver ci-joint la liste des pièces à fournir pour la constitution de votre dossier personnel afin d\'intégrer l\'équipe à la date prévue. <br />Dans cette attente, veuillez agréer, l\'expression de nos salutations distinguées. <br />l\'équipe RH <br />simu Maroc </body></html>");
            emailTemplate.setSubject("Réponse Candidature simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(6L);
            emailTemplate.setCode(EmailType.CALL_INTERNSHIP);
            emailTemplate.setMessage("<html><body>Bonjour, <br />Nous avons le plaisir de vous annoncer que suite à notre entretien, votre candidature a été retenue pour le poste de : $poste <br />Nous vous prions de trouver ci-joint la liste des pièces à fournir pour la constitution de votre dossier personnel afin d\'intégrer l\'équipe à la date prévue. <br />Dans cette attente, veuillez agréer, l\'expression de nos salutations distinguées. <br />l\'équipe RH <br />simu Maroc </body></html>");
            emailTemplate.setSubject("Réponse Candidature simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(7L);
            emailTemplate.setCode(EmailType.REJECTED_AFTER_APP);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br />Suite à votre entretien, j\'ai le regret de vous informer que votre candidature n\'a pas été retenue pour le poste $poste. <br />Ayant néanmoins pu apprécier vos qualités au cours de cet entretien, je vous souhaite de trouver rapidement un poste vous convenant. <br />Veuillez agréer, l\'expression de nos salutations distinguées. <br />Cordialement, <br />L\'équipe Recrutement <br />simu Maroc <br /></body></html>");
            emailTemplate.setSubject("Réponse Candidature simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(8L);
            emailTemplate.setCode(EmailType.CANDIDATURE);
            emailTemplate.setMessage("<html><body>Bonjour, <br />Nous accusons bonne réception de votre candidature et vous remercions de l\'intérêt que vous manifestez à simu MAROC. <br />Nous allons étudier votre dossier avec attention en tenant compte de vos aspirations et de votre expérience. <br />Nous sommes actuellement en phase de réception des candidatures et ne manquerons pas de vous tenir informé de la suite dans les meilleurs délais. <br />A défaut de réponse de notre part dans un délai d\'un mois, veuillez considérer que nous n\'avons pu donner une suite favorable à votre candidature. <br />Toutefois, sauf avis contraire de votre part, nous nous permettons de conserver votre dossier et ne manquerons pas de vous contacter si un poste correspondant mieux à votre profil venait à se libérer. <br />Veuillez agréer, l\'expression de notre sincère considération. <br />L\'équipe RH <br />simu MAROC <br /></body></html>");
            emailTemplate.setSubject("Traitement candidature simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);

            emailTemplate.setId(9L);
            emailTemplate.setCode(EmailType.PASSWORD_FORGOT);
            emailTemplate.setMessage("<html><body>Bonjour $firstname $lastname, <br/><br/>Suite à votre demande, nous vous envoyons un mot de passe temporaire que vous devez modifier dès que vous vous connectez à votre espace simu-recrutement. <br/><br/>Nouveau mot de passe : $npass <br/><br/>Cordialement,<br/>simu Maroc</body></html>");
            emailTemplate.setSubject("Mot de passe oublié - simu Maroc");
            emailTemplate.setCreated(new Date());
            emailTemplateDao.save(emailTemplate);
        }

        List<Authority> a = authorityDao.findAll();
        if (a == null || a.isEmpty()) {
            Personne personne = new Personne();
            personne.setEmail("kecha.mohamed@gmail.com");
            personne.setFonction("developpeur");
            personne.setNom("Kecha");
            personne.setPrenom("Mohamed");
            personne.setStatut(Statut.ACTIF);

            Authority authority = new Authority();
            authority.setRole("ROLE_RH");
            authority.setLevel(1);
            authority.setPosition(1);
            authorityDao.save(authority);

            List<Authority> myAuthList = new ArrayList<Authority>();
            myAuthList.add(authority);
            personne.setUsername("testRH");
            personne.setAuthorities(myAuthList);
            personneDao.save(personne);

            authority.setRole("ROLE_CE");
            myAuthList.clear();
            myAuthList.add(authority);
            personne.setUsername("testCE");
            personne.setAuthorities(myAuthList);
            personneDao.save(personne);

            authority.setRole("ROLE_OP");
            myAuthList.clear();
            myAuthList.add(authority);
            personne.setUsername("testOP");
            personne.setAuthorities(myAuthList);
            personneDao.save(personne);
        }
    }
}
