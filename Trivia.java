
//crystal B 

import java.util.Arrays;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Random;

public class Trivia{
  String hint;
  Scanner scanner;
  ArrayList<TriviaQuestion> TriviaQuestions;
  
  public Trivia() throws FileNotFoundException{
      String filePath = "triviaquestions.txt";      
      TriviaQuestions = new ArrayList<>(); // initizalize arraylist
      //int count =0;
      File file = new File(filePath);
      scanner = new Scanner(file);

      while(scanner.hasNextLine()){
        String line = scanner.nextLine();            
        String[] questionSource = line.split(",", 6);                                                        // for the correct answer
        TriviaQuestion question = new TriviaQuestion(questionSource[0],Arrays.copyOfRange(questionSource, 1, 5),questionSource[5] );
        TriviaQuestions.add(question);
      
      
      }
      //scanner.close();
  }

    public TriviaQuestion GetQuestion(){
    // return a random question from the file
    //create a list of questions
    Random random = new Random();
    int RandomIndex = random.nextInt(TriviaQuestions.size());
    TriviaQuestion ranQuestions = TriviaQuestions.get(RandomIndex);

    System.out.println("Question:" + ranQuestions.Question);
    System.out.println("choose the correct answer");


    for(int i = 0; i  < ranQuestions.Answers.length; i++){
      System.out.println((i + 1) + "." + ranQuestions.Answers[i]);
    } 
      System.out.print("Enter your guess(1-" + ranQuestions.Answers.length + "):" );
      int userGuess = scanner.nextInt();
      
      boolean isCorrect = giveCorrectAnswer(userGuess, ranQuestions);
        if (isCorrect) {
            System.out.println("Congratulations! Your guess is correct.");
        } else {
            System.out.println("Sorry, your guess is incorrect you are dumb .");
            System.out.println("The correct answer is: " + ranQuestions.CorrectAnswer);
    
          return ranQuestions;
     
// you will get a question from the tivaquestion
    }
  public boolean giveCorrectAnswer(int userAnswer, TriviaQuestion question){
   return userAnswer == Integer.parseInt(question.CorrectAnswer) - 1;

    


}
