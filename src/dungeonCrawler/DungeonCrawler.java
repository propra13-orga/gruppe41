package dungeonCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DungeonCrawler {

	/**
	 * Game launcher
	 * 
	 * @param args (has no function)
	 * <p>
	 * @author Tissen
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		
		
		
		FileReader fr = null;
		try {
			fr = new FileReader("Levels" + File.separator + "level.lvl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);

		String zeile1 = br.readLine();
		System.out.println(zeile1);
		br.close();

		Thread t = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(Long.MAX_VALUE);
//						Thread.sleep(5000);
//						System.out.println("test");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
//		t.setDaemon(true);
		t.start();

		App app = new App(Integer.parseInt(zeile1), 500, 250); // number of levels, width and length of a dungeon
		app.start();
	}
	
	public static void credits() {

	}	
	
	
}
