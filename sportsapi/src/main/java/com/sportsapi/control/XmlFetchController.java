package com.sportsapi.control;

import com.sportsapi.repository.LeagueRepository;
import com.sportsapi.repository.XmlArticleStubRepository;
import com.sportsapi.service.XmlFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/xmlfetch")
public class XmlFetchController {

    @Autowired
    XmlFetchService xmlFetchService;

    @Autowired
    XmlArticleStubRepository xmlArticleStubRepository;

    @Autowired
    LeagueRepository leagueRepository;

    @GetMapping(path = "/league/{param}")
    public String fetchXmlArticleStubs(@PathVariable("param") String param) {

        if(param.equals("all")) {
            leagueRepository.findAll().forEach(x -> xmlFetchService.fetchXmlArticleStubsByLeagueId(String.valueOf(x.getLeagueId())));
        } else {
            xmlFetchService.fetchXmlArticleStubsByLeagueId(param);
        }

        return "success";
    }

}
