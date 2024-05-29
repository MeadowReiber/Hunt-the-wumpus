
//crystal B 

import java.util.Arrays;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Random;

public class Trivia{
  String hint;
  ArrayList<TriviaQuestion> TriviaQuestions;
  
  public Trivia() throws FileNotFoundException{
      String filePath = "triviaquestions.txt";      
      TriviaQuestions = new ArrayList<>(); // initizalize arraylist
      //int count =0;
      File file = new File(filePath);
      Scanner scanner = new Scanner(file);

      while(scanner.hasNextLine()){
        String line = scanner.nextLine();            
        String[] questionSource = line.split(",", 6);                                                        // for the correct answer
        TriviaQuestion question = new TriviaQuestion(questionSource[0],Arrays.copyOfRange(questionSource, 1, 4),questionSource );
        TriviaQuestion theanswers = new TriviaQuestion(questionSource[0],Arrays.copyOfRange( questionSource, 5,5), questionSource);
        TriviaQuestions.add(question);
        TriviaQuestions.add(theanswers);
      
      }
      scanner.close();
  }

    public TriviaQuestion GetQuestion(){
    // return a random question from the file
    //create a list of questions
    //cannot repeat questions until all have been given/when game resets
    
    Random random = new Random();
    int RandomIndex = random.nextInt(TriviaQuestions.size());
    TriviaQuestion ranQuestions = TriviaQuestions.get(RandomIndex);

    System.out.println("Question:" + ranQuestions.Question);
    System.out.println("choose the correct answer");
    
    
      //Scanner

    for(int i = 0; i  < ranQuestions.Answers.length; i++){
      System.out.println((i + 1) + ". " + ranQuestions.Answers[i]);
    }
    return ranQuestions;
     
    }
    public String getAnswer(TriviaQuestion theanswers){


      return theanswers.Answers[0];
    }


}
