package com.sportsapi.repository;

import com.sportsapi.entity.XmlArticleStub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface XmlArticleStubRepository  extends CrudRepository<XmlArticleStub, String>{

    List<XmlArticleStub> findByLeagueId(Integer leagueId);
}
