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
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Sorts;


import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Aggregates.skip;


public class TestGrouping {
	 public static void main( String[] args )
	    {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://rpomal:Rishi123@cluster0.xdzgu.gcp.mongodb.net/sample_restaurants?retryWrites=true&w=majority"));
	    	mongoClient.getDatabaseNames();
	        System.out.println( "Available Databases::"+ mongoClient.getDatabaseNames());
	        
	        MongoDatabase  restaurant = mongoClient.getDatabase("sample_restaurants");
	        System.out.println( "Available Collections::"+ restaurant.listCollectionNames().toString());
	        
	        MongoCollection my_restaurant_collection = restaurant.getCollection("restaurants");
	        
			//Group
			Bson grp = group("$cuisine",Accumulators.sum("cuisine", 1));
			
			//Sort
	        List s = new ArrayList();
	        s.add("_id");
			Bson srt = sort(Sorts.ascending(s)) ;
			
			//Limit
			Bson lmt = limit(30);
			
			//Skit
			Bson skp = skip(5);
	        
	        //pipeline
	        List<Bson> pipeline = new ArrayList<Bson>();
	        pipeline.add(grp);
	        pipeline.add(srt);
	        pipeline.add(lmt);
	        pipeline.add(skp);



	        
	        List<Document> doc =  (List<Document>) my_restaurant_collection.aggregate(pipeline).into(new ArrayList());
			System.out.println("Total Document count::::"+doc.size());
			
	        Iterator i = doc.iterator();
	        int k =0;
	        while(i.hasNext()){
	        	k++;
	        	System.out.println("Number::"+k+"::"+i.next().toString());
	        }
	      
	       	        
	    }
}
