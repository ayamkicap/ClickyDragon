package module.model;

import java.io.*;
import javax.sound.sampled.*;

public class soundEffect {

   // Constructor
   public soundEffect(boolean MUTE) {
         try {
            // Open an audio input stream.
            File soundFile = new File(
<<<<<<< HEAD
                  "./src/resource/sound2-inGame.wav");
=======
                  "/Users/User/OneDrive/Desktop/ClickyDragon/ClickyDragon/src/resource/sound2-inGame.wav");
>>>>>>> 78c2c9845ff0d0bd8a2a3478f67a154dc36cd217

            // URL
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioIn.getFormat();
            // Get a sound clip resource.
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
         } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         }
      }
}