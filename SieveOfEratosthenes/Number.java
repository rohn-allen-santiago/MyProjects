
public class Number {
	
	private static final int ARRAY_OFFSET = 1; // Needed to account for sequence of numbers starting at 2
	public int number;
	private boolean isCrossed;
	
	public void setNumber(int userNumber) {
		number = userNumber;
	}
	public int getNumber() {
		return number;
	}
	public void setIsCrossed(boolean userBoolean) {
		isCrossed = userBoolean;
	}
	public boolean getIsCrossed() {
		return isCrossed;
	}
 	
	public static Number[] createSequence(int userNumber) {
		Number[] series = new Number[userNumber - ARRAY_OFFSET];
		int index = 0;
		int value = 2;
		while(index < series.length) {
			series[index] = new Number();
			series[index].setNumber(value);;
			value += 1;
			index += 1;
		}
		return series;
 	}
		
	public static void crossOutHigherMultiples(int userNumber, Number[] numbers) {
		if(numbers != null) {
			for(int index = 0; index < numbers.length; index++) {
				int value = numbers[index].getNumber();
				if(value%userNumber == 0 && value != userNumber)
					numbers[index].setIsCrossed(true);
			}
		}
	}
	
	public static Number[] sieve(int userNumber) {
		Number[] finalArray = createSequence(userNumber);
		for(int index = 0; index < finalArray.length; index++) {
			if(finalArray[index].getIsCrossed() == false)
				crossOutHigherMultiples(finalArray[index].getNumber(), finalArray);
		}
		return finalArray;
	}
	
	public static String sequenceToString(Number[] numbers) {
		String arrayNumbers = "";
		if (numbers != null) {
			for(int index = 0; index < numbers.length; index++) {
				if(numbers[index].getIsCrossed() == false) {
					String value = "" + numbers[index].getNumber() + ((index != numbers.length-1) ? ", " : "");
					arrayNumbers += value;
				}
				else {
					String value = "[" + numbers[index].getNumber() + ((index != numbers.length-1) ? "], " : "]");
					arrayNumbers += value;
				}
			}
		}
		return arrayNumbers;
	}
	
	public static String nonCrossedOutSubseqToString(Number[] numbers) {
		String arrayNumbers = "";
		if (numbers != null) {
			for(int index = 0; index < numbers.length; index++) {
				if(numbers[index].getIsCrossed() == false) {
					String value = ((index == 0) ? "" : ", ") + numbers[index].getNumber();
					arrayNumbers += value;
				}
			}
		}
		return arrayNumbers;
	}	
}
