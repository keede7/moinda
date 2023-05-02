package io.keede.moinda.util

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class AES256 {
    private val keySize = 128
    private val key = "abcdefghabcdefghabcdefghabcdefgh" // 32byte
    private val iv = "0123456789abcdef" // 16byte
    private val aes = "AES"
    private val utf8 = "UTF-8"

    private val cipher: Cipher = Cipher.getInstance(alg)

    // 암호화
    fun encrypt(text: String): String {
        try {

            val keySpec = SecretKeySpec(key.toByteArray(), aes)
            val ivParamSpec = IvParameterSpec(iv.toByteArray())

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec)

            val encrypted: ByteArray = cipher.doFinal(text.toByteArray(charset(utf8)))

            return Base64.getEncoder().encodeToString(encrypted)
        } catch (e: Exception) {
            throw RuntimeException("암호화에 실패했습니다.")
        }
    }

    // 복호화
    fun decrypt(cipherText: String?): String {
        try {

            val keySpec = SecretKeySpec(key.toByteArray(), aes)
            val ivParamSpec = IvParameterSpec(iv.toByteArray())

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec)

            val decodedBytes: ByteArray = Base64.getDecoder().decode(cipherText)
            val decrypted: ByteArray = cipher.doFinal(decodedBytes)

            return String(decrypted, charset(utf8))
        } catch (e: Exception) {
            throw RuntimeException("복호화에 실패했습니다.")
        }
    }

    companion object {
        var alg = "AES/CBC/PKCS5Padding"
    }
}