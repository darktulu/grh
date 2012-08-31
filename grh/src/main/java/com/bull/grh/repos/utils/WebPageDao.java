package com.bull.grh.repos.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bull.grh.domaine.WebPage;

public interface WebPageDao extends JpaRepository<WebPage, Long>{

    public WebPage findByUrl(String url);
}
