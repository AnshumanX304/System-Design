package DesignPatterns.Behavioral_Design_Patterns.Iterator_Design_Pattern;

import java.util.ArrayList;
import java.util.Collections;

class Playlist{
    private ArrayList<String> songs;
    public Playlist(){
        songs=new ArrayList<>();
    }
    public Playlist(Playlist other) {
        songs = new ArrayList<>(other.getSongs());
    }
    public void addSong(String song){
        songs.add(song);
    }
    public ArrayList<String> getSongs(){
        return songs;
    }

    public PlaylistIterator getIterator(String type){
        switch (type) {
        case "simple":
            return new NormalPlaylistIterator(this);
        case "shuffled":
            return new ShuffledPlaylistIterator(this);
        case "favorites":
            return new FavouritePlaylistIterator(this);
        default:
            return null;
        }
    }
}

interface PlaylistIterator{
    boolean hasNext();
    String next();
}

class NormalPlaylistIterator implements PlaylistIterator{
    private Playlist normalplaylist;
    private int index;

    public NormalPlaylistIterator(Playlist playlist){
        this.normalplaylist=playlist;
        index=0;
    }   

    @Override
    public boolean hasNext() {
        return index < normalplaylist.getSongs().size();
    }
    @Override
    public String next(){
        return normalplaylist.getSongs().get(index++);
    }
}

class ShuffledPlaylistIterator implements PlaylistIterator{
    private Playlist shuffledplaylist;
    private int index;

    public ShuffledPlaylistIterator(Playlist playlist){
        Playlist newplaylist=new Playlist(playlist);
        Collections.shuffle(newplaylist.getSongs());
        this.shuffledplaylist= newplaylist;
        index=0;
    }

    @Override
    public boolean hasNext() {
        return index < shuffledplaylist.getSongs().size();
    }
    @Override
    public String next(){
        return shuffledplaylist.getSongs().get(index++);
    }

}

class FavouritePlaylistIterator implements PlaylistIterator{
    private Playlist favplaylist;
    private int index;

    public FavouritePlaylistIterator(Playlist playlist){
        this.favplaylist=playlist;
        index=0;
    }

    @Override
    public boolean hasNext() {
        while(index < favplaylist.getSongs().size()){
            if (favplaylist.getSongs().get(index).toLowerCase().contains("fav")) {
                break;
            }
            index++;
        }

        return index<favplaylist.getSongs().size();

    }
    @Override
    public String next(){
        return favplaylist.getSongs().get(index++);
    }

}



public class Iterator_DP {
    public static void main(String [] args){
        Playlist playlist = new Playlist();
        playlist.addSong("Song 1");
        playlist.addSong("Song 2 Fav");
        playlist.addSong("Song 3");
        playlist.addSong("Song 4 Fav");
        playlist.addSong("Song 5");

        // Simple Playlist Iterator
        System.out.println("Simple Playlist:");
        PlaylistIterator normalIterator = playlist.getIterator("simple");
        while (normalIterator.hasNext()) {
            System.out.println("Playing: " + normalIterator.next());
        }

        // Shuffled Playlist Iterator
        System.out.println("nShuffled Playlist:");
        PlaylistIterator shuffledIterator = playlist.getIterator("shuffled");
        while (shuffledIterator.hasNext()) {
            System.out.println("Playing: " + shuffledIterator.next());
        }

        // Favorites Playlist Iterator
        System.out.println("nFavorites Playlist:");
        PlaylistIterator favoritesIterator = playlist.getIterator("favorites");
        while (favoritesIterator.hasNext()) {
            System.out.println("Playing: " + favoritesIterator.next());
        }
    }
}
