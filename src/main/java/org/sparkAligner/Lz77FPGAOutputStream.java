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
	
	OutputStream mOut;
    //private final WritableByteChannel outChannel;

    // private int position;
    private boolean closed;

    private final int MAX_INPUT_SIZE = 2047 * 1024 * 1024;
    
    private final ByteBuffer buffer = ByteBuffer.allocate(MAX_INPUT_SIZE);
    public Lz77FPGAOutputStream(OutputStream out)
            throws IOException
    {
    	this.mOut = out;
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
        
        buffer.put((byte) b);
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
        
        buffer.put(input, offset, length);
    }

    @Override
    public int write(ByteBuffer src)
            throws IOException
    {
        if (closed) {
            throw new ClosedChannelException();
        }

        
        int srcLength = src.remaining();
        
        buffer.put(src);
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
            mOut.close();
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
        if (buffer.position() > 0) {
        	buffer.flip();
            writeCompressed(buffer);
            buffer.clear();
        }
    }

    private void writeCompressed(ByteBuffer rawBuffer)
            throws IOException
    {
    	byte[] backingArray = rawBuffer.array();
    	int length = rawBuffer.remaining();
    	Lz77FPGA.FPGACompress(backingArray, length, mOut);
    }   
}
