package edu.unca.mpsolle.takeHomeExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
			return new Location(world, 0, 25, 0);
		}
		
		private int coordsToInt(int x, int y, int z) {
			return (x * 16 + z) * 128 + y;
		}
		
		public byte[] generate(World world, Random rand, int chunkX, int chunkZ) {
			byte[] blocks = new byte[32768];
			int x, y, z;
			
			for (x = 0; x < 16; ++x) {
				for(z = 0; z < 16; ++z) {
					blocks[this.coordsToInt(x, 0, z)] = (byte) Material.BEDROCK.getId();
					
					for(y = 1; y < 20; ++y) {
						blocks[this.coordsToInt(x, y, z)] = (byte) Material.DIRT.getId();
					}
					
					blocks[this.coordsToInt(x, 20, z)] = (byte) Material.GRASS.getId();
				}
			}
			
			return blocks;
		}
		
		

}
