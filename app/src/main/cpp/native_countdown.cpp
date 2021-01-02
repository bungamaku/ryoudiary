//
// Created by Ama on 12/7/2020.
//

#include <jni.h>

extern "C" jint Java_id_ac_ui_cs_mobileprogramming_bungaamalia_ryoudiary_fragment_InputCountdownFragment_nativePlus(
        JNIEnv* env,
        jobject,
        int currValue
) {
    return currValue + 1;
}

extern "C" jint Java_id_ac_ui_cs_mobileprogramming_bungaamalia_ryoudiary_fragment_InputCountdownFragment_nativeMinus(
        JNIEnv* env,
        jobject,
        int currValue
) {
    return currValue - 1;
}
