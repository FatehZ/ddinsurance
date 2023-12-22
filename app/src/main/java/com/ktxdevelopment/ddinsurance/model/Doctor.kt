package com.ktxdevelopment.ddinsurance.model

data class Doctor(
    val name: String,
    val options: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Doctor

        if (name != other.name) return false
        if (!options.contentEquals(other.options)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + options.contentHashCode()
        return result
    }
}