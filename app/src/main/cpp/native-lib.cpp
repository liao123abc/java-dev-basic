#include <jni.h>
#include <string>
#include <android/log.h>

#define DEBUG_TAG "NDK_AndroidNDK1SampleActivity"


char* InsertSort(int a[], int n);
jstring test222(JNIEnv* env);
jstring stoJstring(JNIEnv* env, const char* pat);

extern "C"
jstring
Java_liao_thomas_javadev_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

int8_t test() {
    int8_t var = 33;
    return var;
}

extern "C"
jstring
Java_liao_thomas_javadev_ndk_NdkTest_stringFromJNI(JNIEnv* env, jobject thiz) {
#if defined(__arm__)
    #if defined(__ARM_ARCH_7A__)
    #if defined(__ARM_NEON__)
      #if defined(__ARM_PCS_VFP)
        #define ABI "armeabi-v7a/NEON (hard-float)"
      #else
        #define ABI "armeabi-v7a/NEON"
      #endif
    #else
      #if defined(__ARM_PCS_VFP)
        #define ABI "armeabi-v7a (hard-float)"
      #else
        #define ABI "armeabi-v7a"
      #endif
    #endif
  #else
   #define ABI "armeabi"
  #endif
#elif defined(__i386__)
#define ABI "x86"
#elif defined(__x86_64__)
#define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
#define ABI "mips64"
#elif defined(__mips__)
#define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
#define ABI "unknown"
#endif

    char * k = new char[7];
    for (int i = 0; i < 7; i++) {
        k[i] = '5';
    }
    //return env->NewStringUTF("Hello from JNI !  Compiled with ABI " ABI ".");
    return test222(env);
}


char* InsertSort(int a[], int n)
{
    for(int i= 1; i<n; i++){
        if(a[i] < a[i-1]){               //若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入
            int j= i-1;
            int x = a[i];        //复制为哨兵，即存储待排序元素
            a[i] = a[i-1];           //先后移一个元素
            while(x < a[j]){  //查找在有序表的插入位置
                a[j+1] = a[j];
                j--;         //元素后移
            }
            a[j+1] = x;      //插入到正确位置
        }
    }

    char* b = new char[n];
    for (int j = 0; j < n; j++) {
        b[j] = a[j] + '0';
    }
    return b;
}

jstring test222(JNIEnv* env){
    int a[8] = {3,1,5,7,2,4,9,6};
    char* d = InsertSort(a,8);

    jstring result = stoJstring(env, d);
    return result;
}


jstring stoJstring(JNIEnv* global_env, const char* pat)
{
    jclass strClass = global_env->FindClass("java/lang/String");
    jmethodID ctorID = global_env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jstring encoding = global_env->NewStringUTF("GBK");

    jbyteArray bytes = global_env->NewByteArray(strlen(pat));
    global_env->SetByteArrayRegion(bytes, 0, strlen(pat), (jbyte*)pat);
    jstring str = (jstring)global_env->NewObject(strClass, ctorID, bytes, encoding);
    return str;
}