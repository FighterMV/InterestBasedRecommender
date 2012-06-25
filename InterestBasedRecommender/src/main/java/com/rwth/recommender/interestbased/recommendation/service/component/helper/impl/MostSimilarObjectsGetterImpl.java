/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper.impl;

import com.rwth.recommender.interestbased.recommendation.service.component.helper.MostSimilarObjectsGetter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class MostSimilarObjectsGetterImpl<E> implements MostSimilarObjectsGetter<E> {

    @Override
    public List<E> getXMostSimilarObjects(Map angleMap, int numberOfObjectsToReturn) {
	List<E> bestObjects = new ArrayList<E>();
	for (Iterator<E> it = angleMap.keySet().iterator(); it.hasNext();) {
	    E candidate = it.next();
	    if(bestObjects.size() <= numberOfObjectsToReturn){
		bestObjects.add(candidate);
	    }else{
		E weakestObjectInList = getWeakestPersonInList(bestObjects, angleMap);
		Double candidateAngle = (Double) angleMap.get(candidate);
		Double weakestObjectInListAngle = (Double) angleMap.get(weakestObjectInList);
		if(candidateAngle < weakestObjectInListAngle){
		    bestObjects.remove(weakestObjectInList);
		    bestObjects.add(candidate);
		}
	    }
	}
	
	return bestObjects;
    }
    
    private E getWeakestPersonInList(List objects, Map angleMap){
	if(objects.isEmpty()){
	    return null;
	}
	E weakestObject = (E) objects.get(0);
	
	for (Iterator<E> it = objects.iterator(); it.hasNext();) {
	    E object = it.next();
	    if((Double) angleMap.get(object) > (Double) angleMap.get(weakestObject)){
		weakestObject = object;
	    }
	}
	
	return weakestObject;
	
    }
    
}
