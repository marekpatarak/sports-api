package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "xmlarticlestub")
@Getter
@Setter
public class XmlArticleStub {

    @Id
    String guid;
    String title;
    String teaserImg;
    String description;
    String link;
    String pubDate;
    String credit;
    Integer leagueId;

    public XmlArticleStub() {

    }

    @Override
    public String toString() {
        return "XmlArticleStub{" +
                "guid='" + guid + '\'' +
                ", title='" + title + '\'' +
                ", teaserImg='" + teaserImg + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", credit='" + credit + '\'' +
                ", leagueId=" + leagueId +
                '}';
    }
}
