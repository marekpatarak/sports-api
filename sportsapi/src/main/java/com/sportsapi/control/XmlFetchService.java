package com.sportsapi.control;

import com.sportsapi.entity.League;
import com.sportsapi.entity.XmlArticleStub;
import com.sportsapi.repository.LeagueRepository;
import com.sportsapi.repository.XmlArticleStubRepository;
import com.sportsapi.xml.XmlFeedFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class XmlFetchService {

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    XmlArticleStubRepository xmlArticleStubRepository;

    private static Logger logger = Logger.getLogger(XmlFetchService.class.getCanonicalName());


    public void fetchXmlArticleStubsByLeagueId(String leagueId) {

        League league = leagueRepository.findById(Integer.parseInt(leagueId)).get();

        Document feedDocument = XmlFeedFetcher.fetchFeedFromUrl(league.getXmlFeedUrl());

        persistXmlArticleStubs(feedDocument, league.getLeagueId());
    }

    private void persistXmlArticleStubs(Document document, Integer leagueId) {

        NodeList nodeList = document.getDocumentElement().getElementsByTagName("item");

        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node nNode = nodeList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String guid = eElement.getElementsByTagName("guid").item(0).getTextContent();
                String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                String desc = eElement.getElementsByTagName("description").item(0).getTextContent();
                String link = eElement.getElementsByTagName("link").item(0).getTextContent();
                String pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();

                String teaserImg = null;
                Node enclosureNode = eElement.getElementsByTagName("enclosure").item(0);
                if(!(enclosureNode == null)) {
                    teaserImg = enclosureNode.getAttributes().getNamedItem("url").getTextContent();
                }

                String credit = null;
                Node creditNode = eElement.getElementsByTagName("dc:creator").item(0);
                if(!(creditNode == null)) {
                    credit = creditNode.getTextContent();
                }

                XmlArticleStub xmlArticleStub = new XmlArticleStub();
                xmlArticleStub.setGuid(guid);
                xmlArticleStub.setTitle(title);
                xmlArticleStub.setDescription(desc);
                xmlArticleStub.setLink(link);
                xmlArticleStub.setPubDate(pubDate);
                xmlArticleStub.setTeaserImg(teaserImg);
                xmlArticleStub.setCredit(credit);
                xmlArticleStub.setLeagueId(leagueId);

                try {
                    xmlArticleStubRepository.save(xmlArticleStub);

                } catch (DataIntegrityViolationException e) {
                    xmlArticleStub.setDescription("");
                    xmlArticleStubRepository.save(xmlArticleStub);

                }

                logger.log(Level.INFO, "XmlArticleStub " + title + " persisted ");

            }

        }
    }

}


