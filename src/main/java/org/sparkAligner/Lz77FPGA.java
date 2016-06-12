package org.sparkAligner;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class Lz77FPGA {
    
    static {
        try {
        System.loadLibrary("compression_core");
        } catch (UnsatisfiedLinkError e) {
          System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
          System.exit(1);
        }
    }
    
    static long timeStart = -1;
    static long timeMark = -1;
    static String elapsedTimeInSeconds(){
        long timeNow = System.currentTimeMillis();
        long elapsedTime = (timeNow - timeMark);
        timeMark = timeNow;
        
        return String.format("%.3f seconds", elapsedTime/1000.0);
    }
    
public static byte[] compress(SWIGTYPE_p_unsigned_char FPGAinput, int length, WritableByteChannel outChannel) throws IOException {
		
		timeStart = System.currentTimeMillis();
		timeMark = timeStart;
		int input_bytes_len = length / 384 * 384;
		System.out.println( "Input bytes = " + length + " truncated to " + input_bytes_len);
		
	    compression_core.Lz77Compress_WriteLmem(input_bytes_len, FPGAinput);
	    System.out.println("WriteLmem time:" + elapsedTimeInSeconds());
	    
	    BigInteger output_bits_num = compression_core.Lz77Compress_helperfunc(input_bytes_len);
	    System.out.println("Compress time:" + elapsedTimeInSeconds());
	    
	    SWIGTYPE_p_unsigned_char encoded_bytes = compression_core.Lz77Compress_ReadLmem_helperfunc(input_bytes_len, output_bits_num);
	    System.out.println("ReadLmem time:" + elapsedTimeInSeconds());
	    
	    BigInteger compressed_byte_num_bi = output_bits_num.divide(BigInteger.valueOf(8L));
	    if(output_bits_num.mod(BigInteger.valueOf(8L)).intValue()!= 0) {
	    	compressed_byte_num_bi.add(BigInteger.ONE);
	    }
	    
	    int compressed_byte_num = compressed_byte_num_bi.intValue();
	    System.out.println( "Output " + output_bits_num + " bits, i.e."+compressed_byte_num+" bytes");
	    System.out.println( String.format("Compression ratio: %.3f", 1.0*input_bytes_len / compressed_byte_num ) );
	    
	    
	    int outputBulkSize = 1024 * 1024 * 512; 
		byte[] outputBulk = new byte[outputBulkSize];
		for(int i = 0 ; i < compressed_byte_num ; i++){	
			outputBulk[i % outputBulkSize] = (byte)compression_core.uint8_t_array_getitem(encoded_bytes, i) ;
			if( ((i+1) % outputBulkSize) == 0 ) {
				outChannel.write(ByteBuffer.wrap(outputBulk));
			}
		}
		int left = compressed_byte_num % outputBulkSize; 
		if( left != 0) {
			outChannel.write(ByteBuffer.wrap(outputBulk, 0, left));
		}
		
		System.out.println("CopyOut time:" + elapsedTimeInSeconds());
		System.out.println("Total time:" + String.format("%.3f seconds", (System.currentTimeMillis()- timeStart)/1000.0));
		return null;
	}

}

