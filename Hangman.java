

// Name: Bader Aljabri                   Student Number: 894231

/* Variable Dictionary

images: an array of strings that hold the ASCII art images for the Hangman game at different stages of progress. It's declared as a static variable of the Hangman class.
guessed: a string that holds the letters that the user has guessed so far. It's declared as a static variable of the Hangman class.
wrongGuesses: a string that holds the letters that the user has guessed incorrectly so far. It's declared as a static variable of the Hangman class.
strike: an integer that keeps track of the number of strikes the user has received. It's declared as a static variable of the Hangman class.
check: a boolean that is used to check if the user has guessed a wrong letter. It's declared as a static variable of the Hangman class.
rand: an instance of the Random class used to generate random numbers. It's used in the pickRandomWord method.
words: an array of strings that holds the words that can be used in the game. It's used in the pickRandomWord method.
word: a string that holds the word that the user is trying to guess. It's initialized in the main method by calling the pickRandomWord method.
picked: a character array that holds the letters that the user has correctly guessed so far. It's initialized in the main method with dashes for each letter in the word.
guess: a character that holds the letter that the user has just guessed. It's initialized in the handleGuess method.
correctGuess: a boolean that is used to check if the user has guessed a letter correctly. It's initialized in the handleGuess method.
pick: a char variable that holds the current guess that the user has made.
done: a boolean variable that tracks whether the user has correctly guessed the word or not.
i: an int variable used in a loop to iterate over the letters in the word.
lv1, lv2, lv3: String variables that hold the words that the user will need to guess in each of the three levels of the game.
level: an int variable that stores the chosen level of the game (1, 2, or 3).

*/

// Assignment 1: Hangman

import java.util.Random; // import Random class from java.util package
import java.util.Scanner; // import Scanner class from java.util package

class Hangman { // create Hangman class
  
  static String[] images = { // create static array images to hold the hangman ASCII art
            "   +--------+\n   |        |\n            |\n            |\n            |\n            |\n            |\n            |\n        ----+-----",
            "   +--------+\n   |        |\n   O        |\n            |\n            |\n            |\n            |\n            |\n        ----+-----",
            "   +--------+\n   |        |\n   O        |\n   |        |\n            |\n            |\n            |\n            |\n        ----+-----",
            "   +--------+\n   |        |\n   O        |\n  /|        |\n            |\n            |\n            |\n            |\n        ----+-----",
            "   +--------+\n   |        |\n   O        |\n  /|\\       |\n            |\n            |\n            |\n            |\n        ----+-----",
            "   +--------+\n   |        |\n   O        |\n  /|\\       |\n  /         |\n            |\n            |\n            |\n        ----+-----",
            "   +--------+\n   |        |\n   O        |\n  /|\\       |\n  / \\       |\n            |\n            |\n            |\n        ----+-----"
    };
  static String guessed = ""; // create static String called "guessed" to store guessed letters
  static String wrongGuesses = ""; // create static String called "wrongGuesses" to store wrong guessed letters
  static int strike = 0; // create static int strike to hold number of strikes
  static boolean check = false;
  
  private static void pln(Object line) { // create a private method pln to print a given line
    System.out.println(line); // print the given line
  } // closes pln function

  private static void output(char[] picked){ // create a private method output to output the hangman image, the picked letters, and the guessed letters
    pln("\033[H\033[2J"); // clear the screen
    pln(images[strike]); // print the appropriate hangman image based on the number of strikes
    System.out.print("\t\t"); // print some spacing
    for (Character s : picked) System.out.print(s + " "); // print the picked letters
    pln("\n"); // print a newline
    System.out.print("Guessed Letters: "); // print "Guessed Letters: "
    for (int i = 0; i <= guessed.length() - 1; i++){ // loop through guessed letters
      System.out.print(guessed.charAt(i) + " "); // print the guessed letter
    }
    pln("");
    pln("Wrong Guesses: " + wrongGuesses); // print wrong guesses
    if (check) { // the following code runs if the boolean "check" is set to true
      pln("That was incorrect!"); // prints out "That was incorrect!"
      check = false; // sets check to equal false
    } // closes if statement
  } // closes output function
  

  private static String pickRandomWord(String[] words) { // picks a random word out of a specific list when called
    Random rand = new Random(); // make the instance of the class Random calling it "rand"
    return words[rand.nextInt(words.length)]; // generates a random number between 0 and the length of the array which is 20
  } // closes pickRandomWord function

  
  private static boolean isWordGuessed(String word, char[] picked) { // cehcks if the word has been found yet
      return new String(picked).equals(word); // returns either true or false depending whether it has or has not been guessed
  } // closes isWordGuessed function


