import java.util.Arrays;
import java.util.Scanner;

public class MarksRemapper {
	
	public static final double DESIRED_AVERAGE = 60.0;
	public static final double DESIRED_STANDARD_DEVIATION = 20.0;
	
	public static double determineAverage( double[] arrayOfNumbers )
	{
		double sumOfNumbers = 0.0;
		double averageOfNumbers = 0.0;
		if (arrayOfNumbers != null && arrayOfNumbers.length != 0)
		{
			for(int index = 0; index < arrayOfNumbers.length; index++)
			{
				sumOfNumbers += arrayOfNumbers[index];
			}
			averageOfNumbers = sumOfNumbers / arrayOfNumbers.length;
		}
		else
		{
			averageOfNumbers = 0.0;
		}

		return averageOfNumbers;
	}
	
	public static double determineStandardDeviation( double[] arrayOfNumbers, double average )
	{
		double sumOfDeviations = 0.0;
		double standardDeviationOfNumbers = 0.0;
		int index = 0;
		if (arrayOfNumbers != null && arrayOfNumbers.length != 0)
		{
			while (index < arrayOfNumbers.length)
			{
				sumOfDeviations += Math.pow(arrayOfNumbers[index] - average, 2.0);
				index++;
			}
			standardDeviationOfNumbers = Math.sqrt(sumOfDeviations / arrayOfNumbers.length);
		}
		else
		{
			standardDeviationOfNumbers = 0.0;
		}
		return standardDeviationOfNumbers;
	}
	
	public static boolean modifyAllMarks( double[] arrayOfMarks, double desiredAverage, double desiredStandardDeviation )
	{
		double oldAverage = determineAverage(arrayOfMarks);
		double oldStandardDeviation = determineStandardDeviation(arrayOfMarks, oldAverage);
		for (int index = 0; index < arrayOfMarks.length; index++)
		{
			double oldMark = arrayOfMarks[index];
			double newMark = desiredAverage + (oldMark - oldAverage) * desiredStandardDeviation / oldStandardDeviation;
			if (newMark < 0.0)
				newMark = 0.0;
			else if (newMark > 100.0)
				newMark = 100.0;
			else;
			
			arrayOfMarks[index] = newMark;
		}
		if (arrayOfMarks != null && arrayOfMarks.length != 0)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		
		System.out.print("Enter all percentages (separated by spaces): ");
		Scanner input = new Scanner( System.in );
		Scanner inputLine = new Scanner( input.nextLine() );
		int arrayIndex = 0;
		double[] currentPercentages = new double[0];
		while(inputLine.hasNext())
		{
			if (inputLine.hasNextDouble())
			{
				currentPercentages = Arrays.copyOf(currentPercentages, currentPercentages.length+1);
				currentPercentages[arrayIndex] = inputLine.nextDouble();
				arrayIndex += 1;
			}
			else
			{
				System.out.println("Error: " + inputLine.next() + " is not a valid number.");
			}
		}
		double originalAverage = determineAverage(currentPercentages);
		double originalStandardDeviation = determineStandardDeviation(currentPercentages, originalAverage);
		System.out.printf("The original average was %.1f and the standard deviation was %.1f", originalAverage, originalStandardDeviation);
		double[] oldPercentages = currentPercentages.clone();
		String changesInPercentages = String.format("");
		modifyAllMarks(currentPercentages, DESIRED_AVERAGE, DESIRED_STANDARD_DEVIATION);
		for (int index = 0; index < currentPercentages.length; index++)
		{
			boolean isLastValue = (index == currentPercentages.length-1);
			changesInPercentages += String.format("%.1f->%.1f" + (isLastValue ? "" : ", "), oldPercentages[index], currentPercentages[index]);
		}
		double newAverage = determineAverage(currentPercentages);
		double newStandardDeviation = determineStandardDeviation(currentPercentages, newAverage);
		System.out.println("\n" + changesInPercentages);
		System.out.printf("The new average is %.1f and the new standard deviation is %.1f", newAverage, newStandardDeviation);
		input.close();
	}

}
