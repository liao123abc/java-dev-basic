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

char* convertArrayInt2Char(int a[], int n)
{
    //输出为char类型
    char* b = new char[n];
    for (int j = 0; j < n; j++)
    {
        b[j] = a[j] + '0';
    }
    return b;
}

/**
 * 插入排序
 *
 * 对于未排序数据(右手抓到的牌)，在已排序序列(左手已经排好序的手牌)中从后向前扫描，找到相应位置并插入。
 *
 *　插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序
 * 元素逐步向后挪位，为最新元素提供插入空间。
 *
 *　具体算法描述如下：
 * 1 从第一个元素开始，该元素可以认为已经被排序
 * 2 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5 将新元素插入到该位置后
 * 6 重复步骤2~5
 *
 * 最坏情况下的比较次数是 1 + 2 + 3 + ... + (N - 1)，等差数列求和，结果为 N^2 / 2，所以最坏情况下的复杂度为 O(N^2)。
 * 最好情况下，数组已经是有序的，每插入一个元素，只需要考查前一个元素，因此最好情况下，插入排序的时间复杂度为O(N)
 *
 *
 *     分类 ------------- 内部比较排序
 *     数据结构 ---------- 数组
 *     最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)
 *     最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)
 *     平均时间复杂度 ---- O(n^2)
 *     所需辅助空间 ------ O(1)
 *     稳定性 ------------ 稳定
 * @param a
 * @param n
 * @return
 */
char* InsertSort(int a[], int n)
{
    int i, j, get;

    for (i = 1; i < n; i++)
    {
        get = a[i]; //取到需要排序的数
        j = i - 1; //前面的总是拍好序的
        while (j >= 0 && a[j] > get)//从右向左比较
        {
            a[j + 1] = a[j];//如果大于当前选择的数get， 那么右移
            j--;
        }
        a[j + 1] = get;//赋值到适当位置
    }

    char * b = convertArrayInt2Char(a, n);
    return b;
}


void exchange(int a[], int i, int j)
{
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

/*比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。
对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
针对所有的元素重复以上的步骤，除了最后一个。
持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
// 分类 -------------- 内部比较排序
// 数据结构 ---------- 数组
// 最差时间复杂度 ---- O(n^2)
// 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
// 平均时间复杂度 ---- O(n^2)
// 所需辅助空间 ------ O(1)
// 稳定性 ------------ 稳定
char* BubbleSort(int a[], int n) {
    for (int j = 0; j < n - 1; j++)// 每次最大元素就像气泡一样"浮"到数组的最后
    {
        for (int i = 0; i < n - 1 - j; i++)// 依次比较相邻的两个元素,使较大的那个向后移
        {
            if (a[i] > a[i + 1])
            {
                exchange(a, i, i + 1);
            }
        }
    }

    char * b = convertArrayInt2Char(a, n);
    return b;
}


/**
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置；然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 分类 -------------- 内部比较排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(n^2)
 * 最优时间复杂度 ---- O(n^2)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 不稳定
 *
 * @param a
 * @param n
 * @return
 */
char* SelectionSort(int a[], int n)
{
    int i, j, min;
    for (i = 0; i <= n -2; i++)
    { //已排序序列末尾
        min = i;
        for (j = i + 1; j <= n -1; j++)
        {//找出最小值
            if (a[j] < a[min]) {//找出未排序序列中的末尾， 存放在已排序序列的末尾
                min = j;
            }
        }
        if (min != i)
        {
            exchange(a,min, i);
        }
    }
    char * b = convertArrayInt2Char(a, n);
    return b;
}

jstring test222(JNIEnv* env){
    int a[8] = {3,1,5,7,2,4,9,6};

    //char* d = InsertSort(a,8);
    //char* d = BubbleSort(a,8);
    char* d = SelectionSort(a,8);
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