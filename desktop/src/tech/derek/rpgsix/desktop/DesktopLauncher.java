package tech.derek.rpgsix.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tech.derek.rpgsix.App;
import tech.derek.rpgsix.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 920;
		config.height = 720;
		new LwjglApplication(new App(), config);
	}
}
