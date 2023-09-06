public class PersonTest {
    public static void main(String[] args){

        Person Jowon = new Person("jowon", "briggs", "Mr.", "0001", 1000);

        System.out.println(Jowon.toString());
        Jowon.toCSVDataRecord();
        Jowon.readingCSVDataRecords();
    }
}

