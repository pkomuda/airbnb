import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.opencsv.CSVReader;
import java.util.ArrayList;
import com.opencsv.CSVReaderBuilder;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MongoInit {
    public MongoInit() {
        MongoClient mongoClient = new MongoClient("1", 27017);
    }

}


