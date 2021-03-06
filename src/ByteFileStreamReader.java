
/**
 * Members are listed in Main.java
 * Software Design, Spring 2016
 * @author- Group G
 * Created 4/7/2016
 * 
 * Contains functionality used by Main
 * Reads in a file and converts bytes to be interpreted
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteFileStreamReader extends FileInputStream
{

    /**
     * Throws exception if file is not found
     * 
     * @param file
     *            file is default.mze
     * @throws FileNotFoundException
     */
    public ByteFileStreamReader(File file) throws FileNotFoundException
    {
        super(file);
    }

    /**
     * 
     * @param b
     *            b is a placeholder for the data
     * @return returns data type as an int
     */
    private int convertByteToInt(byte[] b)
    {
        ByteBuffer buffer = ByteBuffer.wrap(b);
        return buffer.getInt();
    }
    
    /*
     * @param b
     * b is the placeholder for the long data
     * 
     * @return returns data type as a long DK 5/4/16
     */
    private long convertByteToLong(byte[] b) {
        ByteBuffer buffer = ByteBuffer.wrap(b);
        return buffer.getLong();
    }

    /**
     * 
     * @param b
     *            b is a placeholder for the data
     * @return returns data type as a float convertByteToFloat takes in bytes
     *         and changes data type to float
     */
    private float convertByteToFloat(byte[] b)
    {
        ByteBuffer buffer = ByteBuffer.wrap(b);
        return buffer.getFloat();
    }

    /**
     * Takes in bytes 4 at a time as long as they are valid If bytes are not
     * valid, error is given out If correct data type, convert the read in bytes
     * to ints convertByteToInt takes valid bytes in and then converts them into
     * useable ints
     * 
     * @return
     */
    public int readInt()
    {
        byte[] bytesRead = new byte[4];
        int checkIfValid = 0;
        try
        {
            checkIfValid = read(bytesRead);
        }
        catch (IOException e)
        {
            System.out.println("File not read.");
        }

        if (checkIfValid == -1)
            return checkIfValid;
        else
            return convertByteToInt(bytesRead);

    }

    /**
     * Takes in bytes 4 at a time as long as they are valid If bytes are not
     * valid, error is given out If correct data type, convert the read in bytes
     * to float convertByteToFloat takes valid bytes in and then converts them
     * into useable floats
     * 
     * @return
     */
    public float readFloat()
    {
        byte[] bytesRead = new byte[4];
        int checkIfValid = 0;
        try
        {
            checkIfValid = read(bytesRead);
        }
        catch (IOException e)
        {
            System.out.println("File not read.");
        }

        if (checkIfValid == -1)
            return checkIfValid;
        else
            return convertByteToFloat(bytesRead);
    }
    
    public long readLong()
    {
        byte[] bytesRead = new byte[8];
        int checkIfValid = 0;
        try
        {
            checkIfValid = read(bytesRead);
        }
        catch (IOException e)
        {
            System.out.println("File not read.");
        }

        if (checkIfValid == -1)
            return checkIfValid;
        else
            return convertByteToLong(bytesRead);

    }

}