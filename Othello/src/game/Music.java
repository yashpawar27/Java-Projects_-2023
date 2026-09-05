package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music extends Thread
{

		File file;
		AudioInputStream audioStream;
		Clip clip;
		
		//@Override does nothing, just good programming eddicate
		@Override
		public void run()
		{
			//Imported try/catch
			try 
			{
				file = new File("src/images/Spatial.wav");
				audioStream = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} 
			catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
			{
				
				e.printStackTrace();
			} 
		}
	
}
