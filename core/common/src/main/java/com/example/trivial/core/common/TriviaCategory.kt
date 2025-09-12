package com.example.trivial.core.common

data class TriviaCategory(
    val id: Int,
    val name: String,
    val icon: String? = null,
) {
    companion object {
        val DEFAULT = TriviaCategory(-1, "Select category")
    }
}

object TriviaCategories {
    val list = listOf(
        TriviaCategory(9, "General Knowledge", icon = "\uD83D\uDDFA\uFE0F"),
        TriviaCategory(10, "Books", icon = "\uD83D\uDCDA\uFE0F"),
        TriviaCategory(11, "Film", icon = "\uD83C\uDFAC"),
        TriviaCategory(12, "Music", icon = "\uD83C\uDFB8\uFE0F"),
        TriviaCategory(13, "Musicals & Theatres", icon = "\uD83C\uDFAD"),
        TriviaCategory(14, "Television", icon = "\uD83D\uDCFA\uFE0F"),
        TriviaCategory(15, "Video Games", icon = "\uD83C\uDFAE\uFE0F"),
        TriviaCategory(16, "Board Games", icon = "\uD83C\uDFB2"),
        TriviaCategory(17, "Science & Nature", icon = "\uD83D\uDD2C"),
        TriviaCategory(18, "Computers", icon = "\uD83D\uDCBB\uFE0F"),
        TriviaCategory(19, "Mathematics", icon = "\uD83E\uDDEE"),
        TriviaCategory(20, "Mythology", icon = "\uD83E\uDEBD"),
        TriviaCategory(21, "Sports", icon = "\uD83C\uDFC5"),
        TriviaCategory(22, "Geography", icon = "\uD83C\uDF0E"),
        TriviaCategory(23, "History", icon = "\uD83D\uDCDC"),
        TriviaCategory(24, "Politics", icon = "\uD83D\uDDF3\uFE0F"),
        TriviaCategory(25, "Art", icon = "\uD83D\uDDBC\uFE0F"),
        TriviaCategory(26, "Celebrities", icon = "\uD83D\uDCF8"),
        TriviaCategory(27, "Animals", icon = "\uD83D\uDC31"),
        TriviaCategory(28, "Vehicles", icon = "\uD83D\uDE97\uFE0F"),
        TriviaCategory(29, "Comics", icon = "\uD83D\uDDEF\uFE0F"),
        TriviaCategory(30, "Science Gadgets", icon = "\uD83D\uDD0E"),
        TriviaCategory(31, "Japanese Anime & Manga", icon = "\uD83C\uDDEF\uD83C\uDDF5"),
        TriviaCategory(32, "Cartoon & Animations", icon = "\uD83D\uDCA8"),
    )
}
