package com.bull.grh.repos.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.WebPage;

@Repository("webPageDao")
public interface WebPageDao extends JpaRepository<WebPage, Long>{

    public WebPage findByUrl(String url);
}
