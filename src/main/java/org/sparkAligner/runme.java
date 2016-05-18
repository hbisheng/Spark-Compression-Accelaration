package org.sparkAligner;

public class runme {

  static {
    try {
    System.loadLibrary("compression_core");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
      System.exit(1);
    }
  }

  public static void main(String argv[]) {
    
    System.out.println("------------------Start of Java program------------------");
    
    int input_bytes_len = 38400;
    
    SWIGTYPE_p_unsigned_char input = compression_core.new_uint8_t_array(input_bytes_len);
    for(int i = 0; i < input_bytes_len; i++) {
        compression_core.uint8_t_array_setitem(input, i, (short) (64 + (i%5)) );
    }
    
    compression_core.Lz77Compress_WriteLmem(input_bytes_len, input);
    java.math.BigInteger output_bits_num = compression_core.Lz77Compress_helperfunc(input_bytes_len);
    
    //SWIGTYPE_p_unsigned_long_long output = compression_core.Lz77Compress_ReadLmem_helperfunc(input_len);
    
    SWIGTYPE_p_unsigned_char encoded_bytes = compression_core.Lz77Compress_ReadLmem_helperfunc(input_bytes_len, output_bits_num);
    
    System.out.println( "Output bits: " + output_bits_num );
    System.out.println( String.format("Compression ratio: %.3f", input_bytes_len / (output_bits_num.doubleValue() / 8.0)) );
    for(int i = 0; i < 10; i++) {
      System.out.println( compression_core.uint8_t_array_getitem(encoded_bytes, i) );
    }
    
    System.out.println("------------------End of Java program------------------");
  }
}

