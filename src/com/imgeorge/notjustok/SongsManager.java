package com.imgeorge.notjustok;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SongsManager {
	
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	
	// Constructor
	public SongsManager(){
		
	}
	
	/**
	 * Function to read all mp3 files from notjustok.com
	 * and store the details in ArrayList
	 * @throws IOException 
	 * */
	public ArrayList<HashMap<String, String>> getPlayList() throws IOException{
		Document doc = Jsoup
				.connect("http://notjustok.com/wp-content/themes/notjustok/njo_top_player.php#.")
				.userAgent("Mozilla")
				.cookie("auth", "token").timeout(69999).post();
		Elements result = doc.select("#xmp_playlist li a");
		String Title = "";
		String SongPath = "";
		
		//String s[] = new String[result.size()];
		for(Element el : result){
			Title=el.text();
			SongPath= el.attr("data-src");
			
		    HashMap<String, String> song = new HashMap<String, String>();
			song.put("songTitle",Title );
			song.put("songPath", SongPath);
			// Adding each song to SongList
			songsList.add(song);
		    
		}
		// return songs list array
		return songsList;
	}
	
	/**
	 * Class to filter files which are having .mp3 extension
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}
