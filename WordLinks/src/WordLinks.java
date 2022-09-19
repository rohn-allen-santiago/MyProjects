/* SELF ASSESSMENT 
1. readDictionary
- I have the correct method definition [Mark out of 5:5]
- Comment: I defined readDictionary correctly
- My method reads the words from the "words.txt" file. [Mark out of 5:5]
- Comment: It is able to read the file
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5]
- Comment: An ArrayList is returned with the words from the file as individual strings

2. readWordList
- I have the correct method definition [Mark out of 5:5]
- Comment: I defined readWordList correctly
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:5]
- Comment: It reads the input correctly and stores the words in an ArrayList

3. isUniqueList
- I have the correct method definition [Mark out of 5:5]
- Comment: I defined isUniqueList correctly
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:5]
- Comment: Each word is compared with every other word in the array
- Exits the loop when a non-unique word is found. [Mark out of 5:5]
- Comment: If a non-unique word is found the loop ends
- Returns true is all the words are unique and false otherwise. [Mark out of 5:5]
- Comment: Returns the appropriate boolean if all the words are unique or not

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: I defined isEnglishWork correctly
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: It makes use of the binarySearch method
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment: The appropriate boolean is returned depending on the index returned by binarySearch

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: I defined isDifferentByOne correctly
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: It correctly compares each character in both words and returns true if there is only one difference

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment: I defined isWordChain correctly
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:10]
- Comment: It uses isUniqueList, isEnglishWord and isDifferentByOne and it also prints the correct message

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of the Java.IO classes covered in lectures [Mark out of 10:10]
- Comment: Every word from words.txt is read and put into an ArrayList using BufferedReader
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment: Uses isWordChain to continuously ask the user for input

 Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class WordLinks {
	
	public static ArrayList<String> readDictionary(String fileName) throws Exception{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> dictionary = new ArrayList<String>();
		String word;
		while ((word = br.readLine()) != null) {
			dictionary.add(word);
		}
		return dictionary;
	}
	
	public static ArrayList<String> readWordList(Scanner scanner) {
		ArrayList<String> userWords = new ArrayList<String>();
		String input = scanner.nextLine();
		if(input == "")
			userWords = null;
		else {
			String[] words = input.split(", ");
			for(int i = 0; i < words.length; i++) {
				userWords.add(words[i]);
			}
		}
		return userWords;
	}
	
	public static boolean isUniqueList(ArrayList<String> wordList) {
		boolean isUnique = true;
		int i = 0;
		while(i < wordList.size() && isUnique) {
			String currentWord = wordList.get(i);
			int j = 0;
			while(j < wordList.size() && isUnique) {
				if(j != i) {
					String comparingWord = wordList.get(j);
					if(currentWord.equalsIgnoreCase(comparingWord))
						isUnique = false;
				}
				j++;
			}
			i++;
		}
		return isUnique;
	}
	
	public static boolean isEnglishWord(String word, ArrayList<String> dictionary) {
		boolean isWord = true;
		String[] validWords = new String[dictionary.size()];
		for(int i = 0; i < dictionary.size(); i++ ) {
			validWords[i] = dictionary.get(i);
		}
		int indexOfWord = Arrays.binarySearch(validWords, word);
		if(indexOfWord < 0)
			isWord = false;
		
		return isWord;
	}
	
	public static boolean isDifferentByOne(String wordOne, String wordTwo) {
		boolean isDifferentByOne = true;
		char[] wordOneAsChar = wordOne.toCharArray();
		char[] wordTwoAsChar = wordTwo.toCharArray();
		if(wordOne.length() != wordTwo.length())
			isDifferentByOne = false;
		else {
			int count = 0;
			for(int i = 0; i < wordOneAsChar.length; i++) {
				char wordOneChar = wordOneAsChar[i];
				char wordTwoChar = wordTwoAsChar[i];
				if(wordOneChar != wordTwoChar)
					count++;
			}
			if(count != 1)
				isDifferentByOne = false;
		}
		return isDifferentByOne;
	}
	
	public static boolean isWordChain(ArrayList<String> wordList, ArrayList<String> dictionary) {
		boolean isWordChain = true;
		if(isUniqueList(wordList)) {
			for(int i = 0; i < wordList.size()-1; i++) {
				String currentWord = wordList.get(i);
				if(!isEnglishWord(currentWord, dictionary))
					isWordChain = false;
				String nextWord = wordList.get(i+1);
				if(!isEnglishWord(nextWord, dictionary))
					isWordChain = false;
				if(!isDifferentByOne(currentWord, nextWord))
					isWordChain = false;
			}
		}
		else
			isWordChain = false;
		System.out.println(((isWordChain) ? "Valid " : "Not a valid ") + "chain of words from Lewis Carroll's word-links game.");
		return isWordChain;
	}

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		ArrayList<String> dictionary = readDictionary("words.txt");
		ArrayList<String> wordList = new ArrayList<String>();
		wordList.add("Filler");
		while(wordList != null) {
			System.out.print("Enter a comma separated list of words (or an empty list to quit): ");
			wordList = readWordList(input);
			if(wordList != null)
				isWordChain(wordList, dictionary);
		}
	}

}
