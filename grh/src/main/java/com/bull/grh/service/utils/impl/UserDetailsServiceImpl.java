package com.bull.grh.service.utils.impl;

import com.bull.grh.domaine.Authority;
import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Personne;
import com.bull.grh.repos.admin.PersonneDao;
import com.bull.grh.repos.metier.CandidatDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private PersonneDao personneDao;

    @Inject
    private CandidatDao candidatDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Personne personne = personneDao.findByUsername(username);
            Candidat candidat = candidatDao.findByUsername(username);

            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            if (personne != null) {
                return new User(personne.getUsername(), personne.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked, getGrantedAuthorities(personne.getAuthorities()));
            } else {
                return new User(candidat.getUsername(), candidat.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked, getGrantedAuthorities(candidat.getAuthorities()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<Authority> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Authority role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}