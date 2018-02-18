package de.psych.main.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import de.psych.main.utils.ParticleAPI;
import net.minecraft.server.v1_8_R3.EnumParticle;
public class PlayerToggleFly implements Listener{

	@EventHandler
	public void onPlayerToggleFly(PlayerToggleFlightEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR){
			return;
		}
		e.setCancelled(true);
		p.setFlying(false);
		p.setAllowFlight(false);
		Location loc = p.getLocation();
		Vector v = loc.getDirection().multiply(1.2f).setY(1.2);
		p.setVelocity(v);
		loc.getWorld().playSound(loc, Sound.FIREWORK_BLAST, 1, 0.2f);
		ParticleAPI.sendParticle(EnumParticle.FIREWORKS_SPARK, loc, 0.5f, 0.5f, 0.5f, 0.07f, 80);
		
	}
	
	
}
