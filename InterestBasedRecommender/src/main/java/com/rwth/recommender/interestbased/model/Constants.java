/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model;

/**
 *
 * @author Marco
 */
public class Constants {
    
    public static String WORDNET_FOLDER = "C:/Program Files (x86)/WordNet/2.1/dict";

    //SVD
    public static int MAX_DIMENSIONS_TO_CHECK_SIMILARITY = 10;
            
    public static String INTEREST_SEPARATOR = ",";
    public static String ITEM_SEPARATOR = ";";
    
    public static Double MULTIPLIER_TO_BE_EQUAL = 0.3;
    
    public static int NUMBER_OF_BEST_OBJECTS_TO_RETURN = 5;
    
    public static double SCORE_FACTOR_FROM_FREEBASE_TO_BE_CONSIDERED = 0.7;
        
    public static int MAX_WEIGHTING = 1000;
    
    public static Double MINIMUM_ANGEL_TO_BE_EQUAL_PERSON = 0.2;
}
