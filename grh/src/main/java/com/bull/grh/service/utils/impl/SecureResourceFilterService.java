package com.bull.grh.service.utils.impl;

import com.bull.grh.domaine.Authority;
import com.bull.grh.domaine.WebPage;
import com.bull.grh.repos.utils.WebPageDao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("secureResourceFilterService")
public class SecureResourceFilterService implements FilterInvocationSecurityMetadataSource {

    @Inject
    private WebPageDao webPageDao;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {

        FilterInvocation filterinvocation = (FilterInvocation) filter;
        String url = filterinvocation.getRequestUrl();
        List<String> list = getAuthorities(url);
        String[] roles = list.toArray(new String[list.size()]);
        if (roles.length == 0) {
            return null;
        }
        return SecurityConfig.createList(roles);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    @Cacheable(value = "urlCache", key = "url")
    public List<String> getAuthorities(String url) {
        List<String> chaine = new ArrayList<String>();
        WebPage page = webPageDao.findByUrl(url);

        if (page == null) {
            return chaine;
        }
        for (Authority authority : page.getAuthorities()) {
            chaine.add(authority.getRole());
        }
        return chaine;
    }

}
