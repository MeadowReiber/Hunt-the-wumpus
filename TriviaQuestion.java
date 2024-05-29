public class TriviaQuestion {
     public static final TriviaQuestion RandomIndex = null;
    String[] Answers;
    String Question;
    String[] CorrectAnswer;

    public TriviaQuestion(String question,String[] answers, String[] correctAnswer){
      Question = question;
      Answers = answers;
      CorrectAnswer = correctAnswer;
    }

}
