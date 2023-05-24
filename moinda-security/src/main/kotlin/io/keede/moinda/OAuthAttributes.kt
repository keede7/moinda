package io.keede.moinda

/**
 * @author keede
 * Created on 2023-05-20
 */
data class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String?,
    val name: String,
    val email: String,
) {

    companion object {

        private fun ofGoogle(
            userNameAttributeName: String?,
            attributes: Map<String, Any>
        ): OAuthAttributes =
            OAuthAttributes(
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
                name = attributes.get("name").toString(),
                email = attributes.get("email").toString(),
            )

        fun of(
            registrationId: String?,
            userNameAttributeName: String?,
            attributes: Map<String, Any>
        ): OAuthAttributes =
            ofGoogle(
                userNameAttributeName = userNameAttributeName,
                attributes = attributes
            )
    }
}