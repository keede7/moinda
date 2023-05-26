package io.keede.moinda.service

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.model.OAuthAttributes
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.Collections
import javax.servlet.http.HttpSession


@Service
class CustomOAuth2Service(
    // TODO : Core가 아니라 Domain Layer를 의존하는 것으로 수정하여 UseCase 의존하게 변경
    private val memberQueryAdapter: MemberQueryAdapter,
    private val memberCommandAdapter: MemberCommandAdapter,
    private val httpSession: HttpSession,
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

//        val registrationId = userRequest?.let {
//            it.clientRegistration
//                .registrationId
//        }

        val userNameAttributeName = userRequest?.let {
            it.clientRegistration
                .providerDetails
                .userInfoEndpoint
                .userNameAttributeName
        }

        val attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.attributes)

        // TODO : 사용자를 저장하거나 수정한다.
        val memberJpaEntity = storeOAuthMember(attributes)

        httpSession.setAttribute(
            Constants.SESSION_KEY,
            // TODO : 임시로 설정해둔다.
            SessionResponse(memberJpaEntity?.id, attributes.name)
        )

        httpSession.maxInactiveInterval = Constants.MAX_IN_ACTIVE_INTERVAL

        return DefaultOAuth2User(

            Collections.singleton(
                SimpleGrantedAuthority(
                    "ADMIN"
                ),
            ),
            attributes.attributes,
            attributes.nameAttributeKey,
        )
    }

    /* 소셜로그인시 기존 회원이 존재하면 수정날짜 정보만 업데이트해 기존의 데이터는 그대로 보존 */
//    private User saveOrUpdate(OAuthAttributes attributes) {
//        User user = memberQueryAdapter.findOAuth2ByEmail(attributes.getEmail())
//        .map(User::updateModifiedDate)
//        .orElse(attributes.toEntity());
//        return userRepository.save(user);
//    }

    private fun storeOAuthMember(attributes: OAuthAttributes): MemberJpaEntity? {

        // TODO : 저장 또는 업데이트를 해야한다.
        return memberQueryAdapter.findOAuth2ByEmail(attributes.email)
            ?: return memberCommandAdapter.save(
                CreateMember(
                    attributes.name,
                    attributes.email,
                )
            )
    }
}