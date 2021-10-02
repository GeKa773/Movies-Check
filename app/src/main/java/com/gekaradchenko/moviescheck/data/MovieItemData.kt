package com.gekaradchenko.moviescheck.data

class MovieItemData(
    val id: Int,
    val name: String,
    val info: String,
    val duration: String,
    val description: String,
    val actors: List<Actor>,
    val countActors: Int
) {
    fun printItem(): String {
        return "id = $id, name = $name, info = $info, duration = $duration, description = $description, countActors = $countActors"
    }
}

class Actor(
    val id: Int,
    var name: String?,
    var image: String?
) {
    fun getActor(): String {
        return "id = $id, name = $name, image = $image"
    }
}