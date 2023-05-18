package io.keede.moinda.common

import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class Encryption {

    companion object {
        private const val ALGORITHM = "AES/CBC/PKCS5Padding"
        private const val IV = "letsMoGakKoKeede"
        private val KEY = String.format("%s%s", IV, "GS3G7KS3F1U6S2A1") // 16자 이상으로 구성된 키

        fun encrypt(value: String): String? {
            return try {
                val key = generateKey()
                val cipher = Cipher.getInstance(ALGORITHM)
                cipher.init(Cipher.ENCRYPT_MODE, key)
                val encryptedByteValue = cipher.doFinal(value.toByteArray(StandardCharsets.UTF_8))
                Base64().encodeAsString(encryptedByteValue)
            } catch (e: Exception) {
                // 예외 변경
                println(e)
                throw RuntimeException("암호화를 실패했습니다.")
            }
        }

        fun decrypt(cipherText: String?): String? {
            return try {

//                val key = generateKey()
//                println("key : $key")
                val cipher = Cipher.getInstance(ALGORITHM)
                val keySpec = SecretKeySpec(KEY.toByteArray(), "AES")
                val ivParamSpec = IvParameterSpec(IV.toByteArray())

                println("cipher : $cipher")

                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec)

                println("cipher : $cipher")

                val decryptedValue64: ByteArray = Base64().decode(cipherText)

                println("decryptedValue64: $decryptedValue64")

                val decryptedByteValue = cipher.doFinal(decryptedValue64)

                println("decryptedByteValue : $decryptedByteValue")

                String(decryptedByteValue, StandardCharsets.UTF_8)
            } catch (e: Exception) {
                // 예외 변경
                println(e)
                throw RuntimeException("복호화를 실패했습니다.")
            }
        }

        private fun generateKey(): Key {
            return try {
                val keyValue = KEY.toByteArray(StandardCharsets.UTF_8)
                SecretKeySpec(keyValue, ALGORITHM)
            } catch (e: Exception) {
                // 예외 변경
                throw RuntimeException("암호화 작업 도중 실패했습니다.")
            }
        }
    }
}