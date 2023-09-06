import javax.swing.*;
import java.io.*;
import java.lang.Integer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Person {
    private String firstName;
    private String lastName;
    private String title;
    private String IdNum;
    private int YOB;

    public Person(String firstName, String lastName, String title, String idNum, int YOB) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        IdNum = idNum;
        this.YOB = YOB;
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;

    }// returns firstName, space, lastName

    public String formalName() {
        return this.title + " " + this.firstName + " " + this.lastName;


    }// returns title, space, fullName

    public String getAge() {
        return Integer.toString((int) YOB - 2023);

    }// returns the age assuming the current year

    public String getAge(int year) {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);

        return Integer.toString(year - this.YOB);


    }

    public String toString() {


        return IdNum + " , " + firstName + " , " + lastName + " , " + title + " , " + YOB;


    }

    // uses YOB to calculate age for a specified year
    // use the Calendar object to do these. Requires a bit of a web search.
    public String toCSVDataRecord() {
        ArrayList<String> people = new ArrayList<>();
        boolean cont = true;
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");


        do {
            Person one = new Person(SafeInput.getNonZeroLenString(in, "Please enter your ID (6 digits)"), SafeInput.getNonZeroLenString(in, "Please enter your First Name"), SafeInput.getNonZeroLenString(in, "Please enter your Last Name"), SafeInput.getNonZeroLenString(in, "Please enter your Title"), SafeInput.getRangedInt(in, "Enter birth year", 1000, 9999));


            String CSV = one.toString();
            people.add(CSV);
            cont = SafeInput.getYNConfirm(in, "Would you like to add another person");

        }
        while (cont);
        for (String p : people) {
            //System.out.println(p);
            try {
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                // Finally can write the file LOL!

                for (String rec : people) {
                    writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String readingCSVDataRecords()
    {
        //Path newPath = Paths.getPath("C:\\java\\rules.java");
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        System.out.println("Please select a Person file...");

        try
        {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typiacally, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;
                System.out.println("ID#    Firstname Lastname Title   YOB");
                System.out.println("===================================");
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    // echo to screen
                    System.out.printf("\n"+rec);
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}




