package it.csi.gescovid.acquistiapi.business.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<S,D> {
	
	public D to(S source);
	
	public S from(D dest);
	
	
	public default List<D> toList(List<S> list){
		if(list==null) {
			return new ArrayList<>();
		}
		return list.stream().map(s -> to(s)).collect(Collectors.toList());
	}
	
	public default List<S> fromList(List<D> list){
		if(list==null) {
			return new ArrayList<>();
		}
		return list.stream().map(s -> from(s)).collect(Collectors.toList());
	}
	
}