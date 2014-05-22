package template.core;

import com.fasterxml.jackson.databind.JsonNode;
import template.util.JsonHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dlzhu
 * Date: 13-12-5
 * Time: PM4:36
 * To change this template use File | Settings | File Templates.
 */
public class TechnoSearchResult {

    private String resource_id;
    private String resource_type;
    private String item_id;
    private String name;
    private String feature_type;
    private String feature_attribute;

    public TechnoSearchResult() {
    }

//    public void setResource_id(String resource_id){
//        this.resource_id=resource_id;
//    }
//    public String getResource_id(){
//        return resource_id;
//    }
//    public void setResource_type(String resource_type){
//        this.resource_type=resource_type;
//    }
//    public String getResource_type(){
//        return resource_type;
//    }
//    public void setItem_id(String item_id){
//        this.item_id=item_id;
//    }
//    public String getItem_id(){
//        return item_id;
//    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
//    public void setPopulation(String population){
//        this.population=population;
//    }
//    public String getPopulation(){
//        return population;
//    }
//    public void setFeature_type(String feature_type){
//        this.feature_type=feature_type;
//    }
//    public String getFeature_type(){
//        return feature_type;
//    }
//    public void setFeature_attribute(String feature_attribute){
//        this.feature_attribute=feature_attribute;
//    }
//    public String getFeature_attribute(){
//        return feature_attribute;
//    }


    public void add(String resource_id,String resource_type,String item_id,String name,String feature_type,String feature_attribute){
        this.resource_id=resource_id;
        this.resource_type=resource_type;
        this.item_id=item_id;
        this.name=name;
        this.feature_type=feature_type;
        this.feature_attribute=feature_attribute;
    }

    public String toJsonString() throws Exception {
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("resource_id", resource_id);
        rMap.put("resource_type",resource_type);
        rMap.put("item_id", item_id);
        rMap.put("name", name);
        rMap.put("population", 0);
        rMap.put("feature_type", feature_type);
        rMap.put("feature_attribute", feature_attribute);
        JsonNode jNode = JsonHelper.valueToTree(rMap);
        return jNode.toString();
    }
}
