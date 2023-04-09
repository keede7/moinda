package io.keede.moinda.presentation.config

import java.util.*

/**
 * @author keede
 * Created on 2023-04-09
 */
object UriMaker {
    const val GROUP_API = "/api/group"
    const val MEMBER_API = "/api/member"
}

fun UriMaker.toGroupApiUri(vararg resources: String): String {
    return Arrays.stream(resources)
        .reduce(GROUP_API) { prefix: String, resource: String -> "$prefix/$resource" }
}

fun UriMaker.toMemberApiUri(vararg resources: String): String {
    return Arrays.stream(resources)
        .reduce(MEMBER_API) { prefix: String, resource: String -> "$prefix/$resource" }
}

fun UriMaker.toMemberApiUri(resource: Long): String {
    return "$MEMBER_API/$resource"
}
