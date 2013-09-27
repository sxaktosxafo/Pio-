package com.ias.chick.level;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ias.chick.Game;
import com.ias.chick.entity.EntitySaveLoad;


public class CurrentLevelSaver {
    
	private static Level level;
	private static int height;
	private static int width;
	private static final int terminal=0x3B;
	
	public CurrentLevelSaver(){}
	
	/** Saves the current level (tilesInt+stairs) and calls the function to save the previous ones
	 *  In the current level, player health, mana, spells and keys are saved too
	 * */
	public static void levelSaver(Level level)
	{
		
		width=level.width;
		height=level.height;
		
		try{
			
			File path = new File("");
			String savePath = path.getCanonicalPath()+"/res/saves/saved.lev";
			
			System.out.println(savePath);
			
			System.out.println("player x: "+(Game.game.player.x>>4)+" y: "+(Game.game.player.y>>4));
			
			FileOutputStream file = new FileOutputStream(savePath);
			
			//  things
			file.write(width);
			file.write(height);
			file.write(level.id);
			file.write(level.x1);
			file.write(level.y1);
			file.write(level.x2);
			file.write(level.y2);
			file.write((Game.game.player.x>>4));
			file.write((Game.game.player.y>>4));
			file.write(Game.game.player.mana);
			file.write(Game.game.player.hp);
			
			
			// keys
			for(int i=0; i<Game.game.keys.length; i++)
				if(Game.game.keys[i])
					file.write(0xAF);
				else
					file.write(0x0F);
			
			
			// spells
            file.write(Game.game.player.spells.size());
			
			for(int spell=0; spell<Game.game.player.spells.size(); spell++)
				file.write(Game.game.player.spells.get(spell));
			
			file.write(terminal);
			
			// tiles
			for(int y=0; y<height; y++)
				for(int x=0; x<width; x++)
					file.write(level.tilesInt[x+y*width]);
			
			file.close();
			
		}catch(Exception e){System.out.println(e);}
		
		for(int i=(level.id-1); i>0; i--)
			otherLevelSaver(i); //*/
		
		EntitySaveLoad.saveEntities();
	}
	
	private static void otherLevelSaver(int id){
		
       level=Game.game.getLevel(id);
		
		try{
			
			File path = new File("");
			String savePath = path.getCanonicalPath()+"/res/levels/"+id+".lev";
			
			System.out.println(savePath);
			
            FileOutputStream file = new FileOutputStream(savePath);
			
			file.write(level.width);
			file.write(level.height);
			file.write(level.id);
			file.write(level.x1);
			file.write(level.y1);
			file.write(level.x2);
			file.write(level.y2);
     		file.write(terminal);
			
						
			for(int y=0; y<level.height; y++){
				for(int x=0; x<level.width; x++)
					file.write(level.tilesInt[x+y*level.width]);

			}
			
			file.close();
			
		}catch(Exception e){ System.out.println(e);}
		
	}

	public static void cancelSaved(){
		
		try{
			
			File path = new File("");
			String savePath = path.getCanonicalPath()+"/res/levels/saved.lev";
			FileOutputStream file = new FileOutputStream(savePath);
			
			for(int i=0; i<30; i++)
				file.write(0);
			
			file.close();
		
		}catch(Exception e){ System.out.println(e); }
		
	}
	
}
