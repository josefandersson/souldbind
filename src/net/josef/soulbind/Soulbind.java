package net.josef.soulbind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Soulbind extends JavaPlugin implements Listener {

	public void onEnable() {
		getConfig().addDefault("loreText", "§5Soulbound x{amount}");
		getConfig().addDefault("onlyAllowSoulbindOnWhitelistedItems", true);
		getConfig().addDefault("whitelist", new int[] { 256,257,258,261,267,268,269,270,271,272,273,274,275,276,277,278,279,283,284,285,286,290,291,292,293,294,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,346,386,387,397,398,442,443 });
		
		saveDefaultConfig();
	}
	
	public void onDisable() {
		
	}
	
	
	// Commands
	// /soulbind give <player> [amount=1]
	// /soulbind reload
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}
	
	public Pattern getRegexPattern() {
		return Pattern.compile(getConfig().getString("loreText").replace("{amount}", "(\\d+)"));
	}
	
	public int getItemStackSoulbinds(ItemStack itemStack) {
		Pattern p = getRegexPattern();
		Matcher m;
		if (itemStack != null) {
			for (String lore : itemStack.getItemMeta().getLore()) {
				m = p.matcher(lore);
				if (m.find()) {
					try {
						return Integer.parseInt(m.group(0));
					} catch (NumberFormatException e) {
						return 0;
					}
				}
			}
		}
		return 0;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		int soulbinds;
		for (ItemStack itemStack : p.getInventory().getContents()) {
			soulbinds = getItemStackSoulbinds(itemStack);
			if (soulbinds > 0) {
				
			}
		}
	}
	
}
