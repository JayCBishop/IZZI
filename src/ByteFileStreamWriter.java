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

    public ByteFileStreamWriter(File file) throws FileNotFoundException
    {
        super(file);
    }

    private byte[] intToByte(int i)
    {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putInt(i);
        return buffer.array();
    }

    private byte[] floatToByte(float f)
    {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putFloat(f);
        return buffer.array();
    }
    
    public void writeInt(int i) throws IOException
    {
        write(intToByte(i));
    }
    
    public void writeFloat(float f) throws IOException
    {
        write(floatToByte(f));
    }
}
