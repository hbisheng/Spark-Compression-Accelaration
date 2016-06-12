package org.sparkAligner;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class Lz77FPGA {
        
    public static byte[] compress2(SWIGTYPE_p_unsigned_char FPGAinput, int length, WritableByteChannel outChannel) throws IOException {
    	
		
		int input_bytes_len = length / 384 * 384;
		//System.out.println( "Input bytes = " + length + " truncated to " + input_bytes_len);
		
		int dfe_id = FPGAController.getFPGAGrant();
		BigInteger output_bits_num = compression_core.Lz77Compress_C_Write_Compress(input_bytes_len, FPGAinput, dfe_id);
		//System.out.println("WriteAndCompress time:" + elapsedTimeInSeconds());
		
		SWIGTYPE_p_unsigned_char encoded_bytes = compression_core.Lz77Compress_C_ReadLmem_helperfunc(input_bytes_len, output_bits_num, dfe_id);
		FPGAController.releaseFPGAGrant(dfe_id);
	    //System.out.println("ReadLmem time:" + elapsedTimeInSeconds());
	    
	    BigInteger compressed_byte_num_bi = output_bits_num.divide(BigInteger.valueOf(8L));
	    if(output_bits_num.mod(BigInteger.valueOf(8L)).intValue()!= 0) {
	    	compressed_byte_num_bi.add(BigInteger.ONE);
	    }
	    
	    int compressed_byte_num = compressed_byte_num_bi.intValue();
	    //System.out.println( "Output " + output_bits_num + " bits, i.e."+compressed_byte_num+" bytes");
	    //System.out.println( String.format("Compression ratio: %.3f", 1.0*input_bytes_len / compressed_byte_num ) );
	    
	    
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
		
		//System.out.println("CopyOut time:" + elapsedTimeInSeconds());
		//System.out.println("Total time:" + String.format("%.3f seconds", (System.currentTimeMillis()- timeStart)/1000.0));
		return null;
	}

	public static void FPGACompress(byte[] backingArray, int length, OutputStream mOut) throws IOException {
		
		Clock.start();
		int input_bytes_len = length / 384 * 384;
		System.out.println( "Data arrive: Input bytes = " + length + " truncated to " + input_bytes_len);
		
		int dfe_id = FPGAController.getFPGAGrant();
		System.out.println("Get DFE No. " + dfe_id);
		
		int compressedSize = compression_core.Lz77CompressOverall(backingArray, input_bytes_len, dfe_id);
		mOut.write(backingArray, 0, compressedSize);
	}

}

