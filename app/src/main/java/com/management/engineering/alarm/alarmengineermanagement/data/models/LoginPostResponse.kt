package com.management.engineering.alarm.alarmengineermanagement.data.models

data class LoginPostResponse(
        val token: String?,
        val error: Array<String>?,
        val username: String?,
        val password: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginPostResponse

        if (token != other.token) return false
        if (error != null) {
            if (other.error == null) return false
            if (!error.contentEquals(other.error)) return false
        } else if (other.error != null) return false
        if (username != other.username) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = token?.hashCode() ?: 0
        result = 31 * result + (error?.contentHashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        return result
    }
}
