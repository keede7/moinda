package io.keede.moinda.presentation.advice

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestApiExceptionHandler {

    @ExceptionHandler(value = [RuntimeException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeException(exception: RuntimeException): String? {
        println("exception !!! : ${exception}")
        return exception.message
    }

    // TODO : 추가적인 테스트가 필요하다.
    @ExceptionHandler(value = [BindException::class, MethodArgumentNotValidException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRequestArgumentBindingException(bindingResult: BindingResult): String? {
        return bindingResult.fieldErrors
            .map { obj: FieldError -> obj.defaultMessage }
            .toList()
            .joinToString()
    }
}