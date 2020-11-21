package com.solr.poc.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrClientFactoryBean;

@Configuration
@EnableSolrRepositories(value = "com.solr.poc.repositories", schemaCreationSupport = true)
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	private static final String PROPERTY_NAME_SOLR_SERVER_URL = "spring.data.solr.host";
	
	@Resource
    private Environment environment;

    @Bean
    public HttpSolrClientFactoryBean solrServerFactoryBean() {
    	HttpSolrClientFactoryBean factoryBean = new HttpSolrClientFactoryBean();
    	factoryBean.setUrl(PROPERTY_NAME_SOLR_SERVER_URL);
        return factoryBean;
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        return new SolrTemplate(solrServerFactoryBean().getObject());
    }
}