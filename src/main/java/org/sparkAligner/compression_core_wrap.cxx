/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * This file is not intended to be easily readable and contains a number of
 * coding conventions designed to improve portability and efficiency. Do not make
 * changes to this file unless you know what you are doing--modify the SWIG
 * interface file instead.
 * ----------------------------------------------------------------------------- */


#ifndef SWIGJAVA
#define SWIGJAVA
#endif



#ifdef __cplusplus
/* SwigValueWrapper is described in swig.swg */
template<typename T> class SwigValueWrapper {
  struct SwigMovePointer {
    T *ptr;
    SwigMovePointer(T *p) : ptr(p) { }
    ~SwigMovePointer() { delete ptr; }
    SwigMovePointer& operator=(SwigMovePointer& rhs) { T* oldptr = ptr; ptr = 0; delete oldptr; ptr = rhs.ptr; rhs.ptr = 0; return *this; }
  } pointer;
  SwigValueWrapper& operator=(const SwigValueWrapper<T>& rhs);
  SwigValueWrapper(const SwigValueWrapper<T>& rhs);
public:
  SwigValueWrapper() : pointer(0) { }
  SwigValueWrapper& operator=(const T& t) { SwigMovePointer tmp(new T(t)); pointer = tmp; return *this; }
  operator T&() const { return *pointer.ptr; }
  T *operator&() { return pointer.ptr; }
};

template <typename T> T SwigValueInit() {
  return T();
}
#endif

/* -----------------------------------------------------------------------------
 *  This section contains generic SWIG labels for method/variable
 *  declarations/attributes, and other compiler dependent labels.
 * ----------------------------------------------------------------------------- */

/* template workaround for compilers that cannot correctly implement the C++ standard */
#ifndef SWIGTEMPLATEDISAMBIGUATOR
# if defined(__SUNPRO_CC) && (__SUNPRO_CC <= 0x560)
#  define SWIGTEMPLATEDISAMBIGUATOR template
# elif defined(__HP_aCC)
/* Needed even with `aCC -AA' when `aCC -V' reports HP ANSI C++ B3910B A.03.55 */
/* If we find a maximum version that requires this, the test would be __HP_aCC <= 35500 for A.03.55 */
#  define SWIGTEMPLATEDISAMBIGUATOR template
# else
#  define SWIGTEMPLATEDISAMBIGUATOR
# endif
#endif

/* inline attribute */
#ifndef SWIGINLINE
# if defined(__cplusplus) || (defined(__GNUC__) && !defined(__STRICT_ANSI__))
#   define SWIGINLINE inline
# else
#   define SWIGINLINE
# endif
#endif

/* attribute recognised by some compilers to avoid 'unused' warnings */
#ifndef SWIGUNUSED
# if defined(__GNUC__)
#   if !(defined(__cplusplus)) || (__GNUC__ > 3 || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4))
#     define SWIGUNUSED __attribute__ ((__unused__))
#   else
#     define SWIGUNUSED
#   endif
# elif defined(__ICC)
#   define SWIGUNUSED __attribute__ ((__unused__))
# else
#   define SWIGUNUSED
# endif
#endif

#ifndef SWIG_MSC_UNSUPPRESS_4505
# if defined(_MSC_VER)
#   pragma warning(disable : 4505) /* unreferenced local function has been removed */
# endif
#endif

#ifndef SWIGUNUSEDPARM
# ifdef __cplusplus
#   define SWIGUNUSEDPARM(p)
# else
#   define SWIGUNUSEDPARM(p) p SWIGUNUSED
# endif
#endif

/* internal SWIG method */
#ifndef SWIGINTERN
# define SWIGINTERN static SWIGUNUSED
#endif

/* internal inline SWIG method */
#ifndef SWIGINTERNINLINE
# define SWIGINTERNINLINE SWIGINTERN SWIGINLINE
#endif

/* exporting methods */
#if (__GNUC__ >= 4) || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4)
#  ifndef GCC_HASCLASSVISIBILITY
#    define GCC_HASCLASSVISIBILITY
#  endif
#endif

#ifndef SWIGEXPORT
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   if defined(STATIC_LINKED)
#     define SWIGEXPORT
#   else
#     define SWIGEXPORT __declspec(dllexport)
#   endif
# else
#   if defined(__GNUC__) && defined(GCC_HASCLASSVISIBILITY)
#     define SWIGEXPORT __attribute__ ((visibility("default")))
#   else
#     define SWIGEXPORT
#   endif
# endif
#endif

