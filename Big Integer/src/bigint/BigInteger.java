package bigint;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer
 * with any number of digits, which overcomes the computer storage length
 * limitation of an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;

	/**
	 * Number of digits in this integer
	 */
	int numDigits;

	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as: 5 --> 3 --> 2
	 * 
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 * 5 --> 3 --> 2 (No zeros after the last 2)
	 */
	DigitNode front;

	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}

	/**
	 * Parses an input integer string into a corresponding BigInteger instance. A
	 * correctly formatted integer would have an optional sign as the first
	 * character (no sign means positive), and at least one digit character
	 * (including zero). Examples of correct format, with corresponding values
	 * Format Value +0 0 -0 0 +123 123 1023 1023 0012 12 0 0 -123 -123 -001 -1 +000
	 * 0
	 * 
	 * Leading and trailing spaces are ignored. So " +123 " will still parse
	 * correctly, as +123, after ignoring leading and trailing spaces in the input
	 * string.
	 * 
	 * Spaces between digits are not ignored. So "12 345" will not parse as an
	 * integer - the input is incorrectly formatted.
	 * 
	 * An integer with value 0 will correspond to a null (empty) list - see the
	 * BigInteger constructor
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer.
	 * @throws IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) throws IllegalArgumentException {

		BigInteger num = new BigInteger();

		integer = integer.trim();
		if (!Character.isDigit(integer.charAt(0))) {
			if (integer.charAt(0) == '-') {
				num.negative = true;
			} else if (integer.charAt(0) == '+') {
				num.negative = false;
			} else {
				throw new IllegalArgumentException();
			}
			integer = integer.substring(1);
		}
		while (integer.charAt(0) == '0' && integer.length() > 1) {
			integer = integer.substring(1);
		}

		for (int i = 0; i < integer.length(); i++) {
			if (Character.isDigit(integer.charAt(i))) {
				int digit = integer.charAt(i) - '0';
				num.front = new DigitNode(digit, num.front);
				num.numDigits++;
			} else {
				throw new IllegalArgumentException();
			}
		}
		return num;
	}

	/**
	 * Adds the first and second big integers, and returns the result in a NEW
	 * BigInteger object. DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative. (Which
	 * means this method can effectively subtract as well.)
	 * 
	 * @param first  First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	public static BigInteger add(BigInteger first, BigInteger second) {
		return first.added(second);
	}

	private BigInteger added(BigInteger other) {
		if ((this.negative == false && other.negative == false) || (this.negative == true && other.negative == true)) {
			DigitNode ptrSmall = this.front;
			DigitNode ptrBig = other.front;

			if (other.numDigits < this.numDigits) {
				ptrSmall = other.front;
				ptrBig = this.front;
			}

			BigInteger addedInt = new BigInteger();
			addedInt.negative = this.negative;
			int carry = 0;
			int addedDigit;
			int smallDigit, bigDigit;

			while (ptrBig != null || (ptrBig == null && carry != 0)) {
				if (ptrSmall == null) {
					smallDigit = 0;
				} else {
					smallDigit = ptrSmall.digit;
				}
				if (ptrBig == null) {
					bigDigit = 0;
				} else {
					bigDigit = ptrBig.digit;
				}

				addedDigit = bigDigit + smallDigit + carry;
				carry = 0;

				if (addedDigit > 9) {
					addedDigit = addedDigit % 10;
					carry = 1;
				}
				if (addedInt.numDigits == 0) {
					addedInt.front = new DigitNode(addedDigit, addedInt.front);
				} else {
					DigitNode ptr = addedInt.front;
					while (ptr.next != null) {
						ptr = ptr.next;
					}
					ptr.next = new DigitNode(addedDigit, null);
				}
				addedInt.numDigits++;

				if (ptrSmall != null) {
					ptrSmall = ptrSmall.next;
				}
				if (ptrBig != null) {
					ptrBig = ptrBig.next;
				}
			}

			return addedInt;
		} else {
			BigInteger firstInt = new BigInteger();
			DigitNode end = firstInt.front;
			for (DigitNode ptrFirst = this.front; ptrFirst != null; ptrFirst = ptrFirst.next) {
				if (firstInt.numDigits == 0) {
					end = new DigitNode(ptrFirst.digit, end);
				} else {
					end.next = new DigitNode(ptrFirst.digit, null);
					end = end.next;
				}
				firstInt.numDigits++;
			}

			BigInteger secondInt = new BigInteger();
			for (DigitNode ptrSecond = other.front; ptrSecond != null; ptrSecond = ptrSecond.next) {
				if (secondInt.numDigits == 0) {
					secondInt.front = new DigitNode(ptrSecond.digit, this.front);
				} else {
					DigitNode ptr = secondInt.front;
					while (ptr.next != null) {
						ptr = ptr.next;
					}
					ptr.next = new DigitNode(ptrSecond.digit, null);
				}
				secondInt.numDigits++;
			}

			DigitNode ptrPositive = firstInt.front;
			DigitNode ptrNegative = secondInt.front;
			boolean posBigger = true;
			boolean firstIntIsPositive = true;

			if (this.negative == true) {
				ptrNegative = this.front;
				ptrPositive = other.front;
				firstIntIsPositive = false;

				if (this.numDigits > other.numDigits) {
					posBigger = false;
				} else if (this.numDigits < other.numDigits) {
					posBigger = true;
				} else {
					while (ptrNegative.digit == ptrPositive.digit && ptrNegative != null) {
						ptrNegative = ptrNegative.next;
						ptrPositive = ptrPositive.next;
					}
					if (ptrNegative == null) {
						posBigger = true;
					} else if (ptrNegative.digit > ptrPositive.digit) {
						posBigger = false;
					} else {
						posBigger = true;
					}
				}
				ptrNegative = this.front;
				ptrPositive = other.front;
			} else {
				if (this.numDigits > other.numDigits) {
					posBigger = true;
				} else if (this.numDigits < other.numDigits) {
					posBigger = false;
				} else {
					while (ptrNegative.digit == ptrPositive.digit && ptrNegative != null) {
						ptrNegative = ptrNegative.next;
						ptrPositive = ptrPositive.next;
					}
					if (ptrNegative == null) {
						posBigger = true;
					} else if (ptrNegative.digit > ptrPositive.digit) {
						posBigger = false;
					} else {
						posBigger = true;
					}
				}
				ptrNegative = other.front;
				ptrPositive = this.front;
				firstIntIsPositive = true;
			}

			if (!posBigger) {
				DigitNode temp = ptrNegative;
				ptrNegative = ptrPositive;
				ptrPositive = temp;
				if (firstIntIsPositive) {
					firstIntIsPositive = false;
				} else {
					firstIntIsPositive = true;
				}
			}

			BigInteger answer = new BigInteger();
			int bottomDigit = 0;
			int subtracted = 0;
			while (ptrPositive != null) {
				if (ptrNegative == null) {
					bottomDigit = 0;
				} else {
					bottomDigit = ptrNegative.digit;
				}
				subtracted = ptrPositive.digit - bottomDigit;
				if (subtracted < 0) {
					DigitNode ptrPositiveOriginal = ptrPositive;
					ptrPositive = ptrPositive.next;
					while (ptrPositive.digit == 0 && ptrPositive != null) {
						ptrPositive.digit = 9;
						ptrPositive = ptrPositive.next;
					}
					ptrPositive.digit--;
					subtracted = ptrPositive.digit + 10 - bottomDigit;
					ptrPositive = ptrPositiveOriginal;
				}

				if (answer.numDigits == 0) {
					answer.front = new DigitNode(subtracted, answer.front);
				} else {
					DigitNode ptr = answer.front;
					while (ptr.next != null) {
						ptr = ptr.next;
					}
					ptr.next = new DigitNode(subtracted, null);
				}
				answer.numDigits++;
				ptrPositive = ptrPositive.next;
				if (ptrNegative != null) {
					ptrNegative = ptrNegative.next;
				}
			}

			if (!posBigger) {
				answer.negative = true;
			} else {
				answer.negative = false;
			}
			return answer;
		}
	}

	/**
	 * Returns the BigInteger obtained by multiplying the first big integer with the
	 * second big integer
	 * 
	 * This method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first  First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big
	 *         integers
	 */
	public static BigInteger multiply(BigInteger first, BigInteger second) {

		BigInteger answer = new BigInteger();
		answer = parse("0");
		DigitNode firstPtr = first.front;
		DigitNode secondPtr = second.front;
		int place = 0;
		int carry = 0;

		while (firstPtr != null) {
			BigInteger lineStorage = new BigInteger();
			while (secondPtr != null) {
				int line = firstPtr.digit * secondPtr.digit + carry;
				if (line > 9) {
					carry = line / 10;
					line = line % 10;
				} else {
					carry = 0;
				}

				if (lineStorage.numDigits == 0) {
					lineStorage.front = new DigitNode(line, lineStorage.front);
				} else {
					DigitNode ptr = lineStorage.front;
					while (ptr.next != null) {
						ptr = ptr.next;
					}
					ptr.next = new DigitNode(line, null);
				}
				lineStorage.numDigits++;
				secondPtr = secondPtr.next;
			}

			int count = place;
			while (count > 0) {
				lineStorage.front = new DigitNode(0, lineStorage.front);
				lineStorage.numDigits++;
				count--;
			}
			if (carry > 0) {
				DigitNode ptr = lineStorage.front;
				while (ptr.next != null) {
					ptr = ptr.next;
				}
				ptr.next = new DigitNode(carry, null);
				lineStorage.numDigits++;
			}
			lineStorage = BigInteger.parse(lineStorage.toString());
			answer = answer.added(lineStorage);

			place++;
			carry = 0;
			firstPtr = firstPtr.next;
			secondPtr = second.front;
		}

		if ((first.negative == true && second.negative == true)
				|| (first.negative == false && second.negative == false)) {
			answer.negative = false;
		} else {
			answer.negative = true;
		}
		return answer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
			retval = curr.digit + retval;
		}

		if (negative) {
			retval = '-' + retval;
		}
		return retval;
	}
}
