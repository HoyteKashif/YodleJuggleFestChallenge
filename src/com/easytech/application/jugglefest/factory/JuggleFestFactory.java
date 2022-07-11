package com.easytech.application.jugglefest.factory;

import com.easytech.application.jugglefest.file.data.model.FileModel;
import com.easytech.application.jugglefest.file.data.model.JuggleFest;
import com.easytech.application.jugglefest.file.data.model.JuggleFestFile;
import com.easytech.application.jugglefest.matcher.CircuitMatcher;

public class JuggleFestFactory {
	private JuggleFestFactory(){}
	
	public static JuggleFest getJuggleFest(final FileModel p_FileModel)
	{
		JuggleFestFile jff = new JuggleFestFile(p_FileModel.getCircuitMap() , p_FileModel.getJugglerMap());
		return new CircuitMatcher(jff).match();
		
//		return new JuggleFest(p_FileModel.getCircuitMap());
	}
}
