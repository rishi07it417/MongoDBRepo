package com.test.spring.TestMongoIntegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UnwindOptions;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.out;



import static com.mongodb.client.model.Filters.*;


public class TestInsert {
	 public static void main( String[] args )
	    {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://rpomal:Rishi123@cluster0.xdzgu.gcp.mongodb.net/sample_restaurants?retryWrites=true&w=majority"));
	    	mongoClient.getDatabaseNames();
	        System.out.println( "Available Databases::"+ mongoClient.getDatabaseNames());
	        
	        MongoDatabase  restaurant = mongoClient.getDatabase("sample_restaurants");
	        System.out.println( "Available Collections::"+ restaurant.listCollectionNames().toString());
	        
	        MongoCollection restaurant_collection = restaurant.getCollection("my_restaurant_collection");
	        
	        List<Document> documents = new ArrayList();
	        Document d = new Document();
	        d.append("restaurant_id", "1");
	        d.append("Street", "Mochi");
	        d.append("Building", "SS");
	        d.append("Grade", "B");
	        documents.add(d);
	        
	        d = new Document();
	        d.append("restaurant_id", "2");
	        d.append("Street", "Ghuma");
	        d.append("Building", "Shela Road");
	        d.append("Grade", "A");       
	        documents.add(d);
 
	        restaurant_collection.insertMany(documents);

	        
	        List<Document> doc =  (List<Document>) restaurant_collection.find().into(new ArrayList());
			System.out.println("Total Document count::::"+doc.size());
			
	        Iterator i = doc.iterator();
	        int k =0;
	        while(i.hasNext()){
	        	k++;
	        	System.out.println("Number::"+k+"::"+i.next().toString());
	        }
	      
	       	        
	    }
}
