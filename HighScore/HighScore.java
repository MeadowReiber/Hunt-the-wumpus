package HighScore;
//Cypher Davis
//wumpus
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class HighScore {
  //variables
  private Scanner user;
  private Scanner reader;
  //constructors
  public HighScore(){
    this.user = new Scanner(System.in);
    File f createFile("players.csv");
    this.reader = new Scanner(f);

    
  }
  //methods

  //file methods
  public File createFile(String fileName){
    File f = new File(fileName);
    return f;
  }

  public void sortFile(){
    //????? i dont know if this is necesary ?????????
  }

  public int getFileLength(){
    int length = 0;

    try{
      while(reader.hasNextLine(){
        scanner.nextLine();
        length++;
      })
    }catch(FileNotFoundException e){
      System.out.println("oopsies theres a mistake somewhere");
    }
    return length;
  }

  public Player[] createArray(Scanner reader, int length, String name){

  }

  public String[] turnIntoString(Player[] players, String name, int highScore){

  }

  public void writeToFile(String[] str, Player p){

  }

  //score methods
  public void displayScore(){
    
  }

// get array of highscore(player)
// if players int is high enough then replace them in the array and fix it
// turn into array of strings
// delete file info
// write array to the file


}