/* calling conventions for Windows */
#ifndef SWIGSTDCALL
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   define SWIGSTDCALL __stdcall
# else
#   define SWIGSTDCALL
# endif
#endif

/* Deal with Microsoft's attempt at deprecating C standard runtime functions */
#if !defined(SWIG_NO_CRT_SECURE_NO_DEPRECATE) && defined(_MSC_VER) && !defined(_CRT_SECURE_NO_DEPRECATE)
# define _CRT_SECURE_NO_DEPRECATE
#endif

/* Deal with Microsoft's attempt at deprecating methods in the standard C++ library */
#if !defined(SWIG_NO_SCL_SECURE_NO_DEPRECATE) && defined(_MSC_VER) && !defined(_SCL_SECURE_NO_DEPRECATE)
# define _SCL_SECURE_NO_DEPRECATE
#endif

/* Deal with Apple's deprecated 'AssertMacros.h' from Carbon-framework */
#if defined(__APPLE__) && !defined(__ASSERT_MACROS_DEFINE_VERSIONS_WITHOUT_UNDERSCORES)
# define __ASSERT_MACROS_DEFINE_VERSIONS_WITHOUT_UNDERSCORES 0
#endif

/* Intel's compiler complains if a variable which was never initialised is
 * cast to void, which is a common idiom which we use to indicate that we
 * are aware a variable isn't used.  So we just silence that warning.
 * See: https://github.com/swig/swig/issues/192 for more discussion.
 */
#ifdef __INTEL_COMPILER
# pragma warning disable 592
#endif


/* Fix for jlong on some versions of gcc on Windows */
#if defined(__GNUC__) && !defined(__INTEL_COMPILER)
  typedef long long __int64;
#endif

/* Fix for jlong on 64-bit x86 Solaris */
#if defined(__x86_64)
# ifdef _LP64
#   undef _LP64
# endif
#endif

#include <jni.h>
#include <stdlib.h>
#include <string.h>


/* Support for throwing Java exceptions */
typedef enum {
  SWIG_JavaOutOfMemoryError = 1, 
  SWIG_JavaIOException, 
  SWIG_JavaRuntimeException, 
  SWIG_JavaIndexOutOfBoundsException,
  SWIG_JavaArithmeticException,
  SWIG_JavaIllegalArgumentException,
  SWIG_JavaNullPointerException,
  SWIG_JavaDirectorPureVirtual,
  SWIG_JavaUnknownError
} SWIG_JavaExceptionCodes;

typedef struct {
  SWIG_JavaExceptionCodes code;
  const char *java_exception;
} SWIG_JavaExceptions_t;


static void SWIGUNUSED SWIG_JavaThrowException(JNIEnv *jenv, SWIG_JavaExceptionCodes code, const char *msg) {
  jclass excep;
  static const SWIG_JavaExceptions_t java_exceptions[] = {
    { SWIG_JavaOutOfMemoryError, "java/lang/OutOfMemoryError" },
    { SWIG_JavaIOException, "java/io/IOException" },
    { SWIG_JavaRuntimeException, "java/lang/RuntimeException" },
    { SWIG_JavaIndexOutOfBoundsException, "java/lang/IndexOutOfBoundsException" },
    { SWIG_JavaArithmeticException, "java/lang/ArithmeticException" },
    { SWIG_JavaIllegalArgumentException, "java/lang/IllegalArgumentException" },
    { SWIG_JavaNullPointerException, "java/lang/NullPointerException" },
    { SWIG_JavaDirectorPureVirtual, "java/lang/RuntimeException" },
    { SWIG_JavaUnknownError,  "java/lang/UnknownError" },
    { (SWIG_JavaExceptionCodes)0,  "java/lang/UnknownError" }
  };
  const SWIG_JavaExceptions_t *except_ptr = java_exceptions;

  while (except_ptr->code != code && except_ptr->code)
    except_ptr++;

  jenv->ExceptionClear();
  excep = jenv->FindClass(except_ptr->java_exception);
  if (excep)
    jenv->ThrowNew(excep, msg);
}


/* Contract support */

#define SWIG_contract_assert(nullreturn, expr, msg) if (!(expr)) {SWIG_JavaThrowException(jenv, SWIG_JavaIllegalArgumentException, msg); return nullreturn; } else


#include <stdint.h>		// Use the C99 official header


static uint8_t *new_uint8_t_array(int nelements) { 
  return new uint8_t[nelements](); 
}

