package org.sparkAligner;
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class compression_coreJNI {
  public final static native long new_uint8_t_array(int jarg1);
  public final static native void delete_uint8_t_array(long jarg1);
  public final static native short uint8_t_array_getitem(long jarg1, int jarg2);
  public final static native void uint8_t_array_setitem(long jarg1, int jarg2, short jarg3);
  public final static native long new_uint64_t_array(int jarg1);
  public final static native void delete_uint64_t_array(long jarg1);
  public final static native java.math.BigInteger uint64_t_array_getitem(long jarg1, int jarg2);
  public final static native void uint64_t_array_setitem(long jarg1, int jarg2, java.math.BigInteger jarg3);
  public final static native long new_ull_array(int jarg1);
  public final static native void delete_ull_array(long jarg1);
  public final static native java.math.BigInteger ull_array_getitem(long jarg1, int jarg2);
  public final static native void ull_array_setitem(long jarg1, int jarg2, java.math.BigInteger jarg3);
  public final static native void Lz77Compress_WriteLmem(long jarg1, long jarg2);
  public final static native void Lz77Compress(long jarg1, long jarg2);
  public final static native void Lz77Compress_ReadLmem(long jarg1, long jarg2);
  public final static native java.math.BigInteger Lz77Compress_helperfunc(long jarg1);
  public final static native long Lz77Compress_ReadLmem_helperfunc(long jarg1, java.math.BigInteger jarg2);
}
