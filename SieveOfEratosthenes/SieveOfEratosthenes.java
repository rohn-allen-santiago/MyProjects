/* SELF ASSESSMENT 
   1.    createSequence:
Did I use the correct method definition?
Mark out of 5: 5
Comment: I used the correct definition
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: I initialised an array using the passed parameter
Did I return the correct item?
Mark out of 5: 5
Comment: An array was returned
   2.    crossOutMultiples
Did I use the correct method definition?
Mark out of 5: 5
Comment: I used the correct definitions
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: I made sure the parameters are not null and one of them is a valid index into the array
Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: Yes
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: The correct items are crossed out
   3.    sieve   
Did I have the correct function definition?
Mark out of 5: 5
Comment: I used the correct function definition
Did I make calls to other methods?
Mark out of 5: 5
Comment: I called the crossOutMultiples and createSequence function
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2: 2
Comment: I returned the correct array
   4.    sequenceTostring  
Did I have the correct function definition?
Mark out of 5: 5
Comment: I used the correct function definition
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment:  I made sure the parameter is not null
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
Mark out of 10: 10
Comment: I looped through the array updating the string
   5.    nonCrossedOutSubseqToString  
Did I have the correct function definition
Mark out of 5: 5
Comment: I used the correct function definition  
Did I ensure the parameter to be used is not null?  
Mark out of 3: 3
Comment: I made sure the parameter to be used is not null
Did I loop through the array updating the String variable with just the non-crossed out numbers? 
Mark out of 5: 5
Comment: I looped through the array updating the string
   6.    main  
Did I ask  the user for input n and handles input errors?  
Mark out of 5: 5
Comments: I asked the user for input and handled errors
Did I make calls to other methods (at least one)?
Mark out of 5: 5
Comment:  I made calls to other methods
Did I print the output as shown in the question?  
Mark out of 5: 5
Comment:  I printed the output as shown
   7.    Overall
Is my code indented correctly?
Mark out of 4: 4
Comments: Code is indented correctly
Do my variable names make sense?
Mark out of 4: 4
Comments: My variable names make sense
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4: 4
Comments: My variable, method and class names follow the java coding standard
      Total Mark out of 100 (Add all the previous marks):  100
*/


import java.util.Scanner;

public class SieveOfEratosthenes {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int userNumber = 0;
		System.out.print("Enter int >= 2 : ");
		if(input.hasNextInt()) {
			userNumber = input.nextInt();
			if (userNumber < 2)
				System.out.println("Not a number greater than or equal to 2.");
		}
		else {
			System.out.println("Not a number greater than or equal to 2.");
		}
			
		Number[] numbers = Number.createSequence(userNumber);
		System.out.println(Number.sequenceToString(numbers));
		Number[] finalArray = Number.sieve(userNumber);
		for(int index = 0; index < numbers.length; index++) {
			if(numbers[index].getIsCrossed() == false) {  
				Number.crossOutHigherMultiples(numbers[index].getNumber(), numbers);
				if(!(Number.sequenceToString(numbers).equals(Number.sequenceToString(finalArray))))
					System.out.println(Number.sequenceToString(numbers));
			}
		}
		System.out.println(Number.sequenceToString(finalArray));
		System.out.println(Number.nonCrossedOutSubseqToString(finalArray));
		
		input.close();
	}

}
