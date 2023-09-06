public class ProductTest {
    public static void main(String[] args) {

        Product JowonsStuff = new Product("Ipad", " a tablet made by Apple", "00001", 100.89);

        System.out.println(JowonsStuff.toString());
        JowonsStuff.toCSVDataRecord();
        JowonsStuff.readingCSVDataRecords();


    }

}