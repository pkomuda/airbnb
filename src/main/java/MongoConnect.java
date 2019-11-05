import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoConnect
{
    private MongoCollection<Document> collection;

    public MongoConnect(String databaseName, String collectionName)
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection()
    {
        return collection;
    }

    public void create(int id)
    {
        Document document = new Document();
        document.append("_id", new ObjectId())
                .append("id", id);
        if (read(id) == null)
            collection.insertOne(document);
        else
            throw new IllegalArgumentException("Document with id: " + id + " already exists.");
    }

    public Document read(int id)
    {
        return collection.find(Filters.eq("id", id)).first();
    }

    public List<Document> readAll()
    {
        List<Document> documents = new ArrayList<>();
        try (MongoCursor cursor = collection.find().iterator())
        {
            while (cursor.hasNext())
                documents.add((Document) cursor.next());
        }
        return documents;
    }

    // region update
    public void updateName(int id, String name)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("name", name)));
    }

    public void updateHostId(int id, int hostId)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("host_id", hostId)));
    }

    public void updateHostName(int id, String hostName)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("host_name", hostName)));
    }

    public void updateNeighbourhoodGroup(int id, String neighbourhoodGroup)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("neighbourhood_group", neighbourhoodGroup)));
    }

    public void updateNeighbourhood(int id, String neighbourhood)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("neighbourhood", neighbourhood)));
    }

    public void updateLatitude(int id, float latitude)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("latitude", latitude)));
    }

    public void updateLongtitude(int id, float longtitude)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("longtitude", longtitude)));
    }

    public void updateRoomType(int id, String roomType)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("room_type", roomType)));
    }

    public void updatePrice(int id, float price)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("price", price)));
    }

    public void updateMinimumNights(int id, int minimumNights)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("minimum_nights", minimumNights)));
    }

    public void updateLastReview(int id, int year, int month, int day)
    {
        String date = year + "-" + month + "-" + day;
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("last_review", date)));
    }

    public void updateReviewsPerMonth(int id, int reviewsPerMonth)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("reviews_per_month", reviewsPerMonth)));
    }

    public void updateCalculatedHostListingsCount(int id, int calculatedHostListingsCount)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("calculated_host_listings_count", calculatedHostListingsCount)));
    }

    public void updateAvailability365(int id, int availability365)
    {
        collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("availability_365", availability365)));
    }
    //endregion

    public boolean delete(int id)
    {
        return collection.deleteOne(Filters.eq("id", id)).getDeletedCount() > 0;
    }
}
