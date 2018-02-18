package de.psych.main.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.psych.mysql.MySQL;

public class FileManager {
	
	public static File getMySQLFile(){
		return new File("plugins/DarkNetwork","mysql.yml");
	}
	
	
	public static FileConfiguration getMySQLFileConfiguration(){
		return YamlConfiguration.loadConfiguration(getMySQLFile());
	}
	
	
	
	public static void setStandartMySQL(){
		FileConfiguration cfg = getMySQLFileConfiguration();
		cfg.options().copyDefaults(true);
		cfg.addDefault("host", "localhost");
		cfg.addDefault("port", "3306");
		cfg.addDefault("database", "darkexception");
		cfg.addDefault("username", "mysqluser");
		cfg.addDefault("password", "*7rXC37dbT3o!t!AxVRaey#q88Trb5");
		
		try {
			cfg.save(getMySQLFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readMySQL(){
		FileConfiguration cfg = getMySQLFileConfiguration();
		
		MySQL.host = cfg.getString("host");
		MySQL.port = cfg.getString("port");
		MySQL.database = cfg.getString("database");
		MySQL.username = cfg.getString("username");
		MySQL.password = cfg.getString("password");
		
	}
	
}
