package machine

import kotlin.system.exitProcess

class CoffeeMachine(
    private var water: Int = 400,
    private var milk: Int = 540,
    private var coffee: Int = 120,
    private var cups: Int = 9,
    private var money: Int = 550
) {


    private fun printState(): Unit {
        println()
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffee g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money")
        println()
    }

    fun takeQuery(): Unit {

        println("Write action (buy, fill, take, remaining, exit): ")
        when (readln()) {
            "buy" -> {
                println()
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                when (val answer = readln()) {
                    "back" -> takeQuery()
                    else -> makeCoffee(answer.toInt())
                }
            }

            "fill" -> fillMachine()
            "take" -> takeMoney()
            "remaining" -> printState()
            "exit" -> exitProcess(0)
        }
    }

    private fun checkResources(type: Int) = when (type) {
        1 -> water >= 250 && coffee >= 16 && money >= 4 && cups >= 1
        2 -> water >= 350 && coffee >= 20 && milk >= 75 && money >= 7 && cups >= 1
        3 -> water >= 200 && coffee >= 12 && milk >= 100 && money >= 6 && cups >= 1
        else -> false
    }

    private fun makeCoffee(type: Int) {
        if (checkResources(type)) {
            when (type) {
                1 -> {
                    water -= 250; coffee -= 16; money += 4; cups -= 1
                    println("I have enough resources, making you a coffee!")
                    println()
                }

                2 -> {
                    water -= 350; coffee -= 20; milk -= 75; money += 7; cups -= 1
                    println("I have enough resources, making you a coffee!")
                    println()
                }

                3 -> {
                    water -= 200; coffee -= 12; milk -= 100; money += 6; cups -= 1
                    println("I have enough resources, making you a coffee!")
                    println()
                }
            }
        } else {
            println("Sorry, not enough resources!")
        }
    }

    private fun fillMachine(): Unit {
        println("Write how many ml of water you want to add:")
        val addWater = readln().toInt()
        water += addWater

        println("Write how many ml of milk you want to add:")
        val addMilk = readln().toInt()
        milk += addMilk

        println("Write how many grams of coffee beans you want to add:")
        val addCoffee = readln().toInt()
        coffee += addCoffee

        println("Write how many disposable cups you want to add:")
        val addCups = readln().toInt()
        cups += addCups
    }

    private fun takeMoney(): Unit {
        println("I gave you $$money")
        money = 0
    }
}


fun main() {
    val machine1 = CoffeeMachine()
    val program = true

    while (program) {
        machine1.takeQuery()
    }

}