  private static void handleGuess(char pick, String word, char[] picked) { // checks if the inputed guess is actually correct or not
    boolean correctGuess = false; // declares a new boolean called "correctGuess" and sets it to equal false
    for (int i = 0; i < word.length(); i++) { // loops for the length of the chosen word
      if (word.charAt(i) == pick) { // checks if the i'th letter in word is equal to user input
        picked[i] = pick; // if so, the i'th value of picked is set to equal pick
        correctGuess = true; // and correctGuess is set to equal true
      } // closes if statement
    } // closes for loop
    
    if (!correctGuess && !(pick == '!')) { // checks if user is neither correct nor is equal to '!'
      strike++; // increases strike by one
      wrongGuesses += pick + " "; // expands on the string wrongGuess with the new input
      check = true; // sets check to equal true
    } // closes if statement
    
    output(picked); // calls the function output
    
    if (strike == 6) { // checks if strike is equal to 6
      pln("Sorry you struck out!"); // if so, "Sorry you struck out!" is printed out
      pln("The word was: " + word); // and the user is then told what the word was
      pln("Thank you for playing!"); // and "Thank you for playing!" is printed out
      System.exit(0); // the game is now over, so this terminates the code
    } // closes if statemnet
  } // closes handleGuess function

  
  public static void main(String[] args) { // main method
    String[] lv1 = {"acts", "aged", "holy", "aids", "aims", "call", "tell", "four", "doom", "rise", "come", "bell", "bear", "play", "sing", "bird", "bean", "game", "rice", "five"}; // create array lv1 with easy difficulty words consisting of 4 letters
    String[] lv2 = { "sister", "kidnap", "tissue", "empire", "resist", "tumour", "filter", "wonder", "drawer", "summit", "heroin", "letter", "screen", "memory", "immune", "stable", "Europe", "likely", "exotic", "active"}; // create array lv2 with medium difficulty words consisting of 6 letters
    String[] lv3 = { "recession", "practical", "recognize", "leftovers", "willpower", "guideline", "Different", "breakdown", "disappear", "cigarette", "exemption", "offspring", "establish", "treatment", "reservoir", "advantage", "paralyzed", "undermine", "sanctuary", "inflation"}; // create array lv3 with hard difficulty words consisting of 9 letters
    
    int i; // declares int called 'i'
    try (Scanner kbd = new Scanner(System.in)) {
      String word = null; // declares new string called "word" and sets it to null
      int level; // decalres new int and calles it "level"
      char pick; // decalres new char and calls it "pick"
      String done; // declares new string and calls it "done"

      
      pln("Welcome to Hangman!"); // prints out "Welcome to Hangman!"
      
      pln("Please indicate the level you want to play, 1, 2, or 3:"); // prints out "Please indicate the level you want to play, 1, 2, or 3:"
      level = kbd.nextInt(); // takes user input and stores it to input
      
      if (level == 1) word = pickRandomWord(lv1); // checks if level is equal to 1 and calls the function pickRandomWord and gives it the level 1 list of words
      else if (level == 2) word = pickRandomWord(lv2); // checks if level is equal to 2 and calls the function pickRandomWord and gives it the level 2 list of words
      else if (level == 3) word = pickRandomWord(lv3); // checks if level is equal to 3 and calls the function pickRandomWord and gives it the level 3 list of words
      
      char picked[] = new char[word.length()]; // decalres a new char array called picked and sets it's length to be equal to the length of the chosen word
      
      for (i = 0; i <= word.length() - 1; i++){ // runs for the length of the chosen word
        picked[i] = '_'; // sets the i'th position of picked to equal '_'
      } // closes for loop

      pln(images[0]); // prints out the first image of the hangman game
      System.out.print("\t\t"); // prints out two tabs preparing to print the unguessed letters underscores
      for (Character s : picked) System.out.print(s + " "); // prints out underscores for the length of the chosen word, or prints out every value of the array picked
      pln("\n"); // clicks enter basically, printing a new line

      while(true) { // runs until manually stopped
        pln("Pick a letter (or ! to solve): "); // prints out "Pick a letter (or ! to solve): "
        pick = kbd.next().charAt(0); // sets pick to equal the first letter of the user's input

        if (pick == '!') { // checks if pick is equal to '!'
          System.out.print("Solve: "); // prints out "Solve: "
          done = kbd.next(); // sets done to equal user input
          
          if (done.equals(word)){ // checks if done is equal to word
            pln("Congratulations, You got it!\nThank you for playing!"); // if so the user is congratulated that he guessed correctly and thanked for playing
            System.exit(0); // the game is name over so this terminates the code
          } else strike++; // otherwise if the guess is not equal to word, strike is increased by one
        } else { // otherwise if pick doesn not equal '!'
          if (guessed.contains(String.valueOf(pick))) { // checks if the input has already been guessed
            pln("You already guessed that letter!"); // prints out "You already guessed that letter!"
            continue; // ends the corrent itteration of the while loop and goes on to the next
          } // closesif statement
          guessed = guessed + String.valueOf(pick); // the inputed value is added to the array guessed
        } // closes else statement

        handleGuess(pick, word, picked); // calls the function handleGuess and gives it the following values pick, word, and picked

        if (isWordGuessed(word, picked)) { // calls isWordGuessed and if it returns true the following runs
          pln ("Congratulations, You got it!\nThank you for playing!"); // prints out "Congratulations, You got it!\nThank you for playing!"
          System.exit(0); // the game is now over, so this terminates the code
        } // closes if statement
      } // closes while loop
    }
  } // closes public static void main
} // closes class Hangman