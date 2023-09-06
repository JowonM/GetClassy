import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> people = new ArrayList<>();
        boolean cont = true;
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        String CSV = "";
        String ID = "";
        String FirstName = "";
        String LastName = "";
        String Title = "";
        int YearOfBirth = 0;

        do{
            ID = SafeInput.getNonZeroLenString(in,"Please enter your ID (6 digits)");
            FirstName = SafeInput.getNonZeroLenString(in,"Please enter your First Name");
            LastName = SafeInput.getNonZeroLenString(in,"Please enter your Last Name");
            Title = SafeInput.getNonZeroLenString(in,"Please enter your Title");
            YearOfBirth = SafeInput.getRangedInt(in,"Enter birth year",1000,9999);

            CSV = ID + " , " + FirstName + " , " + LastName + " , " + Title + " , " + YearOfBirth;
            people.add(CSV);
            cont = SafeInput.getYNConfirm(in, "Would you like to add another person");

        }
        while(cont);
        for(String p : people){
            System.out.println(p);
            try
            {
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                // Finally can write the file LOL!

                for(String rec : people)
                {
                    writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

       /* ArrayList<String> store = new ArrayList<String>();
        String data = "";
        Scanner input = new Scanner(System.in);
        Scanner dinput = new Scanner(System.in);
        System.out.println("Enter Data In this form - ID, FirstName, LastName, Title, YearOfBirth");
        System.out.println("Example: 000001, Jowon, Briggs, Mr., 2001");


        boolean cont = true;
        do{

            System.out.println("please enter data pertaining to a person ... ");
            data = dinput.nextLine();
            store.add(data);
            System.out.println("Would you like to list a person? (true/false)");

            cont = input.nextBoolean();

        }
        while(cont);
        System.out.println(store);
*/
    }
}