import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileTest {
    public static void main(String [] args){
        //System.out.println(new File("src/data/login.txt").getAbsolutePath());
        try{
            File log = new File("src/data/login.txt");
            System.out.println(log.exists());
            Scanner scan = new Scanner(log);
            //int homeID = scan.nextInt();
            //System.out.println(homeID);
            scan.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex);
        }
    }
}
//C:\Users\joebo\Documents\School\CAU\Sophmore\EngineeringGraphics\jump\github_version\TLjava\TL\TLController\src\fileTest.java
//data/login.txt
//C:\Users\joebo\Documents\School\CAU\Sophmore\EngineeringGraphics\jump\github_version\TLjava\TL\TLController\src\fileTest.java