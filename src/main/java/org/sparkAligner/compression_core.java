package org.sparkAligner;
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class compression_core {
  public static SWIGTYPE_p_unsigned_char new_uint8_t_array(int nelements) {
    long cPtr = compression_coreJNI.new_uint8_t_array(nelements);
    return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_char(cPtr, false);
  }

  public static void delete_uint8_t_array(SWIGTYPE_p_unsigned_char ary) {
    compression_coreJNI.delete_uint8_t_array(SWIGTYPE_p_unsigned_char.getCPtr(ary));
  }

  public static short uint8_t_array_getitem(SWIGTYPE_p_unsigned_char ary, int index) {
    return compression_coreJNI.uint8_t_array_getitem(SWIGTYPE_p_unsigned_char.getCPtr(ary), index);
  }

  public static void uint8_t_array_setitem(SWIGTYPE_p_unsigned_char ary, int index, short value) {
    compression_coreJNI.uint8_t_array_setitem(SWIGTYPE_p_unsigned_char.getCPtr(ary), index, value);
  }

  public static SWIGTYPE_p_unsigned_long_long new_uint64_t_array(int nelements) {
    long cPtr = compression_coreJNI.new_uint64_t_array(nelements);
    return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_long_long(cPtr, false);
  }

  public static void delete_uint64_t_array(SWIGTYPE_p_unsigned_long_long ary) {
    compression_coreJNI.delete_uint64_t_array(SWIGTYPE_p_unsigned_long_long.getCPtr(ary));
  }

  public static java.math.BigInteger uint64_t_array_getitem(SWIGTYPE_p_unsigned_long_long ary, int index) {
    return compression_coreJNI.uint64_t_array_getitem(SWIGTYPE_p_unsigned_long_long.getCPtr(ary), index);
  }

  public static void uint64_t_array_setitem(SWIGTYPE_p_unsigned_long_long ary, int index, java.math.BigInteger value) {
    compression_coreJNI.uint64_t_array_setitem(SWIGTYPE_p_unsigned_long_long.getCPtr(ary), index, value);
  }

  public static SWIGTYPE_p_unsigned_long_long new_ull_array(int nelements) {
    long cPtr = compression_coreJNI.new_ull_array(nelements);
    return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_long_long(cPtr, false);
  }

  public static void delete_ull_array(SWIGTYPE_p_unsigned_long_long ary) {
    compression_coreJNI.delete_ull_array(SWIGTYPE_p_unsigned_long_long.getCPtr(ary));
  }

  public static java.math.BigInteger ull_array_getitem(SWIGTYPE_p_unsigned_long_long ary, int index) {
    return compression_coreJNI.ull_array_getitem(SWIGTYPE_p_unsigned_long_long.getCPtr(ary), index);
  }

  public static void ull_array_setitem(SWIGTYPE_p_unsigned_long_long ary, int index, java.math.BigInteger value) {
    compression_coreJNI.ull_array_setitem(SWIGTYPE_p_unsigned_long_long.getCPtr(ary), index, value);
  }

  public static void Lz77Compress_WriteLmem(long param_N, SWIGTYPE_p_unsigned_char instream_input) {
    compression_coreJNI.Lz77Compress_WriteLmem(param_N, SWIGTYPE_p_unsigned_char.getCPtr(instream_input));
  }

  public static void Lz77Compress(long param_N, SWIGTYPE_p_unsigned_long_long outscalar_WriteEncodedDataKernel_totalLen) {
    compression_coreJNI.Lz77Compress(param_N, SWIGTYPE_p_unsigned_long_long.getCPtr(outscalar_WriteEncodedDataKernel_totalLen));
  }

  public static void Lz77Compress_ReadLmem(long param_N, SWIGTYPE_p_unsigned_long_long outstream_dataToCPU) {
    compression_coreJNI.Lz77Compress_ReadLmem(param_N, SWIGTYPE_p_unsigned_long_long.getCPtr(outstream_dataToCPU));
  }

  public static java.math.BigInteger Lz77Compress_helperfunc(long param_N) {
    return compression_coreJNI.Lz77Compress_helperfunc(param_N);
  }

  public static SWIGTYPE_p_unsigned_char Lz77Compress_ReadLmem_helperfunc(long param_N, java.math.BigInteger total_len) {
    long cPtr = compression_coreJNI.Lz77Compress_ReadLmem_helperfunc(param_N, total_len);
    return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_char(cPtr, false);
  }

}
