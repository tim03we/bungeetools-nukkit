package tim03we.bungeetools

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

import cn.nukkit.plugin.PluginBase
import tim03we.bungeetools.api.Request
import tim03we.bungeetools.listener.DataPacketReceive

class BungeeTools: PluginBase() {

    companion object {
        var action: HashMap<String, Request> = HashMap()
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(DataPacketReceive(), this)
    }
}