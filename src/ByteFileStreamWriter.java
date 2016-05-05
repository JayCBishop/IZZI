
/**
 * Members are listed in Main.java
 * Software Design, Spring 2016
 * @author- Group G
 * Created 5/1/2016
 * 
 * Writes out a byte file with helpful conversion classes
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteFileStreamWriter extends FileOutputStream
{
    /**
     * Constructor that just calls super
     * 
     * @param file
     * @throws FileNotFoundException
     */
    public ByteFileStreamWriter(File file) throws FileNotFoundException
    {
        super(file);
    }

    /**
     * Converts int to a byte array
     * 
     * @param int
     * @return
     */
    private byte[] intToByte(int i)
    {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putInt(i);
        return buffer.array();
    }

    /**
     * Converts float to a byte array
     * 
     * @param float
     * @return
     */
    private byte[] floatToByte(float f)
    {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putFloat(f);
        return buffer.array();
    }
    
    /**
     * Converts long to a byte array
     * 
     * @param long
     * @return
     */
    private byte[] longToByte(long l)
    {
    	byte[] bytes = new byte[8];
    	ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
    	buffer.putLong(l);
    	return buffer.array();
    }

    /**
     * Writes int to a file in bytes
     * 
     * @param int
     * @throws IOException
     */
    public void writeInt(int i) throws IOException
    {
        write(intToByte(i));
    }

    /**
     * Writes float to a file in bytes
     * 
     * @param float
     * @throws IOException
     */
    public void writeFloat(float f) throws IOException
    {
        write(floatToByte(f));
    }
    
    /**
     * Writes long to a file in bytes
     * 
     * @param long
     * @throws IOException
     */
    public void writeLong(long l) throws IOException
    {
        write(longToByte(l));
    }
}
