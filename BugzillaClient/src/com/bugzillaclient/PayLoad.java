package com.bugzillaclient;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PayLoad {

	public PayLoad() {
		// TODO Auto-generated constructor stub
	}

 public static String getPayload(String r_url,String r_custom_workspace,String r_site_name,String r_shell_command) throws ParserConfigurationException,SAXException,IOException,XPathExpressionException {
	 
		 String payload = null;
		 String url = null;
		 String custom_workspace=null;
		 String site_name=null;
		 String shell_command=null;
		 File inputFile = new File("D:/JenkinsConfigFiles/CHICConfig.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
         dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         XPath xPath =  XPathFactory.newInstance().newXPath();
         String expression = "/project";
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                 doc, XPathConstants.NODESET);
         for (int i = 0; i < nodeList.getLength(); i++) {
             Node nNode = nodeList.item(i);
             System.out.println("\nCurrent Element :" + nNode.getNodeName());
             
             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element eElement =  (Element) nNode;
                 url=eElement.getElementsByTagName("remote").item(0).getTextContent();
                 System.out.println("URL: " 
                         + eElement
                         .getElementsByTagName("remote")
                         .item(0)
                         .getTextContent());
                 custom_workspace = eElement.getElementsByTagName("customWorkspace").item(0).getTextContent();
                 System.out.println("custom workspace: " 
                         + eElement
                         .getElementsByTagName("customWorkspace")
                         .item(0)
                         .getTextContent());
                 site_name = eElement.getElementsByTagName("siteName").item(0).getTextContent();
                 System.out.println("Site: " 
                         + eElement
                         .getElementsByTagName("siteName")
                         .item(0)
                         .getTextContent());
                 shell_command = eElement.getElementsByTagName("preScript").item(0).getTextContent();
                 System.out.println("command:" 
                         + eElement
                         .getElementsByTagName("preScript")
                         .item(0)
                         .getTextContent());
         }
             
             payload = FileUtils.readFileToString(inputFile, "UTF-8");
             payload=payload.replaceAll(url,r_url);
             payload=payload.replaceAll(custom_workspace,r_custom_workspace);
             payload=payload.replaceAll(site_name,r_site_name);
             payload=payload.replaceAll(shell_command,r_shell_command);
             System.out.println(payload);
       }
	return payload;
   }
 }
