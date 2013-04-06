package com.bull.grh.repos.utils;

import com.bull.grh.domaine.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebPageDao extends JpaRepository<WebPage, Long> {
    WebPage findByUrl(String url);
}
