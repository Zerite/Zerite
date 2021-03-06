package cc.zerite.client.event

import com.mojang.authlib.GameProfile
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.util.IChatComponent
import net.minecraft.world.World
import java.lang.reflect.Method

abstract class Event

abstract class CancellableEvent : Event() {
    var cancelled: Boolean = false
}

data class EventSubscriber(val obj: Any, val method: Method, val eventClass: Class<*>, val priority: Int)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Subscribe(val priority: Int = 0)

class ClientStartEvent : Event()
class ClientShutdownEvent : Event()
class RenderGameOverlayEvent(val scaledResolution: ScaledResolution, val partialTicks: Float) : Event()
class ReceiveChatMessageEvent(val message: IChatComponent) : CancellableEvent()
class SendChatMessageEvent(val message: String) : CancellableEvent()
class TickEvent : Event()
class GuiDisplayEvent(val guiScreen: GuiScreen?) : Event()
class RenderTickEvent(val scaledResolution: ScaledResolution) : Event()
class WorldLoadEvent(val worldClient: WorldClient, val message: String) : Event()
class PlayerInitEvent(
    val abstractClientPlayer: AbstractClientPlayer,
    val world: World,
    val playerProfile: GameProfile
) : Event()
class ConnectToServerEvent(val ip: String, val port: Int) : Event()
class DisconnectFromServerEvent : Event()