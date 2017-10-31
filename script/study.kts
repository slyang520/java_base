//  kotlinc -script study.kts

import java.io.File

println("Hello from kts")

val file = File(".")
file.listFiles().forEach(::println)

println("The End.")