package de.psych.main.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.entity.Player;
import de.psych.main.Main;

public class Bungee {

	public static void connect(Main m,Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try{
			out.writeUTF("Connect");
			out.writeUTF(server);
		}catch (IOException io) {
			io.printStackTrace();
		}
		
		p.sendPluginMessage(m, "BungeeCord", b.toByteArray());
		
	}
	
	
}
