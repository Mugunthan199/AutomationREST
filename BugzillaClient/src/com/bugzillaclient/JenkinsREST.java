package com.bugzillaclient;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.bugzillaclient.PayLoad;

public class JenkinsREST {

	public JenkinsREST() {
		// TODO Auto-generated constructor stub
	}
public static void main(String arg[]) throws ParserConfigurationException,SAXException,IOException,XPathExpressionException {
	String name = "admin";
	String password = "admin";
	String authString = name + ":" + password;
	System.out.println("auth string: " + authString);
	byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
	String authStringEnc = new String(authEncBytes);
	System.out.println("Base64 encoded auth string: " + authStringEnc);
	String payload=com.bugzillaclient.PayLoad.getPayload(arg[0],arg[1],arg[2],arg[3]);
	URL createJob = new URL("http://192.168.6.29:9090/createItem?name="+arg[4]);
	HttpURLConnection conn = (HttpURLConnection) createJob.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type","application/xml");
	conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
	OutputStream os = (OutputStream) conn.getOutputStream();
	os.write(payload.getBytes());
	os.flush();
	if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	}
	BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
	String output;
	while ((output = br.readLine()) != null) {
	System.out.println(output);  
	}
}
}
