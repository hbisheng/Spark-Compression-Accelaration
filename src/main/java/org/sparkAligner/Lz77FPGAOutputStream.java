package org.sparkAligner;

import static org.sparkAligner.SnappyFramed.COMPRESSED_DATA_FLAG;
import static org.sparkAligner.SnappyFramed.HEADER_BYTES;
import static org.sparkAligner.SnappyFramed.UNCOMPRESSED_DATA_FLAG;
import static org.sparkAligner.SnappyFramed.maskedCrc32c;
import static org.sparkAligner.SnappyFramed.releaseDirectByteBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public final class Lz77FPGAOutputStream
        extends OutputStream
        implements
        WritableByteChannel
{
	static {
	    try {
	    System.loadLibrary("compression_core");
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
	      System.exit(1);
	    }
	}
	
	
    private final WritableByteChannel outChannel;

    // private int position;
    private boolean closed;

    private int inputLength = 0;
    SWIGTYPE_p_unsigned_char FPGAinput;
    private final int MAX_INPUT_SIZE = 2047 * 1024 * 1024;
    
    public Lz77FPGAOutputStream(OutputStream out)
            throws IOException
    {
    	WritableByteChannel outChannel = Channels.newChannel(out);
    	if (outChannel == null) {
            throw new NullPointerException();
        }
    	this.outChannel = outChannel;
		FPGAinput = compression_core.new_uint8_t_array(MAX_INPUT_SIZE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen()
    {
        return !closed;
    }

    @Override
    public void write(int b)
            throws IOException
    {
        if (closed) {
            throw new IOException("Stream is closed");
        }
        if(inputLength + 1 > MAX_INPUT_SIZE) {
    	  System.err.println("FPGA input size exceeds MAX_INPUT_SIZE(" + MAX_INPUT_SIZE + ").\n");
	      System.exit(1);
        }
        
        compression_core.uint8_t_array_setitem(FPGAinput, inputLength++, (byte) b);
    }

    @Override
    public void write(byte[] input, int offset, int length)
            throws IOException
    {
    	
        if (closed) {
            throw new IOException("Stream is closed");
        }

        if (input == null) {
            throw new NullPointerException();
        }
        else if ((offset < 0) || (offset > input.length) || (length < 0)
                || ((offset + length) > input.length)
                || ((offset + length) < 0)) {
            throw new IndexOutOfBoundsException();
        }
        
        if(inputLength+length > MAX_INPUT_SIZE) {
    	  System.err.println("FPGA input size exceeds MAX_INPUT_SIZE(" + MAX_INPUT_SIZE + ").\n");
	      System.exit(1);
        }
        
        for(int i = 0; i < length; i++) {
        	compression_core.uint8_t_array_setitem(FPGAinput, inputLength++, input[offset+i]);
        }

    }

    @Override
    public int write(ByteBuffer src)
            throws IOException
    {
        if (closed) {
            throw new ClosedChannelException();
        }

        byte[] srcArray = src.array();
        int srcOffset = src.position();
        int srcLength = src.remaining();
        
        if(inputLength+srcLength > MAX_INPUT_SIZE) {
      	  System.err.println("FPGA input size exceeds MAX_INPUT_SIZE(" + MAX_INPUT_SIZE + ").\n");
  	      System.exit(1);
          }
        
        for(int i = 0; i < srcLength; i++) {
        	compression_core.uint8_t_array_setitem(FPGAinput, inputLength++, srcArray[srcOffset+i]);
        }

        return srcLength;
    }

    @Override
    public final void flush()
            throws IOException
    {
        if (closed) {
            throw new IOException("Stream is closed");
        }
        flushBuffer();
    }

    @Override
    public final void close()
            throws IOException
    {
        if (closed) {
            return;
        }
        
        try {
            flush();
            outChannel.close();
        }
        finally {
            closed = true;
        }
    }

    /**
     * Compresses and writes out any buffered data. This does nothing if there
     * is no currently buffered data.
     *
     * @throws IOException
     */
    private void flushBuffer()
            throws IOException
    {
        if (inputLength > 0) {
            writeCompressed();
            inputLength = 0;
        }
    }

    private void writeCompressed()
            throws IOException
    {
    	int i = FPGAController.getFPGAGrant();
    	try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	FPGAController.releaseFPGAGrant(i);
    	
		//byte[] compressedBytes = Lz77FPGA.compress(FPGAinput, inputLength, outChannel);
        
    	//outChannel.write(ByteBuffer.wrap(compressedBytes));  
    
    }   
}
