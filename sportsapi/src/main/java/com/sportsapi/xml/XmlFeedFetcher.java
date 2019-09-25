package com.sportsapi.xml;

import java.io.File;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.Scanner;
import java.util.logging.*;

 import org.w3c.dom.Document;

public class XmlFeedFetcher {
	
	private static Logger logger = Logger.getLogger(XmlFeedFetcher.class.getCanonicalName());

	public static Document fetchFeedFromUrl (String urlString) {
		
		try{

			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());

			doc.getDocumentElement().normalize();
			
			return doc;
		} catch (Exception e) 
		{
			logger.log(Level.SEVERE, "Couldnt fetch feed from URL" + e.getMessage());
		}
		
		return null;
	}

	@Deprecated
	public static Document fetchFeedFromFile (String path) {

		try{
			File file = new File(path);
			Scanner in = new Scanner(file);

			StringBuilder str = new StringBuilder();
			while(in.hasNextLine()) {
				str.append(in.nextLine());
			}

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			return doc;
		} catch (Exception e)
		{
			logger.log(Level.SEVERE, "Couldnt fetch feed from file" + e.getMessage());
		}

		return null;
	}
}
