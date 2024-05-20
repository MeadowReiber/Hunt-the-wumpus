// crystal B

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

public class Hints{
  String hint;
  ArrayList<TriviaQuestion> TriviaQuestions;

  public Hints() throws FileNotFoundException{
      String filePath = "hint.txt";      
      Hints[] quest = new Hints[3];
      int count2 =0;
      File file2 = new File(filePath);
      Scanner scanner2 = new Scanner(file2);

      while(scanner2.hasNextLine()){
        String line = scanner2.nextLine();            
        String[] questionSource = line.split(",", 6);
        TriviaQuestion question = new TriviaQuestion(questionSource[0],Arrays.copyOfRange(questionSource, 1, 4) );
        TriviaQuestions.add(question);
      }
    }
        public void givehints(){

          //int RandomHints = (int)(Math.random)* Hints());
        }

    //learn to paird the hints to the

}
