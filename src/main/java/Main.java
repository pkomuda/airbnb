import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.opencsv.CSVReader;
import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;


public class Main {
    public static void main(String[] args) {
        //MongoInit mongoInit = new MongoInit();
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader("AB_NYC_2019.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // MongoClient mongoClient = new MongoClient("1", 27017);


        MongoConnect mongo = new MongoConnect("nbd", "airbnb");
        mongo.mongoExport(records);
         /*   int id = 29;
            try {
                mongo.create(id);
            } catch (IllegalArgumentException e) {
                System.out.println("Document with id: " + id + " was not created, because it already exists");
            }
            System.out.println(mongo.readJson(id));
            mongo.updateName(id, "test");
            System.out.println(mongo.readJson(id));
            System.out.println(mongo.delete(123));*/
        }
    }
