import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

public class MongoConnect
{
    private MongoCollection<Document> collection;
    private JsonWriterSettings writerSettings;

    public MongoConnect(String databaseName, String collectionName)
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
        writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
    }

    public MongoCollection<Document> getCollection()
    {
        return collection;
    }

    public void create(int id)
    {
        Document document = new Document();
        document.append("id", id);
        if (read(id) == null)
            collection.insertOne(document);
        else
            throw new IllegalArgumentException("Document with id: " + id + " already exists.");
    }

    private Document read(int id)
    {
        return collection.find(Filters.eq("id", id)).first();
    }

    public Document readDocument(int id)
    {
        Document document = read(id);
        if (document != null)
            return document;
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public String readJson(int id)
    {
        return readDocument(id).toJson(writerSettings);
    }

    public List<Document> readAllDocuments()
    {
        List<Document> documents = new ArrayList<>();
        try (MongoCursor cursor = collection.find().iterator())
        {
            while (cursor.hasNext())
                documents.add((Document) cursor.next());
        }
        return documents;
    }

    public List<String> readAllJsons()
    {
        List<String> documents = new ArrayList<>();
        try (MongoCursor cursor = collection.find().iterator())
        {
            while (cursor.hasNext())
                documents.add(((Document) cursor.next()).toJson(writerSettings));
        }
        return documents;
    }

    // region update
    public void updateName(int id, String name)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("name", name)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateHostId(int id, int hostId)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("host_id", hostId)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateHostName(int id, String hostName)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("host_name", hostName)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateNeighbourhoodGroup(int id, String neighbourhoodGroup)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("neighbourhood_group", neighbourhoodGroup)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");

    }

    public void updateNeighbourhood(int id, String neighbourhood)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("neighbourhood", neighbourhood)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateLatitude(int id, float latitude)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("latitude", latitude)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateLongtitude(int id, float longtitude)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("longtitude", longtitude)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateRoomType(int id, String roomType)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("room_type", roomType)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updatePrice(int id, float price)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("price", price)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateMinimumNights(int id, int minimumNights)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("minimum_nights", minimumNights)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateLastReview(int id, int year, int month, int day)
    {
        String date = year + "-" + month + "-" + day;
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("last_review", date)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateReviewsPerMonth(int id, int reviewsPerMonth)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("reviews_per_month", reviewsPerMonth)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateCalculatedHostListingsCount(int id, int calculatedHostListingsCount)
    {
        if(read(id) != null)
             collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("calculated_host_listings_count", calculatedHostListingsCount)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }

    public void updateAvailability365(int id, int availability365)
    {
        if(read(id) != null)
            collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("availability_365", availability365)));
        else
            throw new IllegalArgumentException("Document with id: " + id + " does not exist.");
    }
    //endregion

    public boolean delete(int id)
    {
        return collection.deleteOne(Filters.eq("id", id)).getDeletedCount() > 0;
    }
}
