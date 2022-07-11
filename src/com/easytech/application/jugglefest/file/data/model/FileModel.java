package com.easytech.application.jugglefest.file.data.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.easytech.application.jugglefest.file.parser.FileParser;

public class FileModel {
	final private HashMap<Integer, Circuit> mapIdToCircuit;
	final private HashMap<Integer, Juggler> mapIdToJuggler;

//	public static FileModel getInstance(final FileParser p_oParser) throws Exception
//	{
//		return new FileModel(p_oParser.getCircuitMap(), p_oParser.getJugglerMap());
//	}
	
	public FileModel(final HashMap<Integer, Circuit> p_mapCircuit, final HashMap<Integer, Juggler> p_mapJuggler) {
		mapIdToCircuit = p_mapCircuit;
		mapIdToJuggler = p_mapJuggler;
	}
	
	public HashMap<Integer,Circuit> getCircuitMap(){
		return mapIdToCircuit;
	}
	
	public HashMap<Integer,Juggler> getJugglerMap(){
		return mapIdToJuggler;
	}

	public Circuit getCircuit(final Integer p_iCircuitId) {
		return mapIdToCircuit.get(p_iCircuitId);
	}

	public List<Circuit> getCircuits() {
		return new ArrayList<>(mapIdToCircuit.values());
	}

	public Juggler getJuggler(final Integer p_iJugglerId) {
		return mapIdToJuggler.get(p_iJugglerId);
	}

	public List<Juggler> getJugglers() {
		return new ArrayList<>(mapIdToJuggler.values());
	}

	public List<Juggler> getSortedJugglers(final Comparator<Juggler> p_Comparator) {
		final List<Juggler> jugglers = getJugglers();
		jugglers.sort(p_Comparator);
		return jugglers;
	}
}
