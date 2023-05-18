package io.keede.moinda.common

import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class Encryption {

    companion object {
        private val ALGORITHM = "AES"
        private val SORT = "medicalToken1517"
        private val KEY = String.format("%s%s", SORT, "GS3G7KS3F1U6S2A1") // 16자 이상으로 구성된 키

        fun encrypt(value: String): String? {
            return try {
                val key = generateKey()
                val cipher = Cipher.getInstance(ALGORITHM)
                cipher.init(Cipher.ENCRYPT_MODE, key)
                val encryptedByteValue = cipher.doFinal(value.toByteArray(StandardCharsets.UTF_8))
                Base64().encodeAsString(encryptedByteValue)
            } catch (e: Exception) {
                // 예외 변경
                throw RuntimeException("암호화를 실패했습니다.")
            }
        }

        fun decrypt(value: String?): String? {
            return try {
                val key = generateKey()
                val cipher = Cipher.getInstance(ALGORITHM)
                cipher.init(Cipher.DECRYPT_MODE, key)
                val decryptedValue64: ByteArray = Base64().decode(value)
                val decryptedByteValue = cipher.doFinal(decryptedValue64)
                String(decryptedByteValue, StandardCharsets.UTF_8)
            } catch (e: Exception) {
                // 예외 변경
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