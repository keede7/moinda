package io.keede.moinda.presentation.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.keede.moinda.common.member.session.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MockMvc
import javax.servlet.http.Cookie

/**
 * @author keede
 * Created on 2023-03-25
 */
open class BaseApi {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    protected lateinit var session: MockHttpSession

    // TODO : 추후 삭제
    protected val cookie = Cookie(Constants.COOKIE_NAME, Constants.SESSION_KEY)

    protected fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

}