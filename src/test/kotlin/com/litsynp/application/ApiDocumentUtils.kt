package com.litsynp.application

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.payload.ResponseFieldsSnippet


interface ApiDocumentUtils {
    companion object {
        val documentRequest: OperationRequestPreprocessor?
            get() = preprocessRequest(
                modifyUris().scheme("http").host("localhost").port(8080), prettyPrint()
            )

        val documentResponse: OperationResponsePreprocessor?
            get() = preprocessResponse(prettyPrint())

        val errorResponseFieldsWithFieldErrors: ResponseFieldsSnippet?
            get() = responseFields(
                fieldWithPath("status").description("에러 상태"),
                fieldWithPath("code").description("에러 코드"),
                fieldWithPath("message").description("에러 메세지"),
                subsectionWithPath("errors").description("필드 에러").optional()
            )
    }
}
