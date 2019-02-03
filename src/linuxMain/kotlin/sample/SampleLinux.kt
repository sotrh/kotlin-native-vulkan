package sample

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString
import libs.glfw.*

fun hello(): String = "Hello, Kotlin/Native!"

fun main() {
    val window = createWindow()
    initVulkan(window)
    mainLoop(window)
    cleanup(window)
}

fun createWindow(): CPointer<GLFWwindow> {
    glfwSetErrorCallback(staticCFunction { error, description ->
        println("Error $error: ${description?.toKString()}")
    })

    if (glfwInit() != GLFW_TRUE) {
        throw Error("Unable to init GLFW")
    }

    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)

    println("GLFW_NO_API = $GLFW_NO_API")
    println("GLFW_INVALID_ENUM = $GLFW_INVALID_ENUM")

    return glfwCreateWindow(800, 600, "Kotlin Native Vulkan Sample", null, null)
        ?: throw Error("Unable to create GLFW window")
}

fun initVulkan(window: CPointer<GLFWwindow>) {

}

fun mainLoop(window: CPointer<GLFWwindow>) {
    while (glfwWindowShouldClose(window) == GLFW_FALSE) {
        glfwPollEvents()
    }
}

fun cleanup(window: CPointer<GLFWwindow>) {
    glfwDestroyWindow(window)
    glfwTerminate()
}
