package tim03we.bungeetools.listener

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

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.server.DataPacketReceiveEvent
import cn.nukkit.network.protocol.DataPacket
import cn.nukkit.network.protocol.ScriptCustomEventPacket
import com.google.common.io.ByteArrayDataInput
import com.google.common.io.ByteStreams
import tim03we.bungeetools.BungeeTools
import java.lang.Exception

class DataPacketReceive: Listener {

    @EventHandler
    fun onData(event: DataPacketReceiveEvent) {
        val packet: DataPacket = event.packet;
        if(packet is ScriptCustomEventPacket) {
            if(packet.eventName == "bungeecord:main") {
                try {
                    val inp: ByteArrayDataInput = ByteStreams.newDataInput(packet.eventData);
                    val type: String = inp.readUTF();
                    var data: Any? = null;
                    if(type == "IP" || type == "PlayerCount") {
                        val string: String = inp.readUTF()
                        val integer: Int = inp.readInt()
                        data = "$string:$integer"
                    } else if(type == "PlayerList") {
                        val string: String = inp.readUTF()
                        val stringarray: String = inp.readUTF().split(", ").toString()
                        data = "$string:$stringarray"
                    } else if(type == "ServerIP") {
                        val serverName: String = inp.readUTF()
                        val ip: String = inp.readUTF()
                        val port: Int = inp.readUnsignedShort()
                        data = "$serverName:$ip:$port"
                    } else if(type == "GetServers") {
                        val stringarray: String = inp.readUTF().split(", ").toString()
                        data = stringarray
                    } else if(type == "GetServer") {
                        val string: String = inp.readUTF()
                        data = string
                    }
                    BungeeTools.action.get(event.player.name)?.getHandler()?.handle(event.player, type, data)
                } catch (e: Exception) { /* ignored */ }
            }
        }
    }
}