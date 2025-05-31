package com.yourssu.soongpt.domain.course.implement

import com.yourssu.soongpt.domain.course.implement.exception.InvalidCategoryException

enum class Category(
    val displayName: String,
) {
    MAJOR_REQUIRED("전필"),
    MAJOR_ELECTIVE("전선"),
    GENERAL_REQUIRED("교필"),
    GENERAL_ELECTIVE("교선"),
    CHAPEL("채플"),
    OTHER("기타")
    ;

    companion object {
        fun from(name: String): Category? {
            return when (name) {
                "전필", "전기" -> MAJOR_REQUIRED
                "전선" -> MAJOR_ELECTIVE
                "교필" -> GENERAL_REQUIRED
                "교선" -> GENERAL_ELECTIVE
                "채플" -> CHAPEL
                else -> throw InvalidCategoryException()
            }
        }

        fun match(category: String): Category {
            return Category.entries.find { category.contains(it.displayName) }
                ?: OTHER
        }
    }
}