static void delete_uint8_t_array(uint8_t *ary) { 
  delete [] ary; 
}

static uint8_t uint8_t_array_getitem(uint8_t *ary, int index) {
    return ary[index];
}
static void uint8_t_array_setitem(uint8_t *ary, int index, uint8_t value) {
    ary[index] = value;
}


static uint64_t *new_uint64_t_array(int nelements) { 
  return new uint64_t[nelements](); 
}

static void delete_uint64_t_array(uint64_t *ary) { 
  delete [] ary; 
}

static uint64_t uint64_t_array_getitem(uint64_t *ary, int index) {
    return ary[index];
}
static void uint64_t_array_setitem(uint64_t *ary, int index, uint64_t value) {
    ary[index] = value;
}


static unsigned long long *new_ull_array(int nelements) { 
  return new unsigned long long[nelements](); 
}

static void delete_ull_array(unsigned long long *ary) { 
  delete [] ary; 
}

static unsigned long long ull_array_getitem(unsigned long long *ary, int index) {
    return ary[index];
}
static void ull_array_setitem(unsigned long long *ary, int index, unsigned long long value) {
    ary[index] = value;
}


#include "Maxfiles.h"
#include "MaxSLiCInterface.h"
#include "time.h"   
#include "stdio.h"
#include <stdint.h>


    
    const int MAX_FPGA_NUM = 8;
    max_engine_t *engine[MAX_FPGA_NUM];
    max_file_t *maxFile;
    
    void loadFPGAs(int num) {
        maxFile = Lz77Compress_init();
        for(int i = 0; i < num; i++) {
            engine[i] = max_load(maxFile, "*");
        }
    }
    
    void unloadFPGAs(int num) {
        for(int i = 0; i < num; i++) {
            max_unload(engine[i]);
        }
        max_file_free(maxFile);
    }
    
    uint64_t Lz77CompressOverall( char* dataInJava, int length, int dfe_id ) {
        
        int64_t param_N = (int64_t) length;

        Lz77Compress_WriteLmem_actions_t writeAction;
        writeAction.param_N = param_N;
        writeAction.instream_input = (uint8_t* ) dataInJava;
        Lz77Compress_WriteLmem_run(engine[dfe_id], &writeAction);

        Lz77Compress_actions_t compressAction;
        compressAction.param_N = param_N;
        uint64_t total_len = 0;
        compressAction.outscalar_WriteEncodedDataKernel_totalLen = &total_len;
        Lz77Compress_run(engine[dfe_id], &compressAction);

        uint64_t* encoded_res = new uint64_t[param_N / 8];      
        Lz77Compress_ReadLmem_actions_t readAction;
        readAction.param_N = param_N;
        readAction.outstream_dataToCPU = encoded_res;
        Lz77Compress_ReadLmem_run(engine[dfe_id], &readAction);
        
        int len_output = total_len / 8;
        if(total_len % 8 != 0) {
            len_output ++;
        }
        int num_c = 0;
        for(int i = 0; i < param_N; i++) {
            for(int j = 0; j < 8; j++) {
                dataInJava[num_c++] = (uint8_t)( encoded_res[i] >> 8*(7-j));
                if(num_c == len_output)
                    break;
            }
            if(num_c == len_output)
                break;
        }
        return len_output;
    }


    uint64_t Lz77Compress_C_Write_Compress(int64_t param_N, const uint8_t *instream_input, int dfe_id) {
        Lz77Compress_WriteLmem_actions_t writeAction;
        writeAction.param_N = param_N;
        writeAction.instream_input = instream_input;
        Lz77Compress_WriteLmem_run(engine[dfe_id], &writeAction);
        
        Lz77Compress_actions_t compressAction;
        compressAction.param_N = param_N;

        uint64_t total_len = 0;
        compressAction.outscalar_WriteEncodedDataKernel_totalLen = &total_len;
        Lz77Compress_run(engine[dfe_id], &compressAction);
        
        return total_len;
    }
    
    uint8_t* Lz77Compress_C_ReadLmem_helperfunc(int64_t param_N, uint64_t total_len, int dfe_id) {
        
        uint8_t* result = new uint8_t[param_N];
        uint64_t* encoded_res = new uint64_t[param_N / 8];
        
        Lz77Compress_ReadLmem_actions_t readAction;
        readAction.param_N = param_N;
        readAction.outstream_dataToCPU = encoded_res;
        Lz77Compress_ReadLmem_run(engine[dfe_id], &readAction);
        
        int len_output = total_len / 8;
        if(total_len % 8 != 0) {
            len_output ++;
        }
        int num_c = 0;
        for(int i = 0; i < param_N; i++) {
            for(int j = 0; j < 8; j++) {
                result[num_c++] = (uint8_t)( encoded_res[i] >> 8*(7-j));
                if(num_c == len_output)
                    break;
            }
            if(num_c == len_output)
                break;
        }
        return result;
    }



