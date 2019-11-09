public class Main
{
    public static void main(String[] args)
    {
        MongoConnect mongo = new MongoConnect("nbd", "airbnb");
        mongo.createAll("airbnb.json");
//        int id = 123;
//        try
//        {
//            mongo.create(id);
//        }
//        catch (IllegalArgumentException e)
//        {
//            System.out.println("Document with id: " + id + " was not created, because it already exists");
//        }
//        System.out.println(mongo.readJson(id));
//        mongo.updateName(id, "test");
//        System.out.println(mongo.readJson(id));
//        System.out.println(mongo.delete(id));
    }
}
