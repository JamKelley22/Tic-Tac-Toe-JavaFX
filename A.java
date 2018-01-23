package start;

/**
 * A Class to allow shorter console output functions
 * eg. System.out.println(Object) vs A.SOPL(Object)
 * 
 * @author Jameel
 *
 */
public class A {
	
	/**
	 * Shortcut to System.out.println(o);
	 * @param o
	 */
	public static void SOPL(Object o) {
		if(CONFIG.DEBUG)
			System.out.println(o);
	}
	
	/**
	 * System.out.print(o);
	 * @param o
	 */
	public static void SOP(Object o) {
		if(CONFIG.DEBUG)
			System.out.print(o);
	}
}
