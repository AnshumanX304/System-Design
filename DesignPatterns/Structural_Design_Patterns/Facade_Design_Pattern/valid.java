package DesignPatterns.Structural_Design_Patterns.Facade_Design_Pattern;
import java.util.Scanner;


class MusicPlayer{
    public void initialiseAudioDrivers(){
        System.out.println("The audio drivers have been initialised !!");
    }
    public void decodeAudio(){
        System.out.println("The audio is being decoded !!");
    }
    public void startPlayback(){
        System.out.println("The audio playback has started !!");
    }
}

class VideoPlayer{
    public void setupRenderingEngine(){
        System.out.println("The rendering engine have been setup !!");
    }
    public void loadVideoFile(){
        System.out.println("The video file is being loaded !!");
    }
    public void playVideo(){
        System.out.println("The video has started to play !!");
    }
}

class MediaFacade{
    private MusicPlayer musicPlayer;
    private VideoPlayer videoplayer;

    public MediaFacade(){
        this.musicPlayer=new MusicPlayer();
        this.videoplayer=new VideoPlayer();
    }

    public void performAction(String action){
        switch (action.toLowerCase()){
            case "playmusic":
                musicPlayer.initialiseAudioDrivers();
                musicPlayer.decodeAudio();
                musicPlayer.startPlayback();
            case "playvideo":
                videoplayer.setupRenderingEngine();
                videoplayer.loadVideoFile();
                videoplayer.playVideo();
            default:
                System.out.println("Invalid Action!");
        }
    }
}
public class valid {
    public static void main(String []args){
        MediaFacade mediaFacade = new MediaFacade();
        Scanner scanner=new Scanner(System.in);
        String action=scanner.nextLine();
        mediaFacade.performAction(action);
        scanner.close();
    }
}
