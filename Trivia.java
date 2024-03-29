
//crystal B 
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Trivia{

    
    String answer;
    String Question;

    public Trivia(){
        try{
            FileWriter write = New FileWriter();
            write.write("What color is the sky,blue");
            
            write.close();
        }
        catch(Exception e){

        }
    }

    public void Givequestions(){


    }

    
    public void GiveAnswer(){


    }

}