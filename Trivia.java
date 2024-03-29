
//crystal B 
import java.io.FileWriter;
import java.io.IOException;

public class Trivia{

    public Trivia(){
        try{
            FileWriter write = New FileWriter();
            write.write("What color is the sky,blue");
            
            write.close();
        }
        catch(Exception e){

        }
    }

}