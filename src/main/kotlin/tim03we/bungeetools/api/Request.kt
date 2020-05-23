package tim03we.bungeetools.api

/*
 * This software is distributed under "GNU General Public License v3.0".
 * This license allows you to use it and/or modify it but you are not at
 * all allowed to sell this plugin at any cost. If found doing so the
 * necessary action required would be taken.
 *
 * BungeeTools-Nukkit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License v3.0 for more details.
 *
 * You should have received a copy of the GNU General Public License v3.0
 * along with this program. If not, see
 * <https://opensource.org/licenses/GPL-3.0>.
 */

import cn.nukkit.Server
import cn.nukkit.network.protocol.ScriptCustomEventPacket
import tim03we.bungeetools.BungeeTools
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.lang.Exception

class Request {

    private lateinit var player: String
    private lateinit var action: RequestResponseAction
    private lateinit var type: String
    private lateinit var data: Array<String>

    fun setPlayer(name: String) {
        player = name
    }

    fun setHandler(handler: RequestResponseAction) {
        action = handler
    }

    fun setType(typeRequest: String) {
        type = typeRequest
    }

    fun setData(args: Array<String>) {
        data = args
    }

    fun getPlayer(): String {
        return player
    }

    fun getHandler(): RequestResponseAction {
        return action
    }

    fun getType(): String {
        return type
    }

    fun getData(): Array<String> {
        return data
    }

    fun build() {
        val request: Request = Request()
        request.setHandler(getHandler())
        request.setPlayer(getPlayer())
        request.setType(getType())
        request.setData(getData())
        BungeeTools.action.put(player, request)
        message(getType(), getData())
    }

    private fun message(subchannel: String, arguments: Array<String>): Boolean {
        val customEventPacket: ScriptCustomEventPacket = ScriptCustomEventPacket()
        val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream: DataOutputStream = DataOutputStream(outputStream)
        try {
            dataOutputStream.writeUTF(subchannel)
            for (argument: String in arguments) {
                dataOutputStream.writeUTF(argument)
            }
            customEventPacket.eventName = "bungeecord:main"
            customEventPacket.eventData = outputStream.toByteArray()
            Server.getInstance().getPlayer(player).dataPacket(customEventPacket)
        } catch (e: Exception) { /* ignored */ }
        return true
    }
}