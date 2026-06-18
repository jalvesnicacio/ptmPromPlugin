package org.processmining.plugins.beepbeep.miner.processors;

import java.util.Map;
import ca.uqac.lif.cep.functions.UnaryFunction;


public class MapSize extends UnaryFunction<Map, Integer> {
    public static final MapSize instance = new MapSize();
    
    public MapSize()
    {
        super(Map.class, Integer.class);
    }
    
    @Override
    public Integer getValue(Map x)
    {
        return x.size();
    }

	

}
