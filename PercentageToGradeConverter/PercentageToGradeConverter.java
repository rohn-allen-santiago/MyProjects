
/*  SELF ASSESSMENT of whether my code is easy to understand.
   1. Did I use easy-to-understand meaningful variable names?
       Mark out of 5: 5  
       Comment: The variable names are clear
   2. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5: 5  
       Comment: Variable names are formatted properly
   3. Did I indent the code appropriately?
       Mark out of 5: 5  
       Comment: The code is indented properly
   4. Did I implement a switch statement as required?
       Mark out of 10: 10  
       Comment: I used a switch statement
   5. Did I implement a do-while (I changed it from switch to do-while since it was repeated) statement as required?
       Mark out of 10: 10  
       Comment: I used a do-while statement
     Total Mark out of  25 (Add all the previous marks): 35? (The marks are not correct)
*/

import java.util.Scanner;

public class PercentageToGradeConverter {

	public static void main(String[] args) {
		
		Scanner input = new Scanner( System.in );
		boolean isQuit = false;
		int percentageTensPlace = 11;
		double studentPercentage = 0.0;
		String studentGrade = "";
		
		do
		{
			
			System.out.print("Enter a percentage (or 'quit' to finish)> ");
			if (input.hasNextDouble())
			{
				studentPercentage = input.nextDouble();
				if (studentPercentage < 0.0 || studentPercentage > 100.0)
				{
					System.out.println("Error: Percentages must be between 0.0% and 100.0%");
					percentageTensPlace = 11;
				}
				else
				{
					double copyStudentPercentage = Math.round(studentPercentage);
					while (copyStudentPercentage % 10 != 0)
					{
						copyStudentPercentage -= 1;
					}
					percentageTensPlace = (int)copyStudentPercentage / 10;
				}
			}
			
			else if (input.hasNext())
			{
				String studentInput = input.next();
				isQuit = studentInput.equalsIgnoreCase("Quit");
				if (isQuit)
				{
					
				}
				else
					System.out.println("Error: Enter a number between 0.0 and 100.0, or the word quit to exit the program.");
				
				percentageTensPlace = 11;
			}
			
			switch (percentageTensPlace)
			{
			case 10:
			case 9:
			case 8:
			case 7:
				studentGrade = "I";
				break;
			case 6:
				studentGrade = "II.1";
				break;
			case 5:
				studentGrade = "II.2";
				break;
			case 4:
				studentGrade = "III";
				break;
			case 3:
				if (Math.round(studentPercentage) >= 35.0)
					studentGrade = "F1";
				else
					studentGrade = "F2";
				break;
			case 2:
			case 1:
			case 0:
				studentGrade = "F2";
				break;
			default:
				break;
			}
			
			if (percentageTensPlace >= 0 && percentageTensPlace <= 3)
				System.out.println("The grade for " + studentPercentage + "% is an " + studentGrade);
			else if (percentageTensPlace >= 4 && percentageTensPlace < 11)
				System.out.println("The grade for " + studentPercentage + "% is a " + studentGrade);
			
		} while (!isQuit);
		
	input.close();

	}

}
