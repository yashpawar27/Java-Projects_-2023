package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//We aren't playing music, so we don't need to run it in a different thread.
//Java was created in the 1990's so it's not good with games(fast things like bullets, sound, etc)
public class Sound
{
	private Clip clip;
	private AudioInputStream AIS;
	
	Sound(String file)
	{
		//catch is used so you can fix the Exception
		try
		{
			AIS = AudioSystem.getAudioInputStream(new File(file));
			clip = AudioSystem.getClip();
			clip.open(AIS);
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
	}
	
	public void play(int volumeControl)
	{
		//Allows you to set the volume
		FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(volumeControl);
		//Designed to play short clips
		clip.loop(1);
	}
	
}
