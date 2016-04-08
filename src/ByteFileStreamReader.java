import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteFileStreamReader extends FileInputStream {

	public ByteFileStreamReader(File file) throws FileNotFoundException {
		super(file);
	}
	
	
	public int convertByteToInt(byte[] b)
	{
		ByteBuffer buffer = ByteBuffer.wrap(b);
        return buffer.getInt();	}
	
	public float convertByteToFloat(byte[] b)
	{
		ByteBuffer buffer = ByteBuffer.wrap(b);
		return buffer.getFloat();
	}
	
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
	
	public float readFloat()
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
			return convertByteToFloat(bytesRead);
	}

}
