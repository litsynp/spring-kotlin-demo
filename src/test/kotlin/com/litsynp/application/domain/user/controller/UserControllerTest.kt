package com.litsynp.application.domain.user.controller

import com.litsynp.application.domain.user.entity.User
import com.litsynp.application.domain.user.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    @Throws(Exception::class)
    fun create() {
        val user = User(
            email = "litsynp@test.com",
            password = "testpassword",
            roles = mutableSetOf()
        )

        `when`(userService.signup(user)).thenReturn(user)

        mockMvc.perform(
            post("/api/v1/users")
                .content("{\"email\": \"litsynp@test.com\", \n\"password\": \"testpassword\", \n\"roles\": []}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
            .andDo(
                document(
                    "user-create",
                    requestFields(
                        fieldWithPath("email").description("이메일"),
                        fieldWithPath("password").description("비밀번호"),
                        fieldWithPath("roles").description("권한"),
                    )
                )
            )
    }
}
