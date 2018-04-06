// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn (x: Any): String {
    when (x) {
        "Hello" -> return "world"
        is String -> return "Say what?"
        0 -> return "zero"
        1 -> return "one"
        in 2..10 -> return "low number"
        is Int -> return "a number"
        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add (a: Int, b: Int): Int {
    return a + b
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub (a: Int, b: Int): Int {
    return a - b
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp (a: Int, b: Int, func: (x: Int, y: Int) -> Int): Int {
    return func(a, b)
}

// write a class "Person" with first name, last name and age
class Person (val firstName: String, val lastName: String, var age: Int) {
    fun equals(p: Person): Boolean {
        return  p.firstName == this.firstName && p.lastName == this.lastName && p.age == this.age
    }

    val debugString: String by lazy { 
        "[Person firstName:" + this.firstName + " lastName:" + this.lastName + " age:" + this.age + "]"
    }
}

// write a class "Money"
class Money (var amount: Int, var currency: String) {
    init {
        if (this.amount < 0)
            throw Exception("Can't be negative!")
        else if (!arrayOf("USD", "EUR", "CAN", "GBP").contains(this.currency))
            throw Exception("Currency unexchangeable")
    }

    fun convert(to: String): Money {
        if (this.currency == "USD") {
            when (to) {
                "USD" -> return Money(this.amount*1, "USD")
                "EUR" -> return Money(this.amount*3/2, "USD")
                "CAN" -> return Money(this.amount*5/4, "USD")
                "GBP" -> return Money(this.amount*1/2, "USD")
            }
        } else if (this.currency == "EUR") {
            when (to) {
                "USD" -> return Money(this.amount*2/3, "EUR")
                "EUR" -> return Money(this.amount*1, "EUR")
                "CAN" -> return Money(this.amount*5/6, "EUR")
                "GBP" -> return Money(this.amount*1/3, "EUR")
            }
        } else if (this.currency == "CAN") {
            when (to) {
                "USD" -> return Money(this.amount*4/5, "CAN")
                "EUR" -> return Money(this.amount*6/5, "CAN")
                "CAN" -> return Money(this.amount*1, "CAN")
                "GBP" -> return Money(this.amount*2/5, "CAN")
            }
        } else { // to GBP
            when (to) {
                "USD" -> return Money(this.amount*2, "GBP")
                "EUR" -> return Money(this.amount*3, "GBP")
                "CAN" -> return Money(this.amount*5/2, "GBP")
                "GBP" -> return Money(this.amount*1, "GBP")
            }
        }
        return Money(this.amount, this.currency)
    }

    operator fun plus(a: Money): Money {
        val converted = a.convert(this.currency)
        return Money(this.amount + converted.amount, this.currency)
    }
}

// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