#ifdef __cplusplus
extern "C" {
#endif

SWIGEXPORT jlong JNICALL Java_org_sparkAligner_compression_1coreJNI_new_1uint8_1t_1array(JNIEnv *jenv, jclass jcls, jint jarg1) {
  jlong jresult = 0 ;
  int arg1 ;
  uint8_t *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  result = (uint8_t *)new_uint8_t_array(arg1);
  *(uint8_t **)&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_delete_1uint8_1t_1array(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  uint8_t *arg1 = (uint8_t *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(uint8_t **)&jarg1; 
  delete_uint8_t_array(arg1);
}


SWIGEXPORT jshort JNICALL Java_org_sparkAligner_compression_1coreJNI_uint8_1t_1array_1getitem(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
  jshort jresult = 0 ;
  uint8_t *arg1 = (uint8_t *) 0 ;
  int arg2 ;
  uint8_t result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(uint8_t **)&jarg1; 
  arg2 = (int)jarg2; 
  result = (uint8_t)uint8_t_array_getitem(arg1,arg2);
  jresult = (jshort)result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_uint8_1t_1array_1setitem(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jshort jarg3) {
  uint8_t *arg1 = (uint8_t *) 0 ;
  int arg2 ;
  uint8_t arg3 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(uint8_t **)&jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (uint8_t)jarg3; 
  uint8_t_array_setitem(arg1,arg2,arg3);
}


SWIGEXPORT jlong JNICALL Java_org_sparkAligner_compression_1coreJNI_new_1uint64_1t_1array(JNIEnv *jenv, jclass jcls, jint jarg1) {
  jlong jresult = 0 ;
  int arg1 ;
  uint64_t *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  result = (uint64_t *)new_uint64_t_array(arg1);
  *(uint64_t **)&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_delete_1uint64_1t_1array(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  uint64_t *arg1 = (uint64_t *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(uint64_t **)&jarg1; 
  delete_uint64_t_array(arg1);
}


SWIGEXPORT jobject JNICALL Java_org_sparkAligner_compression_1coreJNI_uint64_1t_1array_1getitem(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
  jobject jresult = 0 ;
  uint64_t *arg1 = (uint64_t *) 0 ;
  int arg2 ;
  uint64_t result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(uint64_t **)&jarg1; 
  arg2 = (int)jarg2; 
  result = (uint64_t)uint64_t_array_getitem(arg1,arg2);
  {
    jbyteArray ba = jenv->NewByteArray(9);
    jbyte* bae = jenv->GetByteArrayElements(ba, 0);
    jclass clazz = jenv->FindClass("java/math/BigInteger");
    jmethodID mid = jenv->GetMethodID(clazz, "<init>", "([B)V");
    jobject bigint;
    int i;
    
    bae[0] = 0;
    for(i=1; i<9; i++ ) {
      bae[i] = (jbyte)(result>>8*(8-i));
    }
    
    jenv->ReleaseByteArrayElements(ba, bae, 0);
    bigint = jenv->NewObject(clazz, mid, ba);
    jresult = bigint;
  }
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_uint64_1t_1array_1setitem(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3) {
  uint64_t *arg1 = (uint64_t *) 0 ;
  int arg2 ;
  uint64_t arg3 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(uint64_t **)&jarg1; 
  arg2 = (int)jarg2; 
  {
    jclass clazz;
    jmethodID mid;
    jbyteArray ba;
    jbyte* bae;
    jsize sz;
    int i;
    
    if (!jarg3) {
      SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "BigInteger null");
      return ;
    }
    clazz = jenv->GetObjectClass(jarg3);
    mid = jenv->GetMethodID(clazz, "toByteArray", "()[B");
    ba = (jbyteArray)jenv->CallObjectMethod(jarg3, mid);
    bae = jenv->GetByteArrayElements(ba, 0);
    sz = jenv->GetArrayLength(ba);
    arg3 = 0;
    for(i=0; i<sz; i++) {
      arg3 = (arg3 << 8) | (uint64_t)(unsigned char)bae[i];
    }
    jenv->ReleaseByteArrayElements(ba, bae, 0);
  }
  uint64_t_array_setitem(arg1,arg2,arg3);
}


SWIGEXPORT jlong JNICALL Java_org_sparkAligner_compression_1coreJNI_new_1ull_1array(JNIEnv *jenv, jclass jcls, jint jarg1) {
  jlong jresult = 0 ;
  int arg1 ;
  unsigned long long *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  result = (unsigned long long *)new_ull_array(arg1);
  *(unsigned long long **)&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_delete_1ull_1array(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  unsigned long long *arg1 = (unsigned long long *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(unsigned long long **)&jarg1; 
  delete_ull_array(arg1);
}


SWIGEXPORT jobject JNICALL Java_org_sparkAligner_compression_1coreJNI_ull_1array_1getitem(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
  jobject jresult = 0 ;
  unsigned long long *arg1 = (unsigned long long *) 0 ;
  int arg2 ;
  unsigned long long result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(unsigned long long **)&jarg1; 
  arg2 = (int)jarg2; 
  result = (unsigned long long)ull_array_getitem(arg1,arg2);
  {
    jbyteArray ba = jenv->NewByteArray(9);
    jbyte* bae = jenv->GetByteArrayElements(ba, 0);
    jclass clazz = jenv->FindClass("java/math/BigInteger");
    jmethodID mid = jenv->GetMethodID(clazz, "<init>", "([B)V");
    jobject bigint;
    int i;
    
    bae[0] = 0;
    for(i=1; i<9; i++ ) {
      bae[i] = (jbyte)(result>>8*(8-i));
    }
    
    jenv->ReleaseByteArrayElements(ba, bae, 0);
    bigint = jenv->NewObject(clazz, mid, ba);
    jresult = bigint;
  }
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_ull_1array_1setitem(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3) {
  unsigned long long *arg1 = (unsigned long long *) 0 ;
  int arg2 ;
  unsigned long long arg3 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(unsigned long long **)&jarg1; 
  arg2 = (int)jarg2; 
  {
    jclass clazz;
    jmethodID mid;
    jbyteArray ba;
    jbyte* bae;
    jsize sz;
    int i;
    
    if (!jarg3) {
      SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "BigInteger null");
      return ;
    }
    clazz = jenv->GetObjectClass(jarg3);
    mid = jenv->GetMethodID(clazz, "toByteArray", "()[B");
    ba = (jbyteArray)jenv->CallObjectMethod(jarg3, mid);
    bae = jenv->GetByteArrayElements(ba, 0);
    sz = jenv->GetArrayLength(ba);
    arg3 = 0;
    for(i=0; i<sz; i++) {
      arg3 = (arg3 << 8) | (unsigned long long)(unsigned char)bae[i];
    }
    jenv->ReleaseByteArrayElements(ba, bae, 0);
  }
  ull_array_setitem(arg1,arg2,arg3);
}


SWIGEXPORT jint JNICALL Java_org_sparkAligner_compression_1coreJNI_MAX_1FPGA_1NUM_1get(JNIEnv *jenv, jclass jcls) {
  jint jresult = 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  result = (int)(int)MAX_FPGA_NUM;
  jresult = (jint)result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_engine_1set(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  max_engine_t **arg1 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(max_engine_t ***)&jarg1; 
  {
    size_t ii;
    max_engine_t * *b = (max_engine_t * *) engine;
    for (ii = 0; ii < (size_t)MAX_FPGA_NUM; ii++) b[ii] = *((max_engine_t * *) arg1 + ii);
  }
  
}


SWIGEXPORT jlong JNICALL Java_org_sparkAligner_compression_1coreJNI_engine_1get(JNIEnv *jenv, jclass jcls) {
  jlong jresult = 0 ;
  max_engine_t **result = 0 ;
  
  (void)jenv;
  (void)jcls;
  result = (max_engine_t **)(max_engine_t **)engine;
  *(max_engine_t ***)&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_maxFile_1set(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  max_file_t *arg1 = (max_file_t *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(max_file_t **)&jarg1; 
  maxFile = arg1;
}


SWIGEXPORT jlong JNICALL Java_org_sparkAligner_compression_1coreJNI_maxFile_1get(JNIEnv *jenv, jclass jcls) {
  jlong jresult = 0 ;
  max_file_t *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  result = (max_file_t *)maxFile;
  *(max_file_t **)&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_loadFPGAs(JNIEnv *jenv, jclass jcls, jint jarg1) {
  int arg1 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  loadFPGAs(arg1);
}


SWIGEXPORT void JNICALL Java_org_sparkAligner_compression_1coreJNI_unloadFPGAs(JNIEnv *jenv, jclass jcls, jint jarg1) {
  int arg1 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  unloadFPGAs(arg1);
}


SWIGEXPORT jobject JNICALL Java_org_sparkAligner_compression_1coreJNI_Lz77CompressOverall(JNIEnv *jenv, jclass jcls, jbyteArray jarg1, jint jarg2, jint jarg3) {
  jobject jresult = 0 ;
  char *arg1 = (char *) 0 ;
  int arg2 ;
  int arg3 ;
  uint64_t result;
  
  (void)jenv;
  (void)jcls;
  {
    arg1 = (char *) jenv->GetByteArrayElements(jarg1, 0); 
  }
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  result = (uint64_t)Lz77CompressOverall(arg1,arg2,arg3);
  {
    jbyteArray ba = jenv->NewByteArray(9);
    jbyte* bae = jenv->GetByteArrayElements(ba, 0);
    jclass clazz = jenv->FindClass("java/math/BigInteger");
    jmethodID mid = jenv->GetMethodID(clazz, "<init>", "([B)V");
    jobject bigint;
    int i;
    
    bae[0] = 0;
    for(i=1; i<9; i++ ) {
      bae[i] = (jbyte)(result>>8*(8-i));
    }
    
    jenv->ReleaseByteArrayElements(ba, bae, 0);
    bigint = jenv->NewObject(clazz, mid, ba);
    jresult = bigint;
  }
  {
    jenv->ReleaseByteArrayElements(jarg1, (jbyte *) arg1, 0); 
  }
  
  return jresult;
}


SWIGEXPORT jobject JNICALL Java_org_sparkAligner_compression_1coreJNI_Lz77Compress_1C_1Write_1Compress(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
  jobject jresult = 0 ;
  int64_t arg1 ;
  uint8_t *arg2 = (uint8_t *) 0 ;
  int arg3 ;
  uint64_t result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int64_t)jarg1; 
  arg2 = *(uint8_t **)&jarg2; 
  arg3 = (int)jarg3; 
  result = (uint64_t)Lz77Compress_C_Write_Compress(arg1,(unsigned char const *)arg2,arg3);
  {
    jbyteArray ba = jenv->NewByteArray(9);
    jbyte* bae = jenv->GetByteArrayElements(ba, 0);
    jclass clazz = jenv->FindClass("java/math/BigInteger");
    jmethodID mid = jenv->GetMethodID(clazz, "<init>", "([B)V");
    jobject bigint;
    int i;
    
    bae[0] = 0;
    for(i=1; i<9; i++ ) {
      bae[i] = (jbyte)(result>>8*(8-i));
    }
    
    jenv->ReleaseByteArrayElements(ba, bae, 0);
    bigint = jenv->NewObject(clazz, mid, ba);
    jresult = bigint;
  }
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_org_sparkAligner_compression_1coreJNI_Lz77Compress_1C_1ReadLmem_1helperfunc(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3) {
  jlong jresult = 0 ;
  int64_t arg1 ;
  uint64_t arg2 ;
  int arg3 ;
  uint8_t *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int64_t)jarg1; 
  {
    jclass clazz;
    jmethodID mid;
    jbyteArray ba;
    jbyte* bae;
    jsize sz;
    int i;
    
    if (!jarg2) {
      SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "BigInteger null");
      return 0;
    }
    clazz = jenv->GetObjectClass(jarg2);
    mid = jenv->GetMethodID(clazz, "toByteArray", "()[B");
    ba = (jbyteArray)jenv->CallObjectMethod(jarg2, mid);
    bae = jenv->GetByteArrayElements(ba, 0);
    sz = jenv->GetArrayLength(ba);
    arg2 = 0;
    for(i=0; i<sz; i++) {
      arg2 = (arg2 << 8) | (uint64_t)(unsigned char)bae[i];
    }
    jenv->ReleaseByteArrayElements(ba, bae, 0);
  }
  arg3 = (int)jarg3; 
  result = (uint8_t *)Lz77Compress_C_ReadLmem_helperfunc(arg1,arg2,arg3);
  *(uint8_t **)&jresult = result; 
  return jresult;
}


#ifdef __cplusplus
}
#endif

