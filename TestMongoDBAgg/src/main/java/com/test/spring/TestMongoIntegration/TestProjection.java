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


public class TestProjection {
	 public static void main( String[] args )
	    {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://rpomal:Rishi123@cluster0.xdzgu.gcp.mongodb.net/sample_restaurants?retryWrites=true&w=majority"));
	    	mongoClient.getDatabaseNames();
	        System.out.println( "Available Databases::"+ mongoClient.getDatabaseNames());
	        
	        MongoDatabase  restaurant = mongoClient.getDatabase("sample_restaurants");
	        System.out.println( "Available Collections::"+ restaurant.listCollectionNames().toString());
	        
	        MongoCollection restaurant_collection = restaurant.getCollection("restaurants");
	        
	        //match
	        Bson mch = match(and(eq("cuisine", "Indian"),eq("address.zipcode", "11218")));
	        
	        //unwind
	        Bson unwnd = unwind("$grades");
	        
	        //Projections
	        List<Bson> fields = new ArrayList<Bson>();
	        fields.add(Projections.excludeId());
	        fields.add(Projections.computed("Street","$address.street"));
	        fields.add(Projections.computed("Building","$address.building"));
	        fields.add(Projections.computed("Grade","$grades.grade"));
	        fields.add(Projections.include("restaurant_id"));       
			Bson projt = project(Projections.fields(fields));
			
			//out
			Bson ot = out("my_restaurant_collection");
	        
	        //pipeline
	        List<Bson> pipeline = new ArrayList<Bson>();
	        pipeline.add(mch);
	        pipeline.add(unwnd);
	        pipeline.add(projt);
	       // pipeline.add(ot); //to export output in new collection

	        
	        List<Document> doc =  (List<Document>) restaurant_collection.aggregate(pipeline).into(new ArrayList());
			System.out.println("Total Document count::::"+doc.size());
			
	        Iterator i = doc.iterator();
	        int k =0;
	        while(i.hasNext()){
	        	k++;
	        	System.out.println("Number::"+k+"::"+i.next().toString());
	        }
	      
	       	        
	    }
}
