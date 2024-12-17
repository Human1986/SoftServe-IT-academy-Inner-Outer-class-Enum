package jom.com.softserve.s3.task4;

//Describe LineType enum here
enum LineType {
    SOLID, DOTTED, DASHED, DOUBLE;

}
public class Line {
	// Write method code here
    public static String drawLine(LineType lineType) {
        return "The line is " + lineType.name().toLowerCase() + " type";
    }
}
