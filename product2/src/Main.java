import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static void showStatus()
    {

        // displays : number of students and each course and number of students registered in it
        // open file reader
        String path = System.getProperty("user.dir");
        int ind = path.length ();
        for(int i = path.length ()-1, cnt = 0; i>=0; i--){
            if(path.charAt ( i ) == '\\')
                cnt++;

            if(cnt==1)
            {
                ind = i;
                break;
            }

        }
        path = path.substring ( 0, ind );

        File file = new File(path+"\\product1\\data.txt");
        Scanner myReader = null;


        int studentCounter = 0; // count the number of students
        Map<String, Integer> courses = new HashMap<String,Integer> (  );

        try {
            myReader = new Scanner (file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String []tmp = data.split ( ";" );
                studentCounter++;

                // the courses
                String []lastTmp = tmp[2].split ( "," );
                for (String cur:lastTmp) {
                    if( courses.containsKey ( cur) )
                        courses.replace ( cur, courses.get ( cur ), courses.get ( cur ) );
                    else
                        courses.put ( cur, 1 );
                }

            }


            System.out.println ("Number of users: " + studentCounter );
            for(Map.Entry<String,Integer> it: courses.entrySet ())
            {
                System.out.println ("Number of students registered in "+it.getKey ()+" course: "+ it.getValue ());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }


    }

    static void showBatchStatus ( )
    {

        // displays :  number of approved & number of total files
        String path = System.getProperty("user.dir");
        int ind = path.length ();
        for(int i = path.length ()-1, cnt = 0; i>=0; i--){
            if(path.charAt ( i ) == '\\')
                cnt++;

            if(cnt==1)
            {
                ind = i;
                break;
            }

        }
        path = path.substring ( 0, ind );


        File directoryPath = new File( path+"\\product1\\batch" );
        String contents[] = directoryPath.list();

        int cntBatFiles = 0;
        int cntVerFiles = 0;

        for(String current:contents)
        {
            cntBatFiles++;
            if(current.contains ( "verified" ))
                cntVerFiles++;
        }

        System.out.println ("Number of batch files: "+ cntBatFiles);
        System.out.println ( "Number of verified batch files: "+ cntVerFiles );

    }


    public static void main(String[] args) {

        showStatus ();
        showBatchStatus();

    }
}
