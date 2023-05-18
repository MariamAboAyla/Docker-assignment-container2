import java.io.*;
import java.util.Scanner;

public class Main {

    static class Student{
        String name;
        int id;
        String courses;
        Student(String n, int i, String c){
            name = n;
            id = i;
            courses = c;
        }

    }

    static void addStudent ( Student s )
    {

        try {
            FileWriter fileWriter = new FileWriter ("data.txt", true) ;
            BufferedWriter bufferedWriter = new BufferedWriter ( fileWriter );
            PrintWriter writer = new PrintWriter ( bufferedWriter );
            writer.write ( s.name+";" );
            writer.write ( s.id+";" );
            writer.write ( s.courses);
            writer.write ( "\n" );

            writer.close ();
            bufferedWriter.close ();
            fileWriter.close ();

        } catch (IOException e) {
            e.printStackTrace ( );
        }

    }

    static void addBatchStudents(String otherFile)
    {
        // Browse some file and pick the file you want

        try {
            // open file writer
            FileWriter fileWriter = new FileWriter ("data.txt", true) ;
            BufferedWriter bufferedWriter = new BufferedWriter ( fileWriter );
            PrintWriter writer = new PrintWriter ( bufferedWriter );

            // open file reader
            File file = new File(otherFile);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                writer.write(data);
                writer.write ( "\n" );
            }

            myReader.close();
            writer.close ();
            bufferedWriter.close ();
            fileWriter.close ();

        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }

    }


    public static void main(String[] args) {

        System.out.println ( "1- Add student data\n" +
                "2- Add batch students data");

        Scanner scan = new Scanner ( System.in );
        int choice = scan.nextInt ( );

        if(choice == 1){
            System.out.println ("Enter Student Name: " );
            String name = scan.next ( );
            System.out.println ("Enter Student Id: " );
            int id = scan.nextInt ( );
            System.out.println ("Enter Courses comma-separated: " );
            String courses = scan.next ();

            Student student = new Student(name, id, courses);
            addStudent(student);

        }else if (choice == 2){

            File directoryPath = new File( System.getProperty("user.dir")+"batch" );
            String contents[] = directoryPath.list();
            System.out.println("List of files in the current directory:");
            for(int i=0; i<contents.length; i++) {
                if(contents[i].endsWith(".txt"))
                System.out.println(contents[i]);
            }

            System.out.println ("Enter the file name you want: " );
            String file = scan.next ();

            if(file.contains ( "verified" ))
                addBatchStudents (file);
            else
                System.out.println ("Invalid file ! Must choose approved file!" );

        }else{
            System.out.println ("Invalid option !" );
        }


    }

}
