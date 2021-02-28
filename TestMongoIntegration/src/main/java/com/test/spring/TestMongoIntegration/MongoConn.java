package com.test.spring.TestMongoIntegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UnwindOptions;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;


/**
 * Hello world!
 *
 */
public class MongoConn 
{
    public static void main( String[] args )
    {
    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://rpomal:Rishi123@cluster0.xdzgu.gcp.mongodb.net/sample_restaurants?retryWrites=true&w=majority"));
    	mongoClient.getDatabaseNames();
        System.out.println( "Available Databases::"+ mongoClient.getDatabaseNames());
        
        MongoDatabase  restaurant = mongoClient.getDatabase("sample_restaurants");
        System.out.println( "Available Collections::"+ restaurant.listCollectionNames().toString());
        
        MongoCollection restaurant_collection = restaurant.getCollection("restaurants");
        System.out.println(" Total Count :: "+restaurant_collection.count());
        //Match
        Bson mch = match(or(Arrays.asList(eq("cuisine", "Chinese"),and(eq("cuisine", "Indian"),eq("address.zipcode", "11218")))));

        //Sort
        List s = new ArrayList();
        s.add("cuisine");
		Bson srt = sort(Sorts.descending(s)) ;
		//Limit
		Bson lmt = limit(10);
		//Projection
		List<Bson> fields = new ArrayList<Bson>();
		fields.add(Projections.excludeId());
		//fields.add(Projections.include("address.building"));
		//fields.add(Projections.include("address.street"));
		//fields.add(Projections.include("address.zipcode"));
		//fields.add(Projections.include("cuisine"));
		//fields.add(Projections.include("restaurant_id"));
		fields.add(Projections.computed("cuisine", "$_id"));

		
		//Group
		Bson grp = group("$cuisine",Accumulators.sum("cuisine", "$cuisine") );
		

		Bson prjt = project(Projections.fields(fields));
        List pipeline = new ArrayList();
        pipeline.add(mch);
        //pipeline.add(grp);
       // pipeline.add(prjt);
        pipeline.add(srt);
        pipeline.add(lmt);

		List<Document> abc =  (List<Document>) restaurant_collection.aggregate(pipeline).into(new ArrayList());
		
		
        Iterator i = abc.iterator();
        int k =0;
        while(i.hasNext()){
        	k++;
        	System.out.println("Number::"+k+"::"+i.next().toString());
        }
    }
}
