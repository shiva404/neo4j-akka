package com.campusconnect.neo4j.types.web;

import java.util.List;

public class HistoryEventPage {
	
	
	 List<HistoryEvent> historyEvents;
	    private int offset;

	    private int size;


	    public HistoryEventPage() {
			super();
		}

		public HistoryEventPage(List<HistoryEvent> historyEvent, int offset, int size) {
	        super();
	        this.historyEvents = historyEvents;
	        this.offset = offset;
	        this.size = size;
	    }

		public List<HistoryEvent> getHistoryEvents() {
			return historyEvents;
		}

		public void setHistoryEvents(List<HistoryEvent> historyEvents) {
			this.historyEvents = historyEvents;
		}

		public int getOffset() {
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

}
