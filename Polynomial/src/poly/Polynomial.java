package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	
	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
	// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	public static Node add(Node poly1, Node poly2) {
		
		Node front = null, ptr = null, back = null;
	    if (poly1 == null && poly2 == null) { //if both poly1 and poly2 are empty
	    	return front;
	    }
	    while(poly1 != null && poly2 != null) {
	    	if(poly1.term.degree < poly2.term.degree) { //adds unique poly1 terms
	    		//can't assume that poly1 < poly2 because of negative numbers
	            ptr = new Node(poly1.term.coeff, poly1.term.degree, null);
	            if (back != null){
	            	back.next = ptr; 
	            }
	            else {
	            	front = ptr;
	            }
	            back = ptr;
	            poly1 = poly1.next;
	    	}
	    	else if (poly2.term.degree < poly1.term.degree) { //add unique poly2 terms
	    		ptr = new Node(poly2.term.coeff, poly2.term.degree, null);
	            if (back != null){
	            	back.next = ptr;
	            }
	            else {
	            	front = ptr;
	            }
	            back = ptr;
	            poly2 = poly2.next;
	        }
	    	else if (poly1.term.degree == poly2.term.degree){//adds common terms
	    		ptr = new Node(poly1.term.coeff + poly2.term.coeff, poly1.term.degree,null);
	            if (back != null){
	            	back.next = ptr;
	            }
	            else {
	            	front = ptr;
	            }
	            back = ptr;
	            poly1 = poly1.next;
	            poly2 = poly2.next;
	    	}
	    }
	    
	    if (poly1 != null) { //checks the remaining poly1
	    	while(poly1 != null) {
	        ptr = new Node(poly1.term.coeff, poly1.term.degree, null);
	        if (back != null){
	        	back.next = ptr;
	        }
	        else {
	        	front = ptr;
	        }
	        back = ptr;
	        poly1 = poly1.next;
	        }
	    }
	    else if (poly2 != null){ //checks remaining poly2
	    	while(poly2 != null) {
	    		ptr = new Node(poly2.term.coeff, poly2.term.degree, null);
	    		if (back != null){
	    			back.next = ptr;
	            }
	    		else {
	    			front = ptr;
	    		}
	    		back = ptr;
	    		poly2 = poly2.next;
	    	}
	    }
	       
	    Node prev = null;
	    ptr = front;
	    while (ptr != null) { //get rid of zero coefficients
	    	if (ptr.term.coeff == 0) {
	    		if (ptr == front) {
	    			front = ptr.next;
	    		}
	    		else {
	    			prev.next = ptr.next;
	    		}
	    	}
	    	else {
	    		prev = ptr;
	    	}
	    	ptr = ptr.next;
	    }      
	    return front;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	
	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
	// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	public static Node multiply(Node poly1, Node poly2) {
		
		Node front = null, back = null, ans = null;
		if (poly1 == null || poly2 == null) {
			return front;
		}
		for (Node ptr2 = poly2; ptr2 != null; ptr2 = ptr2.next) {
			for (Node ptr1 = poly1; ptr1 != null; ptr1 = ptr1.next) {
				Node ptr = new Node(ptr1.term.coeff * ptr2.term.coeff, ptr1.term.degree + ptr2.term.degree, null);
				if (back != null){
					back.next = ptr;
				}
				else {
					front = ptr;
				}
				back = ptr;
			}
			ans = add(ans, front);
			front = null;
			back = null;
		}
		return ans;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	
	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
	// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	public static float evaluate(Node poly, float x) {
		float total = 0;
		if (poly != null) {
			for(Node ptr = poly; ptr != null; ptr = ptr.next) {
				total += ptr.term.coeff * Math.pow(x,ptr.term.degree);
			}
		}
		return total;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
