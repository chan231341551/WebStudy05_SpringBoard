package kr.or.ddit.ui;

import java.util.Map;

public class DefaultPaginationManager implements PaginationManager {

	private Map<String, PaginationRenderer> rendererMap;
	
	
	public DefaultPaginationManager(Map<String, PaginationRenderer> rendererMap) {
		super();
		this.rendererMap = rendererMap;
	}


	@Override
	public PaginationRenderer rendererType(String type) {
		
		PaginationRenderer renderer = rendererMap.get(type);
		if(renderer == null) {
			renderer = new DefaultPaginationRenderer();
		}
		return renderer;
	}

}
