package com.bugzillaclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
//import org.omg.CORBA.portable.OutputStream;
import java.io.OutputStream;

import com.google.common.net.MediaType;
import com.model.Comment;

public class ClientPost {
    public static void main(String[] args) throws Exception {
    	try {
    		//service url for create bug
          
    		//service url for comment bug
    		URL commentbug = new URL("http://192.168.6.35:80/rest.cgi/bug");
    		HttpURLConnection conn2 = (HttpURLConnection) commentbug.openConnection();
    		//headers for create bug
    		
    		//headers for comment bug
    		conn2.setDoOutput(true);
    		conn2.setRequestMethod("POST");
    		conn2.setRequestProperty("Content-Type","application/json");
    		//Payload for comment bug
            String createbugrequest="{\"product\":\"Cloud_Infra\",\"component\":\"SVN\",\"version\":\"unspecified\",\"summary\":\"Request for SVN Access from eclipse with comment\",\"token\":\"1-p3HkZUup7k\",\"op_sys\":\"All\",\"rep_platform\":\"All\"}";
            
    		OutputStream os = (OutputStream) conn2.getOutputStream();
    		os.write(createbugrequest.getBytes());
    		os.flush();

    		if (conn2.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ conn2.getResponseCode());
    		}
    		
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				(conn2.getInputStream())));
    		String output;
    		StringBuffer stringBuffer = new StringBuffer();
    		//"Output from Server .... 
    		while ((output = br.readLine()) != null) {
    		  stringBuffer.append(output);
    		}
    		String response=stringBuffer.toString();
    		
    		Pattern pattern = Pattern.compile("(\\{.*\\})");
    		Matcher matcher = pattern.matcher(response);
    		String ticket_no="";
    		if (matcher.find())
    		{
    		    ticket_no=matcher.group(1);
    		}
    		char tickt[]=new char[100];
    		String tktno[]=ticket_no.split(":");
    		String tcket=tktno[1];
    		int len=(tcket.length())-1;
    		String ticket=tcket.substring(1,len);
    		System.out.println(ticket);
    		conn2.disconnect();
    		
    		URL createbug = new URL("http://192.168.6.35:80/rest.cgi/bug/"+ticket+"/"+"comment");
    		HttpURLConnection conn = (HttpURLConnection) createbug.openConnection();
    		
    		conn.setDoOutput(true);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type", "application/json");
    
    		String commentrequest ="{\"comment\":"+"\""+args[0]+"\""+","+"\"is_private\":false,\"token\":\"1-p3HkZUup7k\"}";
            
    		OutputStream os2 = (OutputStream) conn.getOutputStream();
    		os2.write(commentrequest.getBytes());
    		os2.flush();

    		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ conn.getResponseCode());
    		}
    		
    		BufferedReader br2 = new BufferedReader(new InputStreamReader(
    				(conn.getInputStream())));
    		String output2;
    		//StringBuffer stringBuffer2 = new StringBuffer();
    		//"Output from Server .... 
    		while ((output2 = br2.readLine()) != null) {
    		System.out.println(output2);  
    		}

    	  } catch (MalformedURLException e) {

    		e.printStackTrace();

    	  } catch (IOException e) {

    		e.printStackTrace();

    	 }
    }
}