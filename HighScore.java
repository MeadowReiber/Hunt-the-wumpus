
//Cypher Davis
//wumpus
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.lang.StringBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {
  //variables
  private Scanner user;
  private Scanner reader;
  private File f;
  //constructors
  public HighScore(){
    this.user = new Scanner(System.in);
    this.f = createFile("players.csv");
    try { 
      this.reader = new Scanner(f);
    } catch (FileNotFoundException e) {
      System.out.println("well thats concerning");
    }

    
  }
  //methods

  //file methods
  public File createFile(String fileName){
    File f = new File(fileName);
    return f;
  }

  public Player[] sortFile(Player[] players, Player p){
    for(int i = 0; i < players.length; i++){
      if(p.getHighScore() > player[i].getHighScore){
        for(int j = players.length - 1; j > i){
          players[j] = players[j-1];
        }
        players[i] = p;
      }
    }
    return players;
  }

  public int getFileLength(){
    int length = 0;

    
    while(reader.hasNextLine()){
      user.nextLine();
      length++;
    }
    
    return length;
  }

  public Player[] createArray(Scanner reader, int length, String name){
    Player[] players = new Player[length - 1];
    user.nextLine();

    for(int i = 0; i < players.length; i++){
      String[] vars = reader.nextLine().split(",");
      int score = Integer.valueOf(vars[1]).intValue();
      Player p = new Player(vars[0], score );
      players[i] = p;
    }
    
    return players;
  }

  public String[] turnIntoString(Player[] players, String name, int highScore){
    String finalString[] = new String[players.length];

    for(int i = 0; i < players.length; i++){
      String[] shortArray = {name,String.valueOf(highScore)};
      String result = "";

      if(shortArray.length > 0){
        StringBuilder sb = new StringBuilder( );

        for(String s : shortArray){
          sb.append(s).append(",");
        }
        result = sb.deleteCharAt(sb.length() - 1).toString();
      }
      finalString[i] = result;
    }
    return finalString;
  }

  public void writeToFile(String[] str, Player p){
    try{
      FileWriter writer = new FileWriter(this.f,false);
      writer.write("name,highscore");

      for(int i = 0; i < str.length; i++){
        writer.write(str[i] + "\n");
      }
      writer.close();
      System.out.println();
    }catch (IOException e){
      System.out.println("oopsies i made a mistake");
    }
  }

  //score methods
  public void displayScore(){
    
  }

// get array of highscore(player)
// if players int is high enough then replace them in the array and fix it
// turn into array of strings
// delete file info
// write array to the file

//check all methods ESPECIALLY sortfile


}
