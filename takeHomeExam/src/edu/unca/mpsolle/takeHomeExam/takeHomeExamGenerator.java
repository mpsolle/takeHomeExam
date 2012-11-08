package edu.unca.mpsolle.takeHomeExam;

//The purpose of the world generator is to provide a game-like opportunity to fight others in the sky.
//It's a world-generator arena if you will

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.server.BlockSand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class takeHomeExamGenerator extends ChunkGenerator {
		
		@SuppressWarnings("unused")
		private takeHomeExam plugin;
	
		public takeHomeExamGenerator(takeHomeExam instance){
			this.plugin = instance;
		}
		
		public List<BlockPopulator> getDefaultPopulators(World world) {
			ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
			
			populators.add(new takeHomeExamTreePopulator());
			
			return populators;
		}
		
		public Location getFixedSpawnLocation(World world, Random random) {
			return new Location(world, 0, 46, 0);
		}
		
		private int coordsToInt(int x, int y, int z) {
			return (x * 16 + z) * 128 + y;
		}
		
		public byte[] generate(World world, Random rand, int chunkX, int chunkZ) {
			byte[] blocks = new byte[32768];
			
			//Sets spawn to be safe point
			blocks[this.coordsToInt(0, 46, 0)] = (byte) Material.GLASS.getId();
			
			
			int x, y, z;
			for (x = 0; x < 16; ++x) {
				for(z = 0; z < 16; ++z) {
					//Base Floor of Lava
					blocks[this.coordsToInt(x, 1, z)] = (byte) Material.LAVA.getId();					
					blocks[this.coordsToInt(x, 0, z)] = (byte) Material.BEDROCK.getId();	
					//Laying of sky blocks randomly
					for(y = 1; y < 50; ++y) {
						if(y % 2 == 0) {
							//Random glass blocks in the sky.
							if(rand.nextInt(10) == 1) {							
								blocks[this.coordsToInt(x, y, z)] = (byte) Material.GLASS.getId();
							}
							//One in 1000 glass blocks will have lava ontop of it.
							if(rand.nextInt(1000) == 1) {
								blocks[this.coordsToInt(x, y+1, z)] = (byte) Material.LAVA.getId();
							}
						}
					//Grass layers to help with FPS issues
					if(y % 6 == 0) {
						blocks[this.coordsToInt(x, y, z)] = (byte) Material.GRASS.getId();
					}
						
						
						
					}
					
					
					//Random water streams poring down
					int water = rand.nextInt(1000);
					if(water == 1) {
						blocks[this.coordsToInt(x, 53, z)] = (byte) Material.WATER.getId();
					}
					//Random Cactus placement 3 high
					if(water%100 == 0) {
						blocks[this.coordsToInt(x, 47, z)] = (byte) Material.CACTUS.getId();
						blocks[this.coordsToInt(x, 48, z)] = (byte) Material.CACTUS.getId();
					}
					
					//Too hard on server
					//blocks[this.coordsToInt(x, 31, z)] = (byte) Material.SAND.getId();
					//blocks[this.coordsToInt(x, 38, z)] = (byte) Material.GRAVEL.getId();
					//blocks[this.coordsToInt(x, 45, z)] = (byte) Material.SAND.getId();
					//BlockSand.instaFall = false;
				}
			}
			
			return blocks;
		}
		
		

}
