package br.tips.core.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.tips.core.model.entities.Topic;

@XmlRootElement(name = "responseList")
public class ResponseList {
	
	 private List<String> list;

	    public ResponseList(List<String> next) {
	    	list = next;
	}
	    public ResponseList() {
	    	list = new ArrayList<>();
	    }

		public List<String> getList() {
	        return list;
	    }

	    public void setList(List<String> list) {
	        this.list = list;
	    }
	
}
