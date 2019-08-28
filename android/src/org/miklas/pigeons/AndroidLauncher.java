package org.miklas.pigeons;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import org.miklas.pigeons.PigeonsGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new PigeonsGame(), config);
	}
}
