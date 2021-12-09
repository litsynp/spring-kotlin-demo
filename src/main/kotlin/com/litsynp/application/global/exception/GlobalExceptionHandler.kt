package com.litsynp.application.global.exception

import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest


@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException, req: WebRequest): ErrorMessage {
        return ErrorMessage(message = "HttpMessageNotReadable: ${ex.message.toString()}")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        ex: HttpRequestMethodNotSupportedException, req: WebRequest
    ): ErrorMessage {
        return ErrorMessage(message = "HttpRequestMethodNotSupported: Supported HTTP methods: ${ex.supportedHttpMethods}")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, req: WebRequest): ErrorMessage {
        return ErrorMessage(
            message = "MethodArgumentNotValidException: ${ex.message}",
            errors = ex.bindingResult.fieldErrors
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun notFound(ex: ResourceNotFoundException, request: WebRequest): ErrorMessage {
        return ErrorMessage(message = "ResourceNotFound: ex.message.toString()")
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException, req: WebRequest): ErrorMessage {
        return ErrorMessage(message = "HttpRequestMethodNotSupported: Supported HTTP methods: ${ex.message}")
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun internalServerError(ex: Exception, req: WebRequest): ErrorMessage {
        return ErrorMessage(message = "InternalServerError: ${ex.message.toString()}")
    }
}
