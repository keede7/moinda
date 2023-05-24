package io.keede.moinda.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author keede
 * Created on 2023-05-26
 */
@Configuration
internal class JasyptConfig {

    // TODO : VM옵션으로 설정이 필요하다. 추후 배포할때 추가해야 한다.
    @Value("\${jasypt.encryptor.password}")
    private lateinit var encryptionPassword: String

    @Bean
    fun jasyptStringEncryptor(): StringEncryptor? {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = encryptionPassword // 암호화할 때 사용하는 키
        config.algorithm = "PBEWithMD5AndDES" // 암호화 알고리즘
        config.setKeyObtentionIterations("1000") // 반복할 해싱 회수
        config.setPoolSize("1") // 인스턴스 pool
        config.providerName = "SunJCE"
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator") // salt 생성 클래스
        config.stringOutputType = "base64" //인코딩 방식
        encryptor.setConfig(config)
        return encryptor
    }
}