// https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
// https://www.kenwalger.com/blog/nosql/mongodb-and-java/

public class Main
{
    public static void main(String[] args)
    {
        MongoConnect mongo = new MongoConnect("nbd", "airbnb");
        System.out.println(mongo.read(3647));
    }
}
