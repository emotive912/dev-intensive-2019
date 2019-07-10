package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

        user.printMe()
        user2.printMe()
        user3.printMe()

        println("$user $user2 $user3")
    }
    @Test
    fun test_factory(){
        val user = User.makeUser("John Doa")
        val user2 = User.makeUser("John cena")
    }
    @Test
    fun test_decomposition(){
        val user = User.makeUser("john cena")
        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()
        println("$id, $firstName,$lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }
    @Test
    fun test_copy(){
        val user = User.makeUser("john cena")
        var user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        var user4 = user.copy(lastName = "Cena", lastVisit = Date().add(-2,TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent()
        )
    }
    @Test
    fun test_dataq_maping(){
        val user = User.makeUser("John cena")
        println(user)

        val userView = user.toUserView()

        userView.printMe()
    }
    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("John cena")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "text", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "url", type = "image")

        when(imgMessage){
            is TextMessage -> print("t")
            is ImageMessage -> print("i")
        }

    }
}
