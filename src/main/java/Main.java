// https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
// https://www.kenwalger.com/blog/nosql/mongodb-and-java/

import org.bson.Document;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        MongoConnect mongo = new MongoConnect("nbd", "airbnb");
        System.out.println(mongo.read(3647));
        mongo.create(123);
//        mongo.delete(123);
    }
}
