package io.keede.moinda.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object SHA256 {
    fun encrypt(text: String): String {
        val md: MessageDigest  = try {
            MessageDigest.getInstance("SHA-256")
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
        md.update(text.toByteArray())
        return bytesToHex(md.digest())
    }

    fun isValid(loginPassword: String, savedPassword: String): Boolean = encrypt(loginPassword) == savedPassword

    private fun bytesToHex(bytes: ByteArray): String {
        val builder = StringBuilder()
        for (b in bytes) {
            builder.append(String.format("%02x", b))
        }
        return builder.toString()
    }
}