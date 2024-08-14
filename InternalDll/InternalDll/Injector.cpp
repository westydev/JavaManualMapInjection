#include "dev_westy_Injector.h"

#include "./Process/Process.hpp"
#include "./INJ/INJ.hpp"

JNIEXPORT jint JNICALL Java_dev_westy_Injector_getVersion(JNIEnv*, jclass) {
    return 0xA1;
}
JNIEXPORT jint JNICALL Java_dev_westy_Injector_injectDll(JNIEnv* env, jclass, jint pid, jbyteArray bytes, jint bytesLength) {
    Process process{ DWORD(pid) };

    if (!process) {
        std::cerr << "[-] Failed to open target process\n";
        return -1;
    }

    jbyte* byteArrayElements = env->GetByteArrayElements(bytes, nullptr);

    if (byteArrayElements == nullptr) {
        std::cerr << "[-] Failed to get byte array elements\n";
        return -1;
    }

    uint8_t* dllBytes = reinterpret_cast<uint8_t*>(byteArrayElements);

    Injector injector(process);
    injector.inject(dllBytes, bytesLength);

    env->ReleaseByteArrayElements(bytes, byteArrayElements, 0);

    return 1;
}
