import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
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
        try
        {
            collection.insertOne(document);
        }
        catch(MongoWriteException e)
        {
            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY))
                System.out.println("Document with that id already exists");
        }
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
        Document set = new Document("$set", new Document("name", name));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateHostId(int id, int hostId)
    {
        Document set = new Document("$set", new Document("host_id", hostId));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateHostName(int id, String hostName)
    {
        Document set = new Document("$set", new Document("host_name", hostName));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateNeighbourhoodGroup(int id, String neighbourhoodGroup)
    {
        Document set = new Document("$set", new Document("neighbourhood_group", neighbourhoodGroup));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateNeighbourhood(int id, String neighbourhood)
    {
        Document set = new Document("$set", new Document("neighbourhood", neighbourhood));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateLatitude(int id, float latitude)
    {
        Document set = new Document("$set", new Document("latitude", latitude));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateLongtitude(int id, float longtitude)
    {
        Document set = new Document("$set", new Document("longtitude", longtitude));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateRoomType(int id, String roomType)
    {
        Document set = new Document("$set", new Document("room_type", roomType));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updatePrice(int id, float price)
    {
        Document set = new Document("$set", new Document("price", price));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateMinimumNights(int id, int minimumNights)
    {
        Document set = new Document("$set", new Document("minimum_nights", minimumNights));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateLastReview(int id, int year, int month, int day)
    {
        String date = year + "-" + month + "-" + day;
        Document set = new Document("$set", new Document("last_review", date));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateReviewsPerMonth(int id, int reviewsPerMonth)
    {
        Document set = new Document("$set", new Document("reviews_per_month", reviewsPerMonth));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateCalculatedHostListingsCount(int id, int calculatedHostListingsCount)
    {
        Document set = new Document("$set", new Document("calculated_host_listings_count", calculatedHostListingsCount));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }

    public void updateAvailability365(int id, int availability365)
    {
        Document set = new Document("$set", new Document("availability_365", availability365));
        collection.updateOne(new Document("_id", read(id).get("_id")), set);
    }
    //endregion

    public boolean delete(int id)
    {
        return collection.deleteOne(Filters.eq("id", id)).getDeletedCount() > 0;
    }
}
