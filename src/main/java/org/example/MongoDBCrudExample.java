package org.example;

import com.mongodb.*;
import org.example.model.User;

import java.net.UnknownHostException;

public class MongoDBCrudExample {
    public static void main(String... args) throws UnknownHostException {
        User user = new User(104, "Rohan", "MGR", true);

        //implementation given below
        DBObject doc = createDBObject(user);

        //use try catch
        MongoClient mongo = new MongoClient("localhost", 27017);


        DB db = mongo.getDB("testdb");
        //DBCollection coll = db.createCollection("users", doc);
        DBCollection coll = db.getCollection("users");

//        //Create User
        //comment out below line while using others (read or update or delete)
        WriteResult result = coll.insert(doc);
        System.out.println(result.getUpsertedId());
        System.out.println(result.getN());

        //Read User after Creation
        DBObject obj = BasicDBObjectBuilder.start().add("_id", user.getId()).get();
        DBCursor cursor = coll.find(obj);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }


        //Update
        user.setName("Rohan Sharma");
        doc = createDBObject(user);
        coll.update(obj, doc);

        //Read user after Update
        System.out.println("After Update:: ");
        DBObject obj1 = BasicDBObjectBuilder.start().add("_id", user.getId()).get();
        DBCursor cursor1 = coll.find(obj1);
        while (cursor1.hasNext()) {
            System.out.println(cursor1.next());
        }


        //Add remove method
    }


    private static DBObject createDBObject(User user){

        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        docBuilder.append("_id", user.getId());
        docBuilder.append("name", user.getName());
        docBuilder.append("role", user.getRole());
        docBuilder.append("isEmployee", user.isEmployee());

        return docBuilder.get();
    }


}
