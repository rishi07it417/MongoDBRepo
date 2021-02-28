package com.test.spring.TestMongoIntegration;

import java.util.ArrayList;
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

import static com.mongodb.client.model.Filters.*;


public class TestMatchAggWithLike {
	 public static void main( String[] args )
	    {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://rpomal:Rishi123@cluster0.xdzgu.gcp.mongodb.net/sample_restaurants?retryWrites=true&w=majority"));
	    	mongoClient.getDatabaseNames();
	        System.out.println( "Available Databases::"+ mongoClient.getDatabaseNames());
	        
	        MongoDatabase  restaurant = mongoClient.getDatabase("sample_restaurants");
	        System.out.println( "Available Collections::"+ restaurant.listCollectionNames().toString());
	        
	        MongoCollection restaurant_collection = restaurant.getCollection("restaurants");
	        System.out.println(" Total Count :: "+restaurant_collection.count());
	        
	        Bson filt = regex("cuisine", "Indian|Chinese");
	               
		
	        System.out.println("dddd::::" +  restaurant_collection.find(filt).toString());
	        
	        		
	        List<Document> abc =  (List<Document>) restaurant_collection.find(filt).into(new ArrayList());
			System.out.println("count::::"+abc.size());
			
	        Iterator i = abc.iterator();
	        int k =0;
	        while(i.hasNext()){
	        	k++;
	        	System.out.println("Number::"+k+"::"+i.next().toString());
	        }
	      
	       	        
	    }
}
