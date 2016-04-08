
/**
 * Added authors as Group G on 3-21-2016 
 * Members are listed in Main.java
 * Software Design, Spring 2016
 * @author- Group G
 * Created 2/23/2016
 * 
 * Contains functionality used by Main
 * Reads in a file and converts bytes to be interpreted
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteFileStreamReader extends FileInputStream {

	/** Throws exception if file is not found 
	 * 
	 * @param file
	 * 			file is default.mze 
	 * @throws FileNotFoundException
	 */
	public ByteFileStreamReader(File file) throws FileNotFoundException {
		super(file);
	}
	
	/** 
	 * 
	 * @param b
	 * 		b is a placeholder for the data
	 * @return
	 * 		returns data type as an int
	 */
	public int convertByteToInt(byte[] b)
	{
		ByteBuffer buffer = ByteBuffer.wrap(b);
        return buffer.getInt();	}
	
	/**
	 * 
	 * @param b
	 * 		b is a placeholder for the data
	 * @return
	 * 		returns data type as a float
	 * convertByteToFloat takes in bytes and changes data type to float
	 */
	public float convertByteToFloat(byte[] b)
	{
		ByteBuffer buffer = ByteBuffer.wrap(b);
		return buffer.getFloat();
	}
	
	/**
	 * Takes in bytes 4 at a time as long as they are valid
	 * If bytes are not valid, error is given out
	 * If correct data type, convert the read in bytes to ints
	 * convertByteToInt takes valid bytes in
	 * 		and then converts them into useable ints
	 * @return
	 */
	public int readInt()
	{
		byte[] bytesRead = new byte[4];
		int checkIfValid = 0;
		try {
			checkIfValid = read(bytesRead);
		} catch (IOException e) {
			System.out.println("File not read.");
			e.printStackTrace();
		}
				
		if(checkIfValid == -1)
			return checkIfValid;
		else
			return convertByteToInt(bytesRead);
		
	}

}
