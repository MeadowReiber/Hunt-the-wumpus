
//crystal B 
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TriviaQuestion {

    String answer;
    String Question;
    

    
}

public class Trivia{

    

    public Trivia(){
        String filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
          // do something with line .. split 
         // Custom input string
        String str = "triviaquestions.txt";
        String[] Questions = str.split("triviaquestions.txt", 6);

        }
        //how to make the file doc, into somthing i can use a mthod on .split
        System.out.println("split successful.");
        return;

      } catch (IOException e) {
        System.out.println("Error loading file: " + e.getMessage());
      }
    }

    public TriviaQuestion GetQuestion(){

// you will get a question from the tivaquestion
    }

    
    public void GiveAnswer(){


    }
    public String Hints(){

    // will return hints
    //reutrn boolean, if answer if right/wrong
    }

}