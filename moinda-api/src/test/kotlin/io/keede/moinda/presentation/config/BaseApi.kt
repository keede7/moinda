package io.keede.moinda.presentation.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc

/**
 * @author keede
 * Created on 2023-03-25
 */
open class BaseApi {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    protected fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

}