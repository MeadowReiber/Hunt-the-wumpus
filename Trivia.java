
//crystal B 
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Trivia{
  String hint;
  ArrayList<TriviaQuestion> TriviaQuestions;

  public Trivia() throws FileNotFoundException{
      String filePath = "triviaquestions.txt";      
      Trivia[] qq = new Trivia[3];
      int count =0;
      File file = new File(filePath);
      Scanner scanner = new Scanner(file);

      while(scanner.hasNextLine()){
        String line = scanner.nextLine();            
        String[] questionSource = line.split(",", 6);
        TriviaQuestion question = new TriviaQuestion(questionSource[0],Arrays.copyOfRange(questionSource, 1, 4) );
        TriviaQuestions.add(question);
      }
  }

    public TriviaQuestion GetQuestion(){
    
     Random newQuestion = new Random();
    

    return null;
     
// you will get a question from the tivaquestion
    }

    
    public void GiveAnswer(){


    }
   
    /* 
    public String Hints(){
      System.out.println("hint:"+ hint);
    // will return hints
    //reutrn boolean, if answer if right/wrong
    }
    */

}