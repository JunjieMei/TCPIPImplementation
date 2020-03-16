/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jpcap_JpcapSender */

#ifndef _Included_jpcap_JpcapSender
#define _Included_jpcap_JpcapSender
#ifdef __cplusplus
extern "C" {
#endif
#undef jpcap_JpcapSender_MAX_NUMBER_OF_INSTANCE
#define jpcap_JpcapSender_MAX_NUMBER_OF_INSTANCE 255L
/* Inaccessible static: instanciatedFlag */
#undef jpcap_JpcapSender_RAW_SOCKET_ID
#define jpcap_JpcapSender_RAW_SOCKET_ID 99999L
/*
 * Class:     jpcap_JpcapSender
 * Method:    nativeOpenDevice
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jpcap_JpcapSender_nativeOpenDevice
  (JNIEnv *, jobject, jstring);

/*
 * Class:     jpcap_JpcapSender
 * Method:    nativeSendPacket
 * Signature: (Ljpcap/packet/Packet;)V
 */
JNIEXPORT void JNICALL Java_jpcap_JpcapSender_nativeSendPacket
  (JNIEnv *, jobject, jobject);

/*
 * Class:     jpcap_JpcapSender
 * Method:    nativeCloseDevice
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jpcap_JpcapSender_nativeCloseDevice
  (JNIEnv *, jobject);

/*
 * Class:     jpcap_JpcapSender
 * Method:    nativeOpenRawSocket
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jpcap_JpcapSender_nativeOpenRawSocket
  (JNIEnv *, jobject);

/*
 * Class:     jpcap_JpcapSender
 * Method:    nativeSendPacketViaRawSocket
 * Signature: (Ljpcap/packet/Packet;)V
 */
JNIEXPORT void JNICALL Java_jpcap_JpcapSender_nativeSendPacketViaRawSocket
  (JNIEnv *, jobject, jobject);

/*
 * Class:     jpcap_JpcapSender
 * Method:    nativeCloseRawSocket
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jpcap_JpcapSender_nativeCloseRawSocket
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
