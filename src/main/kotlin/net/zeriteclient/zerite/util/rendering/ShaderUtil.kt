package net.zeriteclient.zerite.util.rendering

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.EntityRenderer
import net.minecraft.util.ResourceLocation
import net.zeriteclient.zerite.util.other.ReflectionUtil
import java.lang.reflect.InvocationTargetException


object ShaderUtil {

    fun applyShader(path: ResourceLocation) {
        val method = ReflectionUtil
            .getMethod(
                EntityRenderer::class.java, arrayOf("loadShader", "a"),
                arrayOf(ResourceLocation::class.java)
            )!!
        method.isAccessible = true
        try {
            method.invoke(
                Minecraft.getMinecraft().entityRenderer,
                path
            )
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

    }

    fun unloadShader() {
        Minecraft.getMinecraft().entityRenderer.stopUseShader()
    }

}